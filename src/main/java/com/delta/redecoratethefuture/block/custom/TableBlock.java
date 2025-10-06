package com.delta.redecoratethefuture.block.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class TableBlock extends Block implements SimpleWaterloggedBlock
{
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public TableBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape TABLE_TOP = Block.box(0, 13, 0, 16, 16, 16);
        final VoxelShape LEG_NORTH_EAST = Block.box(12, 0, 1, 15, 13, 4);
        final VoxelShape LEG_SOUTH_EAST = Block.box(12, 0, 12, 15, 13, 15);
        final VoxelShape LEG_NORTH_WEST = Block.box(1, 0, 1, 4, 13, 4);
        final VoxelShape LEG_SOUTH_WEST = Block.box(1, 0, 12, 4, 13, 15);

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            boolean north = state.getValue(NORTH);
            boolean east = state.getValue(EAST);
            boolean south = state.getValue(SOUTH);
            boolean west = state.getValue(WEST);

            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(TABLE_TOP);
            if(!north && !west)
            {
                shapes.add(LEG_NORTH_WEST);
            }
            if(!north && !east)
            {
                shapes.add(LEG_NORTH_EAST);
            }
            if(!south && !west)
            {
                shapes.add(LEG_SOUTH_WEST);
            }
            if(!south && !east)
            {
                shapes.add(LEG_SOUTH_EAST);
            }
            //builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        return SHAPES.get(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        return SHAPES.get(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos)
    {
        boolean north = this.isTable(level, pos, Direction.NORTH);
        boolean east = this.isTable(level, pos, Direction.EAST);
        boolean south = this.isTable(level, pos, Direction.SOUTH);
        boolean west = this.isTable(level, pos, Direction.WEST);
        return state.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
    }

    private boolean isTable(LevelAccessor level, BlockPos source, Direction direction)
    {
        BlockState state = level.getBlockState(source.relative(direction));
        return state.getBlock() == this;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidState = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(NORTH, EAST, SOUTH, WEST, WATERLOGGED);
    }
}
