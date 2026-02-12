package net.kindling.senesence.impl.client.entity;

import net.kindling.senesence.impl.entity.CleaverEntity;
import net.kindling.senesence.impl.index.SenesenceDataComponents;
import net.kindling.senesence.impl.index.SenesenceItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class CleaverEntityRenderer extends EntityRenderer<CleaverEntity> {
    public CleaverEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public Identifier getTexture(CleaverEntity entity) {
        return null;
    }

    public void render(CleaverEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        var render = MinecraftClient.getInstance().getItemRenderer();
        int rotationSpeed = 50;
        ItemStack stack = SenesenceItems.AUBURN_CLEAVER.getDefaultStack();
        stack.set(SenesenceDataComponents.THROWN_CLEAVER, true);


        matrices.push();
        matrices.translate(0, 0.8, 0);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((entity.age + tickDelta) * -rotationSpeed));

        render.renderItem(
                stack,
                ModelTransformationMode.THIRD_PERSON_RIGHT_HAND,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                -1
        );

        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
