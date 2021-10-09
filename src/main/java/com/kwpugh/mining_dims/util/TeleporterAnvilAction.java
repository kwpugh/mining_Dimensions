package com.kwpugh.mining_dims.util;

import com.kwpugh.mining_dims.init.EnchantmentInit;
import com.kwpugh.mining_dims.mixin.ForgingScreenHandlerAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.Property;
import net.minecraft.text.LiteralText;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class TeleporterAnvilAction
{
    public static void anvilAction(ForgingScreenHandlerAccessor accessor, Property levelCost, String newItemName, CallbackInfo ci)
    {
        // Logic will be skipped if a bed is not in second slot
        if(accessor.getInput().getStack(1).getItem().equals(Items.WHITE_BED))
        {
            ItemStack result = accessor.getInput().getStack(0).copy();
            result.addEnchantment(EnchantmentInit.RETURNING, 0);

            if (!StringUtils.isBlank(newItemName))
            {
                result.setCustomName(new LiteralText(newItemName));
            }
            else
            {
                result.removeCustomName();
            }

            accessor.getOutput().setStack(0, result);
            levelCost.set(30);
            ci.cancel();
        }
    }
}
