package com.delta.redecoratethefuture.block;

import com.delta.redecoratethefuture.RedecorateTheFuture;
import com.delta.redecoratethefuture.block.custom.*;
import com.delta.redecoratethefuture.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;



public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(RedecorateTheFuture.MODID);

    public static final DeferredBlock<Block> COMPOSITE_TABLE = registerBlock("composite_table",
            () -> new TableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.CALCITE)));

    public static final DeferredBlock<Block> COMPOSITE_CHAIR = registerBlock("composite_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> ITEM_LOCKER = registerBlock("item_locker",
            () -> new ItemLockerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.CALCITE)));

    public static final DeferredBlock<Block> WHITE_LAMP = registerBlock("white_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> LIGHT_GRAY_LAMP = registerBlock("light_gray_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> GRAY_LAMP = registerBlock("gray_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> BLACK_LAMP = registerBlock("black_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> LIME_LAMP = registerBlock("lime_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> GREEN_LAMP = registerBlock("green_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> CYAN_LAMP = registerBlock("cyan_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> LIGHT_BLUE_LAMP = registerBlock("light_blue_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> BLUE_LAMP = registerBlock("blue_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> PURPLE_LAMP = registerBlock("purple_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> MAGENTA_LAMP = registerBlock("magenta_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> RED_LAMP = registerBlock("red_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> PINK_LAMP = registerBlock("pink_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> ORANGE_LAMP = registerBlock("orange_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> YELLOW_LAMP = registerBlock("yellow_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));
    public static final DeferredBlock<Block> BROWN_LAMP = registerBlock("brown_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE).lightLevel(state -> 15).noOcclusion().strength(2.0F, 3.0F).sound(SoundType.COPPER_BULB)));

    public static final DeferredBlock<Block> WHITE_SOFA = registerBlock("white_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_GRAY_SOFA = registerBlock("light_gray_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> GRAY_SOFA = registerBlock("gray_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> BLACK_SOFA = registerBlock("black_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIME_SOFA = registerBlock("lime_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> GREEN_SOFA = registerBlock("green_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CYAN_SOFA = registerBlock("cyan_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_BLUE_SOFA = registerBlock("light_blue_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> BLUE_SOFA = registerBlock("blue_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> PURPLE_SOFA = registerBlock("purple_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MAGENTA_SOFA = registerBlock("magenta_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> RED_SOFA = registerBlock("red_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> PINK_SOFA = registerBlock("pink_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> ORANGE_SOFA = registerBlock("orange_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> YELLOW_SOFA = registerBlock("yellow_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> BROWN_SOFA = registerBlock("brown_sofa",
            () -> new SofaBlock(BlockBehaviour.Properties.of().noOcclusion().strength(2.0F, 3.0F).sound(SoundType.WOOL)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
