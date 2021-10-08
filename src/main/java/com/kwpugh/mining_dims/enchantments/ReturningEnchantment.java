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

    public int getMinPower(int level)
    {
        return 20;
    }

    public int getMaxPower(int level)
    {
        return 50;
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    /*
     * Makes the enchant only available
     *  from Village librarian
     */
    @Override
    public boolean isAvailableForEnchantedBookOffer()
    {
        return true;
    }

    /*
     * This excludes enchant from enchanting table
     * and loot
     */
    @Override
    public boolean isAvailableForRandomSelection()
    {
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