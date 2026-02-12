package net.kindling.senesence.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CritEffectItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.kindling.senesence.impl.Senesence;
import net.kindling.senesence.impl.entity.CleaverEntity;
import net.kindling.senesence.impl.index.*;
import net.kindling.senesence.impl.index.data.SenesenceDamageTypes;
import net.kindling.senesence.impl.index.data.SenesenceEnchantments;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import net.kindling.senesence.impl.util.item.SenesenceItemUtils;

public class AuburnCleaverItem extends AxeItem implements ColorableItem, CustomHitSoundItem, CustomKillSourceItem, CritEffectItem, ModelVaryingItem {
    public int startColor(ItemStack itemStack) {return 0xFF401216;}
    public int endColor(ItemStack itemStack) {return 0xFFdd6544;}
    public int backgroundColor(ItemStack itemStack) {return 0xF0130c0b;}

//     public int startColor(ItemStack itemStack) {return SenesenceItemUtils.startColor;}
//    public int endColor(ItemStack itemStack) {return SenesenceItemUtils.endColor;}
//    public int backgroundColor(ItemStack itemStack) {return SenesenceItemUtils.backgroundColor;}



    public AuburnCleaverItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings
                .component(SenesenceDataComponents.CLEAVER_SEEDS, 0)
                .component(SenesenceDataComponents.SPLINTERED, false)
                .component(SenesenceDataComponents.THROWN_CLEAVER, false)
        );
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
        float strength = 1.0F;
        World world = playerEntity.getWorld();
        boolean b = EnchantmentHelper.hasAnyEnchantmentsWith(itemStack, SenesenceEnchantmentEffects.HARVEST);
        var component = SenesenceDataComponents.CLEAVER_SEEDS;

        if (!b) {
            playerEntity.setVelocity(playerEntity.getRotationVec(0).multiply(strength));
            playerEntity.velocityModified = true;
        } else {
            if (itemStack.getOrDefault(component, 0) < 5) {
                itemStack.set(component, itemStack.getOrDefault(component, 0) + 1);
                playerEntity.playSoundToPlayer(SoundEvents.BLOCK_MANGROVE_ROOTS_BREAK, SoundCategory.PLAYERS, 1, 1);

                if (itemStack.getOrDefault(component, 0) == 5) {
                    itemStack.set(component, 0);
                }
            }
        }

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

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var component = SenesenceDataComponents.SPLINTERED;
        ItemStack stack = user.getStackInHand(hand);
        boolean hasSplinter = EnchantmentHelper.hasAnyEnchantmentsWith(stack, SenesenceEnchantmentEffects.SPLINTER);

        if (hasSplinter) {
            if (stack.getOrDefault(component, false) == false) {
                if (world instanceof ServerWorld serverWorld) {
                    CleaverEntity cleaver = new CleaverEntity(SenesenceEntities.CLEAVER, serverWorld);

                    cleaver.setPosition(user.getX(), user.getEyeY() - 0.10000000149011612, user.getZ());

                    cleaver.setVelocity(user, user.getPitch(), user.getHeadYaw(), 0.0F, 2.5F, 0.0F);
                    cleaver.setPitch(user.getPitch());
                    cleaver.setYaw(user.getHeadYaw());
                    cleaver.setOwner(user);

                    serverWorld.spawnEntity(cleaver);
                    stack.set(component, true);
                }
            }
        }
        return super.use(world, user, hand);
    }

    // enchantment
    public int getItemBarStep(ItemStack stack) {
        return Math.round((float) stack.getOrDefault(SenesenceDataComponents.CLEAVER_SEEDS, 0) / 4 * 13);
    }

    public int getItemBarColor(ItemStack stack) {
        return 0xFFdd6544;
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return EnchantmentHelper.hasAnyEnchantmentsWith(stack, SenesenceEnchantmentEffects.HARVEST);
    }

    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getItem() != newStack.getItem();
    }

    @Override
    public Identifier getModel(ModelTransformationMode modelTransformationMode, ItemStack itemStack, @Nullable LivingEntity livingEntity) {
        if (itemStack.getOrDefault(SenesenceDataComponents.SPLINTERED, false)) {
            return MiscUtils.isGui(modelTransformationMode) ? Senesence.id("auburn_cleaver") : Senesence.id("splintered_cleaver_handheld");
        }
        if (itemStack.getOrDefault(SenesenceDataComponents.THROWN_CLEAVER, false)) {
            return MiscUtils.isGui(modelTransformationMode) ? Senesence.id("auburn_cleaver") : Senesence.id("thrown_cleaver_handheld");
        }
        return MiscUtils.isGui(modelTransformationMode) ? Senesence.id("auburn_cleaver") : Senesence.id("auburn_cleaver_handheld");
    }

    @Override
    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Senesence.id("auburn_cleaver"),
                Senesence.id("auburn_cleaver_handheld"),
                Senesence.id("splintered_cleaver_handheld"),
                Senesence.id("thrown_cleaver_handheld")
        );
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());

        if (state.isOf(Blocks.ANVIL) || state.isOf(Blocks.SMITHING_TABLE)) {
            if (player != null) {
                player.swingHand(player.getActiveHand());

                ItemStack stack = player.getStackInHand(player.getActiveHand());
                if (stack.isOf(SenesenceItems.AUBURN_CLEAVER) && stack.getOrDefault(SenesenceDataComponents.SPLINTERED, false) == true) {
                    stack.set(SenesenceDataComponents.SPLINTERED, false);
                }
            }
        }

        return super.useOnBlock(context);
    }
}