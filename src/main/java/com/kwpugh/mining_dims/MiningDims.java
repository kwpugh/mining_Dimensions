package com.kwpugh.mining_dims;

import com.kwpugh.mining_dims.config.MiningDimsConfig;
import com.kwpugh.mining_dims.init.*;
import com.kwpugh.mining_dims.world.MiningDimsOreConfiguredFeature;
import com.kwpugh.mining_dims.world.MiningDimsOrePlacedFeature;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MiningDims implements ModInitializer
{
	public static final String MOD_ID = "mining_dims";
    public static final ItemGroup MINING_DIMS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "mining_dims_group"), () -> new ItemStack(ItemInit.MINING_TELEPORTER));

    public static final Identifier MOD_DIMENSION_ID = new Identifier(MiningDims.MOD_ID, "mining_dim");  // 384 -64
    public static final Identifier MOD_DIMENSION2_ID = new Identifier(MiningDims.MOD_ID, "hunting_dim"); // 384 -64
    public static final Identifier MOD_DIMENSION3_ID = new Identifier(MiningDims.MOD_ID, "caving_dim");  // 384 -64
    public static final Identifier MOD_DIMENSION4_ID = new Identifier(MiningDims.MOD_ID, "nethering_dim"); // 128 0
    public static final Identifier MOD_DIMENSION5_ID = new Identifier(MiningDims.MOD_ID, "climbing_dim");  // 384 -64

    public static final MiningDimsConfig CONFIG = AutoConfig.register(MiningDimsConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();

    @Override
    public void onInitialize()
    {
        BlockInit.registerBlocks();
        BlockInit.registerBlockItems();
        ItemInit.init();
        PortalInit.registerPortal();
        MiningDimsRegistry.setupDimension();
        EnchantmentInit.registerEnchantments();
        MiningDimsOreConfiguredFeature.init();
        MiningDimsOrePlacedFeature.init();
    }	
}