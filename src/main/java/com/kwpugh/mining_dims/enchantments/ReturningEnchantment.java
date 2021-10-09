package com.kwpugh.mining_dims.enchantments;

import com.kwpugh.mining_dims.init.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ReturningEnchantment extends Enchantment
{
    public ReturningEnchantment(Enchantment.Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes)
    {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinPower(int level)
    {
        return 1 + 10 * (level - 1);
    }

    @Override
    public int getMaxPower(int level)
    {
        return super.getMinPower(level) + 50;
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack)
    {
        Item item = stack.getItem();
        if(item == ItemInit.MINING_TELEPORTER ||
            item == ItemInit.CLIMBING_TELEPORTER ||
                item == ItemInit.CAVING_TELEPORTER ||
                item == ItemInit.HUNTING_TELEPORTER ||
                item == ItemInit.NETHERING_TELEPORTER)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}