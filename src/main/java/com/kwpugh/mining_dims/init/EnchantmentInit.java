package com.kwpugh.mining_dims.init;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.enchantments.ReturningEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EnchantmentInit
{
    public static final Enchantment RETURNING = new ReturningEnchantment(Enchantment.Rarity.COMMON, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});

    public static void registerEnchantments()
    {
        Registry.register(Registries.ENCHANTMENT, new Identifier(MiningDims.MOD_ID, "returning"), RETURNING);
    }
}