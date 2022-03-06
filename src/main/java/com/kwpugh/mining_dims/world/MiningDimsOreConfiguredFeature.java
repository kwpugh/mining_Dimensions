package com.kwpugh.mining_dims.world;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.config.MiningDimsConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import java.util.List;

public class MiningDimsOreConfiguredFeature
{
    public static final MiningDimsConfig.General CONFIG = MiningDims.CONFIG.GENERAL;

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_EXTRA_DIAMOND_DEEPSLATE;
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_EXTRA_ANCIENT_DEBRIS;


    static
    {
        ORE_EXTRA_DIAMOND_DEEPSLATE = ConfiguredFeatures.register("ore_extra_diamond_deepslate",
                Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState())), CONFIG.extraDiamondDeepslateVeinSize));

        ORE_EXTRA_ANCIENT_DEBRIS = ConfiguredFeatures.register("ore_extra_ancient_debris",
                Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                Blocks.ANCIENT_DEBRIS.getDefaultState())), CONFIG.extraAncientDebrisVeinSize));
    }

    public static void init()
    {
        // Force class loading
    }
}
