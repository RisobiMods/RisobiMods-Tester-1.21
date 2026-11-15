package net.risobimods.risobimobs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.risobimods.risobimobs.entity.custom.ScaryEntity;

public class ScaryModel <T extends ScaryEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("risobimobs", "scary"), "main");
    private final ModelPart Body;
    private final ModelPart LeftArm;
    private final ModelPart RightArm;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;
    private final ModelPart Head;
    private final ModelPart Neck;
    private final ModelPart Torso;

    public ScaryModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.LeftArm = this.Body.getChild("Left Arm");
        this.RightArm = this.Body.getChild("Right Arm");
        this.LeftLeg = this.Body.getChild("Left Leg");
        this.RightLeg = this.Body.getChild("Right Leg");
        this.Head = this.Body.getChild("Head");
        this.Neck = this.Body.getChild("Neck");
        this.Torso = this.Body.getChild("Torso");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition LeftArm = Body.addOrReplaceChild("Left Arm", CubeListBuilder.create(), PartPose.offset(3.0F, -28.0F, 0.0F));

        PartDefinition cube_r1 = LeftArm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-2.0F, -26.0F, 0.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 19.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition RightArm = Body.addOrReplaceChild("Right Arm", CubeListBuilder.create(), PartPose.offset(-3.0F, -28.0F, 0.0F));

        PartDefinition cube_r2 = RightArm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, -26.0F, 0.0F, 2.0F, 26.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition LeftLeg = Body.addOrReplaceChild("Left Leg", CubeListBuilder.create().texOffs(24, 14).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -14.0F, 0.0F));

        PartDefinition RightLeg = Body.addOrReplaceChild("Right Leg", CubeListBuilder.create().texOffs(8, 22).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -14.0F, 0.0F));

        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -13.0F, -1.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -44.0F, 0.0F));

        PartDefinition Neck = Body.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(16, 28).addBox(-1.0F, -15.0F, 0.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

        PartDefinition Torso = Body.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -15.0F, -1.0F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(ScaryEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animate(entity.idleAnimationState, ScaryAnimations.ANIM_SCARY_IDLE, ageInTicks, 1);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.Head.yRot = headYaw * ((float)Math.PI / 180f);
        this.Head.xRot = headPitch * ((float)Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return Body;
    }
}
