package com.delta.redecoratethefuture.block.custom;

import com.delta.redecoratethefuture.entity.ModEntities;
import com.delta.redecoratethefuture.entity.custom.SeatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
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


public class ChairBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ChairBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    private static final VoxelShape NORTH = Stream.of(
            Block.box(2, 4, 2, 14, 7, 14),
            Block.box(11, 0, 2, 14, 4, 5),
            Block.box(2, 0, 2, 5, 4, 5),
            Block.box(2, 0, 11, 5, 4, 14),
            Block.box(11, 0, 11, 14, 4, 14),
            Block.box(11, 7, 11, 14, 16, 14),
            Block.box(5, 7, 12.475, 11, 16, 12.525),
            Block.box(2, 7, 11, 5, 16, 14)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH = Stream.of(
            Block.box(2, 4, 2, 14, 7, 14),
            Block.box(2, 0, 11, 5, 4, 14),
            Block.box(11, 0, 11, 14, 4, 14),
            Block.box(11, 0, 2, 14, 4, 5),
            Block.box(2, 0, 2, 5, 4, 5),
            Block.box(2, 7, 2, 5, 16, 5),
            Block.box(5, 7, 3.475, 11, 16, 3.525),
            Block.box(11, 7, 2, 14, 16, 5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST = Stream.of(
            Block.box(2, 4, 2, 14, 7, 14),
            Block.box(11, 0, 11, 14, 4, 14),
            Block.box(11, 0, 2, 14, 4, 5),
            Block.box(2, 0, 2, 5, 4, 5),
            Block.box(2, 0, 11, 5, 4, 14),
            Block.box(2, 7, 11, 5, 16, 14),
            Block.box(3.475, 7, 5, 3.525, 16, 11),
            Block.box(2, 7, 2, 5, 16, 5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST = Stream.of(
            Block.box(2, 4, 2, 14, 7, 14),
            Block.box(2, 0, 2, 5, 4, 5),
            Block.box(2, 0, 11, 5, 4, 14),
            Block.box(11, 0, 11, 14, 4, 14),
            Block.box(11, 0, 2, 14, 4, 5),
            Block.box(11, 7, 2, 14, 16, 5),
            Block.box(12.475, 7, 5, 12.525, 16, 11),
            Block.box(11, 7, 11, 14, 16, 14)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(FACING)) {
            case SOUTH -> {
                return SOUTH;
            }
            case WEST -> {
                return WEST;
            }
            case EAST -> {
                return EAST;
            }
            default -> {
                return NORTH;
            }
        }
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidState = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }
    @Override
    public FluidState getFluidState(BlockState state)
    {
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
        pBuilder.add(FACING, WATERLOGGED);
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
