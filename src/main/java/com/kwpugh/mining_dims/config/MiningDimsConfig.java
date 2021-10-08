package com.kwpugh.mining_dims.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "mining_dims")
public class MiningDimsConfig extends PartitioningSerializer.GlobalData
{
    public General GENERAL = new General();

    @Config(name = "general")
    public static class General implements ConfigData {
        @Comment("\n"
                + "\n"
                + "***********************"
                + "\nPortals"
                + "\n***********************")
        public boolean enableMiningPortal = true;
        public boolean enableHuntingPortal = true;
        public boolean enableCavingPortal = true;
        public boolean enableNetheringPortal = true;
        public boolean enableClimbingPortal = true;

        @Comment("\n"
                + "\n"
                + "***********************"
                + "\nPortable Teleporters"
                + "\n***********************")
        public boolean enableMiningTeleporter = true;
        public boolean enableHuntingTeleporter = true;
        public boolean enableCavingTeleporter = true;
        public boolean enableNetheringTeleporter = true;
        public boolean enableClimbingTeleporter = true;
        public boolean enableReturningEnchantment = true;

        @Comment("\n"
                + "\n"
                + "***********************"
                + "\nTeleporters"
                + "\n * disable check and place player"
                + "\n possibly in middle of an ocean on"
                + "\n a stone block."
                + "\n * be sure to be carrying a boat!"
                + "\n***********************")
        public boolean enableOceanCheck = true;

        @Comment("\n"
                + "\n"
                + "***********************"
                + "\nExtra Overworld Deepslate Ores"
                + "\n (Also affects the Mining, Climbing, & Caving Dims)"
                + "\n***********************")
        public int extraDiamondDeepslateVeinSize = 3;
        public int extraDiamondDeepslateVeinsPerChunk = 8;
        public int extraDiamondDeepslateMaxHeight = -50;
    }
}