package com.kwpugh.mining_dims.world;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.config.MiningDimsConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class MiningDimsOreConfiguredFeature
{
    public static final MiningDimsConfig.General CONFIG = MiningDims.CONFIG.GENERAL;

    // OVERWORLD ORES
    public static final ConfiguredFeature<?, ?> ORE_EXTRA_DIAMOND_DEEPSLATE = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState(), CONFIG.extraDiamondDeepslateVeinSize));

    // NETHER ORES
    public static final ConfiguredFeature<?, ?> ORE_EXTRA_ANCIENT_DEBRIS = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_NETHER, Blocks.ANCIENT_DEBRIS.getDefaultState(), CONFIG.extraAncientDebrisVeinSize));


	public static void init()
    {
        // OVERWORLD ORES
        RegistryKey<ConfiguredFeature<?, ?>> extraDiamondDeepslate = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MiningDims.MOD_ID, "ore_extra_diamond_deepslate"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, extraDiamondDeepslate.getValue(), ORE_EXTRA_DIAMOND_DEEPSLATE);

        // NETHER ORES
        RegistryKey<ConfiguredFeature<?, ?>> extraAncientDebris = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MiningDims.MOD_ID, "ore_extra_ancient_debris"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, extraAncientDebris.getValue(), ORE_EXTRA_ANCIENT_DEBRIS);
    }
}
