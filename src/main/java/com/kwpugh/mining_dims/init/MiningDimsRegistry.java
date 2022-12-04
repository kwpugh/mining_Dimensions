package com.kwpugh.mining_dims.init;

import com.kwpugh.mining_dims.MiningDims;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public class MiningDimsRegistry
{
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY = RegistryKey.of(RegistryKeys.WORLD, MiningDims.MOD_DIMENSION_ID);
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY2 = RegistryKey.of(RegistryKeys.WORLD, MiningDims.MOD_DIMENSION2_ID);
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY3 = RegistryKey.of(RegistryKeys.WORLD, MiningDims.MOD_DIMENSION3_ID);
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY4 = RegistryKey.of(RegistryKeys.WORLD, MiningDims.MOD_DIMENSION4_ID);
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY5 = RegistryKey.of(RegistryKeys.WORLD, MiningDims.MOD_DIMENSION5_ID);

    public static void setupDimension()
    {
        //TBD - refer to BumbleZone in the future for ideas
    }
}