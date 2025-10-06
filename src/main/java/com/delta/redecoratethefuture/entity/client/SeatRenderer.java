package com.delta.redecoratethefuture.entity.client;


import com.delta.redecoratethefuture.entity.custom.SeatEntity;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SeatRenderer  extends EntityRenderer<SeatEntity> {
    public SeatRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(SeatEntity seatEntity) {
        return null;
    }

    @Override
    public boolean shouldRender(SeatEntity livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }
}
