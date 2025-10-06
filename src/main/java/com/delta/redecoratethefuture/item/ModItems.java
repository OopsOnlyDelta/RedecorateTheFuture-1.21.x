package com.delta.redecoratethefuture.item;

import com.delta.redecoratethefuture.RedecorateTheFuture;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RedecorateTheFuture.MODID);

    public static final DeferredItem<Item> COMPOSITE = ITEMS.register("composite",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}