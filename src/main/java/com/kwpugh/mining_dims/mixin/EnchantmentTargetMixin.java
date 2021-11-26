package com.kwpugh.mining_dims.mixin;

import com.kwpugh.mining_dims.items.BaseTeleporter;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// BREAKABLE enchantment target
@Mixin(targets = "net/minecraft/enchantment/EnchantmentTarget$2")
public class EnchantmentTargetMixin
{
    @Inject(method = "Lnet/minecraft/enchantment/EnchantmentTarget$2;isAcceptableItem(Lnet/minecraft/item/Item;)Z", at = @At("HEAD"), cancellable = true)
    private void miningdimsIsAcceptableItem(Item item, CallbackInfoReturnable<Boolean> cir)
    {
        if (item instanceof BaseTeleporter)
        {
            cir.setReturnValue(true);
        }
    }
}