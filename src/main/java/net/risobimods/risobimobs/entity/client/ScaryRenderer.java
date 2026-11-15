package net.risobimods.risobimobs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.risobimods.risobimobs.RisobiMobs;
import net.risobimods.risobimobs.entity.custom.ScaryEntity;

public class ScaryRenderer extends MobRenderer<ScaryEntity, ScaryModel<ScaryEntity>> {

    public ScaryRenderer(EntityRendererProvider.Context context) {
        super(context, new  ScaryModel<>(context.bakeLayer(ScaryModel.LAYER_LOCATION)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ScaryEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(RisobiMobs.MOD_ID, "textures/entity/scary/scary.png");
    }

    @Override
    public void render(ScaryEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {


        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
