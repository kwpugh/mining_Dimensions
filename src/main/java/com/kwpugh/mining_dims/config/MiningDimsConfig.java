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
        public boolean enableMining = true;
        public boolean enableHunting = true;
        public boolean enableCaving = true;
        public boolean enableNethering = true;

        @Comment("\n"
                + "\n"
                + "***********************"
                + "\nPortable Teleporters"
                + "\n***********************")
        public boolean enableMiningTeleporter = true;
        public boolean enableHuntingTeleporter = true;
        public boolean enableCavingTeleporter = true;
        public boolean enableNetheringTeleporter = true;
    }
}