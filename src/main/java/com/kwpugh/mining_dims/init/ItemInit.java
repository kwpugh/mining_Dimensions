package com.kwpugh.mining_dims.init;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.items.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit
{
    static boolean mining = MiningDims.CONFIG.GENERAL.enableMiningTeleporter;
    static boolean caving = MiningDims.CONFIG.GENERAL.enableCavingTeleporter;
    static boolean climbing = MiningDims.CONFIG.GENERAL.enableClimbingTeleporter;
    static boolean nethering = MiningDims.CONFIG.GENERAL.enableNetheringTeleporter;
    static boolean hunting = MiningDims.CONFIG.GENERAL.enableHuntingTeleporter;

    public static final Item MINING_TELEPORTER = new MiningTeleporter(new Item.Settings().maxCount(1).group(MiningDims.MINING_DIMS_GROUP));
    public static final Item CAVING_TELEPORTER = new CavingTeleporter(new Item.Settings().maxCount(1).group(MiningDims.MINING_DIMS_GROUP));
    public static final Item CLIMBING_TELEPORTER = new ClimbingTeleporter(new Item.Settings().maxCount(1).group(MiningDims.MINING_DIMS_GROUP));
    public static final Item NETHERING_TELEPORTER = new NetheringTeleporter(new Item.Settings().maxCount(1).group(MiningDims.MINING_DIMS_GROUP));
    public static final Item HUNTING_TELEPORTER = new HuntingTeleporter(new Item.Settings().maxCount(1).group(MiningDims.MINING_DIMS_GROUP));

    public static final Item FLINT_AND_DIAMOND = new Item(new Item.Settings().maxCount(1).group(MiningDims.MINING_DIMS_GROUP));

    public static final Item DIAMOND_NUGGET = new Item((new Item.Settings()).maxCount(64).group(MiningDims.MINING_DIMS_GROUP));
    public static final Item NETHERITE_FRAGMENT = new Item((new Item.Settings()).maxCount(64).group(MiningDims.MINING_DIMS_GROUP));

    public static void init()
    {
        if(mining)
        {
            Registry.register(Registry.ITEM, new Identifier(MiningDims.MOD_ID, "mining_teleporter"), MINING_TELEPORTER);
        }

        if(caving)
        {
            Registry.register(Registry.ITEM, new Identifier(MiningDims.MOD_ID, "caving_teleporter"), CAVING_TELEPORTER);
        }

        if(climbing)
        {
            Registry.register(Registry.ITEM, new Identifier(MiningDims.MOD_ID, "climbing_teleporter"), CLIMBING_TELEPORTER);
        }

        if(nethering)
        {
            Registry.register(Registry.ITEM, new Identifier(MiningDims.MOD_ID, "nethering_teleporter"), NETHERING_TELEPORTER);
        }

        if(hunting)
        {
            Registry.register(Registry.ITEM, new Identifier(MiningDims.MOD_ID, "hunting_teleporter"), HUNTING_TELEPORTER);
        }

        Registry.register(Registry.ITEM, new Identifier(MiningDims.MOD_ID, "diamond_nugget"), DIAMOND_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MiningDims.MOD_ID, "netherite_fragment"), NETHERITE_FRAGMENT);

        Registry.register(Registry.ITEM, new Identifier(MiningDims.MOD_ID, "flint_and_diamond"), FLINT_AND_DIAMOND);
    }
}
