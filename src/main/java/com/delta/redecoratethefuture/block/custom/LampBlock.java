package com.delta.redecoratethefuture.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class LampBlock extends EndRodBlock implements SimpleWaterloggedBlock {

    public LampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(UP, false).setValue(DOWN,false).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    private static final VoxelShape STANDING_EMPTY = Block.box(3, 0, 3, 13, 2, 13);
    private static final VoxelShape HANGING_EMPTY = Block.box(5, 14, 5, 11, 16, 11);
    private static final VoxelShape WALL_NORTH_EMPTY = Block.box(4, 3, 14, 12, 11, 16);
    private static final VoxelShape WALL_SOUTH_EMPTY = Block.box(4, 3, 0, 12, 11, 2);
    private static final VoxelShape WALL_EAST_EMPTY = Block.box(0, 3, 4, 2, 11, 12);
    private static final VoxelShape WALL_WEST_EMPTY = Block.box(14, 3, 4, 16, 11, 12);
    private static final VoxelShape BASE = Stream.of(Block.box(3, 0, 3, 13, 2, 13), Block.box(7, 2, 7, 9, 16, 9)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape POST = Block.box(7, 0, 7, 9, 16, 9);
    private static final VoxelShape UPPER_EMPTY = Block.box(6, 0, 6, 10, 1, 10);


    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        boolean up = state.getValue(UP);
        boolean down = state.getValue(DOWN);

        switch (state.getValue(FACING)) {
            case NORTH -> {
                return WALL_NORTH_EMPTY;
            }
            case SOUTH -> {
                return WALL_SOUTH_EMPTY;
            }
            case EAST -> {
                return WALL_EAST_EMPTY;
            }
            case WEST -> {
                return WALL_WEST_EMPTY;
            }
            case UP -> {
                if (state.getValue(UP) && !state.getValue(DOWN)) return BASE;
                if (state.getValue(UP) && state.getValue(DOWN)) return POST;
                if (!state.getValue(UP) && state.getValue(DOWN)) {
                    return UPPER_EMPTY;
                }
                return STANDING_EMPTY;
            }
            case DOWN -> {
                if (state.getValue(UP) && !state.getValue(DOWN)) return BASE;
                if (state.getValue(UP) && state.getValue(DOWN)) return POST;
                if (!state.getValue(UP) && state.getValue(DOWN)) {
                    return UPPER_EMPTY;
                }
                return HANGING_EMPTY;
            }
            default -> {
                return STANDING_EMPTY;
            }
        }
    }

    private boolean isLamp(LevelAccessor level, BlockPos source, Direction direction)
    {
        BlockState state = level.getBlockState(source.relative(direction));
        return state.getBlock() == this;
    }

    private boolean isOnWall(LevelAccessor level, BlockPos source, Direction direction) {
        BlockState state = level.getBlockState(source.relative(direction));
        switch (state.getValue(FACING)) {
            case NORTH, SOUTH, EAST, WEST -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos)
    {
        boolean up = this.isLamp(level, pos, Direction.UP);
        boolean down = this.isLamp(level, pos, Direction.DOWN);

        if (isLamp(level, pos, Direction.UP)) {
            if (isOnWall(level, pos, Direction.UP)) {
                up = false;
            }
        }
        if (isLamp(level, pos, Direction.DOWN)) {
            if (isOnWall(level, pos, Direction.DOWN)) {
                down = false;
            }
        }

        return super.updateShape(state.setValue(UP, up).setValue(DOWN, down), direction, state, level, pos, newPos);
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getClickedFace();
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(direction.getOpposite()));
        FluidState fluidState = pContext.getLevel().getFluidState(pContext.getClickedPos());

        return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }
    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UP, DOWN, WATERLOGGED);
    }
}
