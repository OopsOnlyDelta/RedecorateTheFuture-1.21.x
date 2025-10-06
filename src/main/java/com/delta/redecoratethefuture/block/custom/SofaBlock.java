package com.delta.redecoratethefuture.block.custom;

import com.delta.redecoratethefuture.entity.ModEntities;
import com.delta.redecoratethefuture.entity.custom.SeatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

public class SofaBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    public SofaBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(NORTH, false).setValue(SOUTH, false).setValue(EAST, false).setValue(WEST, false));
    }

    private static final VoxelShape SHAPE_NORTH = Stream.of(Block.box(0, 0, 13, 3, 2, 16),
            Block.box(0, 0, 0, 3, 2, 3),
            Block.box(13, 0, 13, 16, 2, 16),
            Block.box(13, 0, 0, 16, 2, 3),
            Block.box(0, 5, 13, 16, 12, 16),
            Block.box(0, 2, 0, 16, 5, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_SOUTH = Stream.of(Block.box(13, 0, 0, 16, 2, 3),
            Block.box(13, 0, 13, 16, 2, 16),
            Block.box(0, 0, 0, 3, 2, 3),
            Block.box(0, 0, 13, 3, 2, 16),
            Block.box(0, 5, 0, 16, 12, 3),
            Block.box(0, 2, 0, 16, 5, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_EAST = Stream.of(Block.box(0, 0, 0, 3, 2, 3),
            Block.box(13, 0, 0, 16, 2, 3),
            Block.box(0, 0, 13, 3, 2, 16),
            Block.box(13, 0, 13, 16, 2, 16),
            Block.box(0, 5, 0, 3, 12, 16),
            Block.box(0, 2, 0, 16, 5, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_WEST = Stream.of(Block.box(13, 0, 13, 16, 2, 16),
            Block.box(0, 0, 13, 3, 2, 16),
            Block.box(13, 0, 0, 16, 2, 3),
            Block.box(0, 0, 0, 3, 2, 3),
            Block.box(13, 5, 0, 16, 12, 16),
            Block.box(0, 2, 0, 16, 5, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_NORTH_MIDDLE = Stream.of(Block.box(0, 5, 13, 16, 12, 16), Block.box(0, 2, 0, 16, 5, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_SOUTH_MIDDLE = Stream.of(Block.box(0, 5, 0, 16, 12, 3), Block.box(0, 2, 0, 16, 5, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_EAST_MIDDLE = Stream.of(Block.box(0, 5, 0, 3, 12, 16), Block.box(0, 2, 0, 16, 5, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_WEST_MIDDLE = Stream.of(Block.box(13, 5, 0, 16, 12, 16), Block.box(0, 2, 0, 16, 5, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_NORTH_RIGHT = Stream.of(Block.box(0, 0, 0, 3, 2, 3), Block.box(0, 0, 13, 3, 2, 16), Block.box(0, 2, 0, 16, 5, 16), Block.box(0, 5, 13, 16, 12, 16), Block.box(0, 5, 0, 3, 8, 13)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_SOUTH_RIGHT = Stream.of(Block.box(13, 0, 13, 16, 2, 16), Block.box(13, 0, 0, 16, 2, 3), Block.box(0, 2, 0, 16, 5, 16), Block.box(0, 5, 0, 16, 12, 3), Block.box(13, 5, 3, 16, 8, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_EAST_RIGHT = Stream.of(Block.box(13, 0, 0, 16, 2, 3), Block.box(0, 0, 0, 3, 2, 3), Block.box(0, 2, 0, 16, 5, 16), Block.box(0, 5, 0, 3, 12, 16), Block.box(3, 5, 0, 16, 8, 3)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_WEST_RIGHT = Stream.of(Block.box(0, 0, 13, 3, 2, 16), Block.box(13, 0, 13, 16, 2, 16), Block.box(0, 2, 0, 16, 5, 16), Block.box(13, 5, 0, 16, 12, 16), Block.box(0, 5, 13, 13, 8, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_NORTH_LEFT = Stream.of(Block.box(13, 0, 0, 16, 2, 3), Block.box(13, 0, 13, 16, 2, 16), Block.box(0, 2, 0, 16, 5, 16), Block.box(0, 5, 13, 16, 12, 16), Block.box(13, 5, 0, 16, 8, 13)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_SOUTH_LEFT = Stream.of(Block.box(0, 0, 13, 3, 2, 16), Block.box(0, 0, 0, 3, 2, 3), Block.box(0, 2, 0, 16, 5, 16), Block.box(0, 5, 0, 16, 12, 3), Block.box(0, 5, 3, 3, 8, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_EAST_LEFT = Stream.of(Block.box(13, 0, 13, 16, 2, 16), Block.box(0, 0, 13, 3, 2, 16), Block.box(0, 2, 0, 16, 5, 16), Block.box(0, 5, 0, 3, 12, 16), Block.box(3, 5, 13, 16, 8, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SHAPE_WEST_LEFT = Stream.of(Block.box(0, 0, 0, 3, 2, 3), Block.box(13, 0, 0, 16, 2, 3), Block.box(0, 2, 0, 16, 5, 16), Block.box(13, 5, 0, 16, 12, 16), Block.box(0, 5, 0, 13, 8, 3)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case SOUTH -> {
                if (state.getValue(WEST) && state.getValue(EAST)) {
                    return SHAPE_SOUTH_MIDDLE;
                }
                if (state.getValue(EAST)){
                    return SHAPE_SOUTH_LEFT;
                }
                if (state.getValue(WEST)){
                    return SHAPE_SOUTH_RIGHT;
                }
                return SHAPE_SOUTH;
            }
            case WEST -> {
                if (state.getValue(NORTH) && state.getValue(SOUTH)) {
                    return SHAPE_WEST_MIDDLE;
                }
                if (state.getValue(NORTH)){
                    return SHAPE_WEST_RIGHT;
                }
                if (state.getValue(SOUTH)){
                    return SHAPE_WEST_LEFT;
                }
                return SHAPE_WEST;
            }
            case EAST -> {
                if (state.getValue(NORTH) && state.getValue(SOUTH)) {
                    return SHAPE_EAST_MIDDLE;
                }
                if (state.getValue(NORTH)){
                    return SHAPE_EAST_LEFT;
                }
                if (state.getValue(SOUTH)){
                    return SHAPE_EAST_RIGHT;
                }
                return SHAPE_EAST;
            }
            default -> {
                if (state.getValue(WEST) && state.getValue(EAST)) {
                    return SHAPE_NORTH_MIDDLE;
                }
                if (state.getValue(EAST)){
                    return SHAPE_NORTH_RIGHT;
                }
                if (state.getValue(WEST)){
                    return SHAPE_NORTH_LEFT;
                }
                return SHAPE_NORTH;
            }
        }
    }
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos) {
        boolean north = this.isSofa(level, pos, Direction.NORTH);
        boolean east = this.isSofa(level, pos, Direction.EAST);
        boolean south = this.isSofa(level, pos, Direction.SOUTH);
        boolean west = this.isSofa(level, pos, Direction.WEST);


        if (isSofa(level, pos, Direction.NORTH)){
            if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
                north = false;
                south = false;
            }
            if (state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST) {
                if (isNorthSouth(level, pos, Direction.NORTH)) {
                    north = false;
                }
            }
        }

        if (isSofa(level, pos, Direction.SOUTH)){
            if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
                north = false;
                south = false;
            }
            if (state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST) {
                if (isNorthSouth(level, pos, Direction.SOUTH)) {
                    south = false;
                }
            }
        }



        if (isSofa(level, pos, Direction.EAST) || isSofa(level, pos, Direction.WEST)){
            if (state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST) {
                east = false;
                west = false;
            }
            if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {
                if (isSofa(level, pos, Direction.EAST)) {
                    if (!isNorthSouth(level, pos, Direction.EAST)) {
                        east = false;
                    }
                }
                if (isSofa(level, pos, Direction.WEST)) {
                    if (!isNorthSouth(level, pos, Direction.WEST)) {
                        west = false;
                    }
                }
            }
        }


        return state.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
    }

    private boolean isSofa(LevelAccessor level, BlockPos source, Direction direction)
    {
        BlockState state = level.getBlockState(source.relative(direction));
        return state.getBlock() == this;
    }

    private boolean isNorthSouth(LevelAccessor level, BlockPos source, Direction direction) {
        BlockState state = level.getBlockState(source.relative(direction));
        switch (state.getValue(FACING)) {
            case NORTH, SOUTH -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidState = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, WATERLOGGED, NORTH, SOUTH, EAST, WEST);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            Entity entity = null;
            List<SeatEntity> entities = level.getEntities(ModEntities.SEAT_ENTITY.get(), new AABB(pos), seat -> true);
            if(entities.isEmpty()) {
                entity = ModEntities.SEAT_ENTITY.get().spawn(((ServerLevel) level), pos, MobSpawnType.TRIGGERED);
            } else {
                entity = entities.get(0);
            }

            player.startRiding(entity);
        }
        return InteractionResult.SUCCESS;
    }
}
