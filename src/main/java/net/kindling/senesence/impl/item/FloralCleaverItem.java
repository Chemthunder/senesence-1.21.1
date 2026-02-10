package net.kindling.senesence.impl.item;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class FloralCleaverItem extends AxeItem {
    public FloralCleaverItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 8.0F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .build();
    }
}