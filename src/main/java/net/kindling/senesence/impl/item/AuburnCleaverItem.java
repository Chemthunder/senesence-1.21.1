package net.kindling.senesence.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CritEffectItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.kindling.senesence.impl.index.SenesenceParticles;
import net.kindling.senesence.impl.index.data.SenesenceDamageTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class AuburnCleaverItem extends AxeItem implements ColorableItem, CustomHitSoundItem, CustomKillSourceItem, CritEffectItem {
    public int startColor(ItemStack itemStack) {return 0xFF4b2c33;}
    public int endColor(ItemStack itemStack) {return 0xFF6f4235;}
    public int backgroundColor(ItemStack itemStack) {return 0xF02d172b;}

    public AuburnCleaverItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 8.0F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .build();
    }

    public void playHitSound(PlayerEntity playerEntity, Entity entity) {
        playerEntity.playSound(SoundEvents.BLOCK_MUDDY_MANGROVE_ROOTS_BREAK, 1, 0.2f);
    }

    public DamageSource getKillSource(LivingEntity livingEntity) {
        return livingEntity.getDamageSources().create(SenesenceDamageTypes.BURY);
    }

    public void critEffect(PlayerEntity playerEntity, ItemStack itemStack, Entity entity) {
        World world = playerEntity.getWorld();

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(SenesenceParticles.AUBURN_LEAF,
                    entity.getX() + 0.5f,
                    entity.getY() + 1.0f,
                    entity.getZ() + 0.5f,
                    4,
                    0,
                    0,
                    0,
                    0.1
            );
        }
    }
}