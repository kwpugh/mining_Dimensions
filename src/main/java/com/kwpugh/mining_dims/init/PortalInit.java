package com.kwpugh.mining_dims.init;

import com.kwpugh.mining_dims.MiningDims;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class PortalInit
{
	static boolean mining = MiningDims.CONFIG.GENERAL.enableMiningPortal;
	static boolean hunting = MiningDims.CONFIG.GENERAL.enableHuntingPortal;
	static boolean caving = MiningDims.CONFIG.GENERAL.enableCavingPortal;
	static boolean nethering = MiningDims.CONFIG.GENERAL.enableNetheringPortal;
	static boolean climbing = MiningDims.CONFIG.GENERAL.enableClimbingPortal;

    public static void registerPortal()
    {
    	//  50, 133, 168 = light blue portal color
    	//  13, 130, 21 = green portal
		//  28, 27, 31 = black portal
		//  235, 52, 55 = red portal
		//  235, 208, 52 = yellowish portal

		if(mining)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(Blocks.COBBLESTONE)
					.destDimID(new Identifier(MiningDims.MOD_ID, "mining_dim"))
					.tintColor(50, 133, 168)
					.registerPortal();
		}

		if(hunting)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(Blocks.OAK_LOG)
					.destDimID(new Identifier(MiningDims.MOD_ID, "hunting_dim"))
					.tintColor(13, 130, 21)
					.registerPortal();
		}

		if(caving)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(Blocks.DIORITE)
					.destDimID(new Identifier(MiningDims.MOD_ID, "caving_dim"))
					.tintColor(28, 27, 31)
					.registerPortal();
		}

		if(nethering)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(Blocks.BASALT)
					.destDimID(new Identifier(MiningDims.MOD_ID, "nethering_dim"))
					.tintColor(235, 52, 55)
					.registerPortal();
		}

		if(climbing)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(Blocks.SNOW_BLOCK)
					.destDimID(new Identifier(MiningDims.MOD_ID, "climbing_dim"))
					.tintColor(235, 208, 52)
					.registerPortal();
		}
    }
}
