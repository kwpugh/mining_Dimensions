package com.kwpugh.mining_dims;

import com.kwpugh.mining_dims.config.MiningDimsConfig;
import com.kwpugh.mining_dims.init.EnchantmentInit;
import com.kwpugh.mining_dims.init.ItemInit;
import com.kwpugh.mining_dims.init.MiningDimsRegistry;
import com.kwpugh.mining_dims.init.PortalInit;
import com.kwpugh.mining_dims.world.OreGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

public class MiningDims implements ModInitializer
{
	public static final String MOD_ID = "mining_dims";
    public static final ItemGroup MINING_DIMS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "mining_dims_group"), () -> new ItemStack(ItemInit.MINING_TELEPORTER));

    public static final Identifier MOD_DIMENSION_ID = new Identifier(MiningDims.MOD_ID, "mining_dim");
    public static final Identifier MOD_DIMENSION2_ID = new Identifier(MiningDims.MOD_ID, "hunting_dim");
    public static final Identifier MOD_DIMENSION3_ID = new Identifier(MiningDims.MOD_ID, "caving_dim");
    public static final Identifier MOD_DIMENSION4_ID = new Identifier(MiningDims.MOD_ID, "nethering_dim");
    public static final Identifier MOD_DIMENSION5_ID = new Identifier(MiningDims.MOD_ID, "climbing_dim");

    public static final MiningDimsConfig CONFIG = AutoConfig.register(MiningDimsConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();

    @Override
    public void onInitialize()
    {
        PortalInit.registerPortal();
        MiningDimsRegistry.setupDimension();
        ItemInit.init();
        EnchantmentInit.registerEnchantments();
        OreGen.Features();
    }	
}