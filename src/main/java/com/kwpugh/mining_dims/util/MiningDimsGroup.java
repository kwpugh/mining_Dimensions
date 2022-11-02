package com.kwpugh.mining_dims.util;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.init.BlockInit;
import com.kwpugh.mining_dims.init.ItemInit;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

public class MiningDimsGroup
{
    public static void addGroup()
    {
        // force class run when we want
    }

    public static final ItemGroup MINING_DIMS_GROUP = new FabricItemGroup(new Identifier(MiningDims.MOD_ID, "mining_dims_group"))
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ItemInit.MINING_TELEPORTER);
        }

        @Override
        protected void addItems(FeatureSet enabledFeatures, Entries entries)
        {
            entries.add(BlockInit.MINING_PORTAL_BLOCK);
            entries.add(BlockInit.CLIMBING_PORTAL_BLOCK);
            entries.add(BlockInit.CAVING_PORTAL_BLOCK);
            entries.add(BlockInit.HUNTING_PORTAL_BLOCK);
            entries.add(BlockInit.NETHERING_PORTAL_BLOCK);

            entries.add(ItemInit.MINING_TELEPORTER);
            entries.add(ItemInit.CLIMBING_TELEPORTER);
            entries.add(ItemInit.CAVING_TELEPORTER);
            entries.add(ItemInit.HUNTING_TELEPORTER);
            entries.add(ItemInit.NETHERING_TELEPORTER);
        }
    };
}
