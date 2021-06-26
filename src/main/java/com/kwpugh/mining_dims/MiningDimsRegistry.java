package com.kwpugh.mining_dims;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;

public class MiningDimsRegistry
{
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, MiningDims.MOD_DIMENSION_ID);
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY2 = RegistryKey.of(Registry.WORLD_KEY, MiningDims.MOD_DIMENSION2_ID);
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY3 = RegistryKey.of(Registry.WORLD_KEY, MiningDims.MOD_DIMENSION3_ID);
    public static final RegistryKey<World> MININGDIMS_WORLD_KEY4 = RegistryKey.of(Registry.WORLD_KEY, MiningDims.MOD_DIMENSION4_ID);

    public static void setupDimension()
    {
        //TBD - refer to BumbleZone in the future for ideas
    }
}