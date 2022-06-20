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
                + "\nExtra Overworld Deepslate Ores"
                + "\n (Also affects the Mining, Climbing, & Caving Dims)"
                + "\n***********************")
        public int extraDiamondDeepslateVeinSize = 3;
        public int extraDiamondDeepslateVeinsPerChunk = 8;
        public int extraDiamondDeepslateMaxHeight = -50;

        @Comment("\n"
                + "\n"
                + "***********************"
                + "\nExtra Nether Ores"
                + "\n (Also affects the Nethering Dim)"
                + "\n***********************")
        public int extraAncientDebrisVeinSize = 3;
        public int extraAncientDebrisVeinsPerChunk = 8;
        public int extraAncientDebrisMaxHeight = 40;

        @Comment("\n"
                + "\n"
                + "***********************"
                + "\nHunting Dim Mobs"
                + "\n - settings for mob attributes"
                + "\n***********************")
        public double zombieMaxHealth = 40.0D;
        public double zombieAttackDamageBonus = 6.0D;
        public double zombieArmorBonus = 8.0D;
        public double zombieMovementBonus = 0.050D;
        public double piglinMaxHealth = 40.0D;
        public double piglinAttackDamageBonus = 20.0D;
        public double piglinArmorBonus = 20.0D;
        public double piglinMovementBonus = 0.050D;
        public double piglinBruteMaxHealth = 60.0D;
        public double piglinBruteAttackDamageBonus = 20.0D;
        public double piglinBruteArmorBonus = 20.0D;
        public double piglinBruteMovementBonus = 0.0750D;
        public double witherSkeletonMaxHealth = 80.0D;
        public double witherSkeletonDamageBonus = 20.0D;
        public double witherSkeletonArmorBonus = 20.0D;
        public double witherSkeletonMovementBonus = 0.0850D;

        @Comment("\n"
                + "\n"
                + "***********************"
                + "\nHunting Dim Mobs"
                + "\n - settings for armor/weapon"
                + "\n***********************")
        public boolean enableZombieGear = true;
        public boolean enablePiglinGear = true;
        public boolean enablePiglinBruteGear = true;
        public boolean enableWitherSkeletonGear = true;
        public boolean enableVexGear = true;
        public double gearDropChannce = 0.10;
    }
}