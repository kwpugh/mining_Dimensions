package com.kwpugh.mining_dims.world;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.config.MiningDimsConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.function.Predicate;


public class MiningDimsOrePlacedFeature
{
    public static final MiningDimsConfig.General CONFIG = MiningDims.CONFIG.GENERAL;

    public static final RegistryEntry<PlacedFeature> ORE_EXTRA_DIAMOND_DEEPSLATE;
    public static final RegistryEntry<PlacedFeature> ORE_EXTRA_ANCIENT_DEBRIS;


    static
    {
        ORE_EXTRA_DIAMOND_DEEPSLATE = PlacedFeatures.register("ore_extra_diamond_deepslate",
                MiningDimsOreConfiguredFeature.ORE_EXTRA_DIAMOND_DEEPSLATE,
                modifiersWithCount(CONFIG.extraDiamondDeepslateVeinsPerChunk,
                HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.extraDiamondDeepslateMaxHeight)) ));

        ORE_EXTRA_ANCIENT_DEBRIS = PlacedFeatures.register("ore_extra_ancient_debris",
                MiningDimsOreConfiguredFeature.ORE_EXTRA_ANCIENT_DEBRIS,
                modifiersWithCount(CONFIG.extraAncientDebrisVeinsPerChunk,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(0),YOffset.fixed(CONFIG.extraAncientDebrisMaxHeight)) ));
    }

    public static void init()
    {
        RegistryKey<PlacedFeature> extraDiamondDeepslate = ORE_EXTRA_DIAMOND_DEEPSLATE.getKey().get();
        RegistryKey<PlacedFeature> extraAncientDebris = ORE_EXTRA_ANCIENT_DEBRIS.getKey().get();

        // insert into biomes using biome tags
        BiomeModifications.addFeature(overworldSelector(), GenerationStep.Feature.UNDERGROUND_ORES, extraDiamondDeepslate);
        BiomeModifications.addFeature(netherSelector(), GenerationStep.Feature.UNDERGROUND_ORES, extraAncientDebris);
    }

    public static Predicate<BiomeSelectionContext> overworldSelector()
    {
        return context -> context.getBiomeRegistryEntry().isIn(BiomeTags.IS_OVERWORLD);
    }

    public static Predicate<BiomeSelectionContext> netherSelector()
    {
        return context -> context.getBiomeRegistryEntry().isIn(BiomeTags.IS_NETHER);
    }

    // Used here because the vanilla ones are private
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier)
    {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier)
    {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier)
    {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }

}
