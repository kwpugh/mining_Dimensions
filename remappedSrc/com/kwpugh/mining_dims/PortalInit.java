package com.kwpugh.mining_dims;

import com.kwpugh.mining_dims.MiningDims;

import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class PortalInit
{
	static boolean mining = MiningDims.CONFIG.GENERAL.enableMining;
	static boolean hunting = MiningDims.CONFIG.GENERAL.enableHunting;
	static boolean caving = MiningDims.CONFIG.GENERAL.enableCaving;
	static boolean nethering = MiningDims.CONFIG.GENERAL.enableNethering;

    public static void registerPortal()
    {
    	//  50, 133, 168 = light blue portal color
    	//  13, 130, 21 = green portal
		//  28, 27, 31 = black portal
		//  235, 52, 55 = red portal

    	if(mining)
    	{
    		CustomPortalApiRegistry.addPortal(Blocks.COBBLESTONE, new Identifier(MiningDims.MOD_ID, "mining_dim"), 50, 133, 168);
    	}

    	if(hunting)
    	{
    		CustomPortalApiRegistry.addPortal(Blocks.OAK_LOG, new Identifier(MiningDims.MOD_ID, "hunting_dim"), 13, 130, 21);
    	}

		if(caving)
		{
			CustomPortalApiRegistry.addPortal(Blocks.DIORITE, new Identifier(MiningDims.MOD_ID, "caving_dim"), 28, 27, 31);
		}

		if(nethering)
		{
			CustomPortalApiRegistry.addPortal(Blocks.BASALT, new Identifier(MiningDims.MOD_ID, "nethering_dim"), 235, 52, 55);
		}
    }
}
