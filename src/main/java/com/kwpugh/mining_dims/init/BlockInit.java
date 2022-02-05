package com.kwpugh.mining_dims.init;

import com.kwpugh.mining_dims.MiningDims;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit
{
    public static final Block MINING_PORTAL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
    public static final Block HUNTING_PORTAL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
    public static final Block CAVING_PORTAL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
    public static final Block NETHERING_PORTAL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));
    public static final Block CLIMBING_PORTAL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));

    public static void registerBlocks()
    {
        Registry.register(Registry.BLOCK, new Identifier("mining_dims", "mining_portal_block"), MINING_PORTAL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("mining_dims", "hunting_portal_block"), HUNTING_PORTAL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("mining_dims", "caving_portal_block"), CAVING_PORTAL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("mining_dims", "nethering_portal_block"), NETHERING_PORTAL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("mining_dims", "climbing_portal_block"), CLIMBING_PORTAL_BLOCK);
    }

    public static void registerBlockItems()
    {
        Registry.register(Registry.ITEM, new Identifier("mining_dims", "mining_portal_block"), new BlockItem(MINING_PORTAL_BLOCK, new Item.Settings().group(MiningDims.MINING_DIMS_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("mining_dims", "hunting_portal_block"), new BlockItem(HUNTING_PORTAL_BLOCK, new Item.Settings().group(MiningDims.MINING_DIMS_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("mining_dims", "caving_portal_block"), new BlockItem(CAVING_PORTAL_BLOCK, new Item.Settings().group(MiningDims.MINING_DIMS_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("mining_dims", "nethering_portal_block"), new BlockItem(NETHERING_PORTAL_BLOCK, new Item.Settings().group(MiningDims.MINING_DIMS_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("mining_dims", "climbing_portal_block"), new BlockItem(CLIMBING_PORTAL_BLOCK, new Item.Settings().group(MiningDims.MINING_DIMS_GROUP)));
    }
}
