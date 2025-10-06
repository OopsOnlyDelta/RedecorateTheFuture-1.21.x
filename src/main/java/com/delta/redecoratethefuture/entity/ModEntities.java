package com.delta.redecoratethefuture.entity;

import com.delta.redecoratethefuture.RedecorateTheFuture;
import com.delta.redecoratethefuture.entity.custom.SeatEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, RedecorateTheFuture.MODID);

    public static final Supplier<EntityType<SeatEntity>> SEAT_ENTITY = ENTITY_TYPES.register("seat_entity", () -> EntityType.Builder.of(SeatEntity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f).build("seat_entity"));

    public static void register (IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}