package com.kwpugh.mining_dims.world;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.config.MiningDimsConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class OreGen
{
    private static final MiningDimsConfig.General CONFIG = MiningDims.CONFIG.GENERAL;

    public static ConfiguredFeature<?, ?> ORE_EXTRA_DIAMOND_DEEPSLATE = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES,
                    Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState(),
                    CONFIG.extraDiamondDeepslateVeinSize)) // vein size
            .range(new RangeDecoratorConfig(
                    UniformHeightProvider.create(YOffset.getBottom(), YOffset.fixed(CONFIG.extraDiamondDeepslateMaxHeight))))
            .spreadHorizontally()
            .repeat(CONFIG.extraDiamondDeepslateVeinsPerChunk); // number of veins per chunk


    public static void Features()
    {
        RegistryKey<ConfiguredFeature<?, ?>> extraDiamondDeepslate = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(MiningDims.MOD_ID, "ore_extra_diamond_deepslate"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, extraDiamondDeepslate.getValue(), ORE_EXTRA_DIAMOND_DEEPSLATE);
    }
}