package com.kwpugh.mining_dims.mixin;

import com.kwpugh.mining_dims.util.TeleporterAnvilAction;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin
{
    @Shadow @Final private Property levelCost;
    @Shadow private String newItemName;

    @Inject(method = "canTakeOutput", at = @At("HEAD"), cancellable = true)
    public void miningdimsCanTakeOutput(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir)
    {
        ForgingScreenHandlerAccessor accessor = (ForgingScreenHandlerAccessor) (Object) this;

        // Logic will be skipped if a ruby is not in second slot
        if(accessor.getInput().getStack(1).getItem().equals(Items.BLUE_BED))
        {
            cir.setReturnValue(levelCost.get() <= player.experienceLevel);
            cir.cancel();
        }
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    public void miningdimsUpdateResult(CallbackInfo ci)
    {
        ForgingScreenHandlerAccessor accessor = (ForgingScreenHandlerAccessor) this;

        TeleporterAnvilAction.anvilAction(accessor, levelCost,  newItemName, ci);
    }
}
