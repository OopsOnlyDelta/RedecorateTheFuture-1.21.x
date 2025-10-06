package com.delta.redecoratethefuture;

import com.delta.redecoratethefuture.block.ModBlocks;
import com.delta.redecoratethefuture.entity.ModEntities;
import com.delta.redecoratethefuture.entity.client.SeatRenderer;
import com.delta.redecoratethefuture.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(RedecorateTheFuture.MODID)
public class RedecorateTheFuture {
    public static final String MODID = "redecoratethefuture";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RedecorateTheFuture(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.COMPOSITE);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.COMPOSITE_TABLE);
            event.accept(ModBlocks.COMPOSITE_CHAIR);
            event.accept(ModBlocks.ITEM_LOCKER);
        }
        if(event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            event.accept(ModBlocks.WHITE_LAMP);
            event.accept(ModBlocks.LIGHT_GRAY_LAMP);
            event.accept(ModBlocks.GRAY_LAMP);
            event.accept(ModBlocks.BLACK_LAMP);
            event.accept(ModBlocks.GREEN_LAMP);
            event.accept(ModBlocks.LIME_LAMP);
            event.accept(ModBlocks.CYAN_LAMP);
            event.accept(ModBlocks.LIGHT_BLUE_LAMP);
            event.accept(ModBlocks.BLUE_LAMP);
            event.accept(ModBlocks.PURPLE_LAMP);
            event.accept(ModBlocks.MAGENTA_LAMP);
            event.accept(ModBlocks.PINK_LAMP);
            event.accept(ModBlocks.RED_LAMP);
            event.accept(ModBlocks.ORANGE_LAMP);
            event.accept(ModBlocks.YELLOW_LAMP);
            event.accept(ModBlocks.BROWN_LAMP);

            event.accept(ModBlocks.WHITE_SOFA);
            event.accept(ModBlocks.LIGHT_GRAY_SOFA);
            event.accept(ModBlocks.GRAY_SOFA);
            event.accept(ModBlocks.BLACK_SOFA);
            event.accept(ModBlocks.GREEN_SOFA);
            event.accept(ModBlocks.LIME_SOFA);
            event.accept(ModBlocks.CYAN_SOFA);
            event.accept(ModBlocks.LIGHT_BLUE_SOFA);
            event.accept(ModBlocks.BLUE_SOFA);
            event.accept(ModBlocks.PURPLE_SOFA);
            event.accept(ModBlocks.MAGENTA_SOFA);
            event.accept(ModBlocks.PINK_SOFA);
            event.accept(ModBlocks.RED_SOFA);
            event.accept(ModBlocks.ORANGE_SOFA);
            event.accept(ModBlocks.YELLOW_SOFA);
            event.accept(ModBlocks.BROWN_SOFA);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.SEAT_ENTITY.get(), SeatRenderer::new);
        }
    }
}
