package com.kwpugh.mining_dims.init;

import com.kwpugh.mining_dims.MiningDims;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
		//  233, 171, 255 = purple'ish portal

		if(mining)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(BlockInit.MINING_PORTAL_BLOCK)
					.lightWithItem(ItemInit.FLINT_AND_DIAMOND)
					.destDimID(new Identifier(MiningDims.MOD_ID, "mining_dim"))
					.tintColor(50, 133, 168)
					.registerPortal();
		}

		if(hunting)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(BlockInit.HUNTING_PORTAL_BLOCK)
					.lightWithItem(ItemInit.FLINT_AND_DIAMOND)
					.destDimID(new Identifier(MiningDims.MOD_ID, "hunting_dim"))
					.tintColor(13, 130, 21)
					.registerPortal();
		}

		if(caving)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(BlockInit.CAVING_PORTAL_BLOCK)
					.lightWithItem(ItemInit.FLINT_AND_DIAMOND)
					.destDimID(new Identifier(MiningDims.MOD_ID, "caving_dim"))
					.tintColor(28, 27, 31)
					.registerPortal();
		}

		if(nethering)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(BlockInit.NETHERING_PORTAL_BLOCK)
					.lightWithItem(ItemInit.FLINT_AND_DIAMOND)
					.destDimID(new Identifier(MiningDims.MOD_ID, "nethering_dim"))
					.tintColor(235, 52, 55)
					.registerPortal();
		}

		if(climbing)
		{
			CustomPortalBuilder.beginPortal()
					.frameBlock(BlockInit.CLIMBING_PORTAL_BLOCK)
					.lightWithItem(ItemInit.FLINT_AND_DIAMOND)
					.destDimID(new Identifier(MiningDims.MOD_ID, "climbing_dim"))
					.tintColor(233, 171, 255)
					.registerPortal();
		}
    }
}
