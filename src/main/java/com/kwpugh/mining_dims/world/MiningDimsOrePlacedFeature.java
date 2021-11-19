package com.kwpugh.mining_dims.world;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.config.MiningDimsConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;

public class MiningDimsOrePlacedFeature
{
    public static final MiningDimsConfig.General CONFIG = MiningDims.CONFIG.GENERAL;

    // OVERWORLD ORES
    public static final PlacedFeature ORE_EXTRA_DIAMOND_DEEPSLATE = MiningDimsOreConfiguredFeature.ORE_EXTRA_DIAMOND_DEEPSLATE.withPlacement(modifiersWithCount(CONFIG.extraDiamondDeepslateVeinsPerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(-64),YOffset.fixed(CONFIG.extraDiamondDeepslateMaxHeight))));

    // NETHER ORES
    public static final PlacedFeature ORE_EXTRA_ANCIENT_DEBRIS = MiningDimsOreConfiguredFeature.ORE_EXTRA_ANCIENT_DEBRIS.withPlacement(modifiersWithCount(CONFIG.extraAncientDebrisVeinsPerChunk, HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.extraAncientDebrisMaxHeight))));


    public static void init()
    {
        // OVERWORLD ORES
        RegistryKey<PlacedFeature> extraDiamondDeepslate = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MiningDims.MOD_ID, "ore_extra_diamond_deepslate"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, extraDiamondDeepslate.getValue(), ORE_EXTRA_DIAMOND_DEEPSLATE);

        // NETHER ORES
        RegistryKey<PlacedFeature> extraAncientDebris = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MiningDims.MOD_ID, "ore_extra_ancient_debris"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, extraAncientDebris.getValue(), ORE_EXTRA_ANCIENT_DEBRIS);


        if(CONFIG.enableExtraDiamond)
        {
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, extraDiamondDeepslate);
        }

        if(CONFIG.enableExtraAncientDebris)
        {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, extraAncientDebris);
        }
    }


    // Just in here until accessors are added to Fabric
    private static List<PlacementModifier> modifiers(PlacementModifier first, PlacementModifier second)
    {
        return List.of(first, SquarePlacementModifier.of(), second, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier modifier)
    {
        return modifiers(CountPlacementModifier.of(count), modifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier modifier)
    {
        return modifiers(RarityFilterPlacementModifier.of(chance), modifier);
    }
}
