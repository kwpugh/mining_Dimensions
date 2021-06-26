package com.kwpugh.mining_dims;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

public class MiningDims implements ModInitializer
{
	public static final String MOD_ID = "mining_dims";
    public static final MiningDims INSTANCE = new MiningDims();

    public static final Identifier MOD_DIMENSION_ID = new Identifier(MiningDims.MOD_ID, "mining_dim");
    public static final Identifier MOD_DIMENSION2_ID = new Identifier(MiningDims.MOD_ID, "hunting_dim");
    public static final Identifier MOD_DIMENSION3_ID = new Identifier(MiningDims.MOD_ID, "caving_dim");
    public static final Identifier MOD_DIMENSION4_ID = new Identifier(MiningDims.MOD_ID, "nethering_dim");

    public static final MiningDimsConfig CONFIG = AutoConfig.register(MiningDimsConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();

    @Override
    public void onInitialize()
    {
        PortalInit.registerPortal();
        MiningDimsRegistry.setupDimension();
    }	
}