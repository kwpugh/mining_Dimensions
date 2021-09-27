package com.kwpugh.mining_dims.mixin;

import com.kwpugh.mining_dims.world.OreGen;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin
{
    // Inject extra diamond ore into Overworld
    @Inject(method = "addDefaultOres",  at = @At(value = "TAIL"))
    private static void miningdimsAddDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci)
    {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGen.ORE_EXTRA_DIAMOND_DEEPSLATE);
    }
}