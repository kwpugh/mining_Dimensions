package com.kwpugh.mining_dims.util;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.init.EnchantmentInit;
import com.kwpugh.mining_dims.init.MiningDimsRegistry;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class TeleporterUtil
{
    public static TypedActionResult<ItemStack> movePlayer(RegistryKey<World> dimKey, World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getStackInHand(hand);
        ItemStack stack1 = player.getStackInHand(hand);
        RegistryKey<World> registryKey = world.getRegistryKey();

        int heightMax;
        int heightMin;

        // Only run on server side
        if(world.isClient) return TypedActionResult.success(stack);

        // If teleporter is enchanted, check for Returning and return to Overworld bed if so
        if(player.isSneaking() && (EnchantmentHelper.getLevel(EnchantmentInit.RETURNING, player.getEquippedStack(EquipmentSlot.MAINHAND)) > 0))
        {
            ServerWorld serverWorld = ((ServerWorld)world).getServer().getWorld(World.OVERWORLD);
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

            if(serverPlayer.getSpawnPointPosition() != null) //player bed location not null
            {
                BlockPos bedLoc = serverPlayer.getSpawnPointPosition(); //get player bed position
                serverPlayer.stopRiding();

                serverPlayer.teleport(serverWorld, bedLoc.getX() + 0.5F, bedLoc.getY(), bedLoc.getZ() + 0.5F, serverPlayer.getYaw(), serverPlayer.getPitch());
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);

                player.sendMessage((new TranslatableText("item.mining_dims.teleporter4")), true);   //Welcome Home!

                TypedActionResult.success(stack1);
            }
            else
            {
                player.sendMessage((new TranslatableText("item.mining_dims.teleporter5")), true);  //Set a bed spawn first!

                TypedActionResult.success(stack);
            }
        }

        // Logic to either send to dim of teleporter in hand or back to Overworld, depending on current location
        if(!player.isSneaking())
        {
            ServerWorld destWorld = null;
            ServerWorld overWorld = ((ServerWorld)world).getServer().getWorld(World.OVERWORLD);
            ServerWorld targetWorld = ((ServerWorld)world).getServer().getWorld(dimKey);
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            RegistryKey<World> currentWorldKey = world.getRegistryKey();

            if(currentWorldKey == dimKey)
            {
                destWorld = overWorld;
            }
            else
            {
                destWorld = targetWorld;
            }

            RegistryKey<World> destKey = destWorld.getRegistryKey();

            heightMax = getHeightMax(destKey);
            heightMin = getHeightMin(destKey);

            // Check a number of times for a safe spot
            for (int i = 1; i < 6; i++)
            {
                if (i == 1)
                {
                    serverPlayer.sendMessage((new TranslatableText("item.mining_dims.teleporter1")), true);   //checking...
                }

                if (i > 1)
                {
                    serverPlayer.sendMessage((new TranslatableText("item.mining_dims.teleporter2")), true);
                }

                BlockPos playerLoc = player.getBlockPos();
                Random rand = new Random();

                // Use players current x and z for starting point
                int x = Math.round(playerLoc.getX()) + rand.nextInt(10 + 5) - 5;
                int y = heightMax;   // starting height for a given dimension
                int z = Math.round(playerLoc.getZ()) + rand.nextInt(10 + 5) - 5;

                Chunk chunk = destWorld.getChunk(x >> 4, z >> 4);

                RegistryEntry<Biome> registryEntry = world.getBiome(new BlockPos(x, y, z));
                if (registryEntry.matchesKey(BiomeKeys.OCEAN) ||
                        registryEntry.matchesKey(BiomeKeys.RIVER) ||
                        registryEntry.matchesKey(BiomeKeys.BEACH))
                {
                    continue;
                }

                //Let's avoid putting them underground
                while(y > heightMin)
                {
                    y--;
                    BlockPos groundPos = new BlockPos(x, y - 2, z);

                    if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR) &&
                            (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.BEDROCK) &&
                            (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.LAVA) &&
                            (y - 2) > heightMin)))
                    {
                        // If block pos under feet is water or lava, place a stone block
                        if(chunk.getBlockState(groundPos).getMaterial().equals(Material.WATER)) chunk.setBlockState(groundPos, Blocks.STONE.getDefaultState(), false);

                        BlockPos legPos = new BlockPos(x, y - 1, z);

                        if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
                        {
                            BlockPos headPos = new BlockPos(x, y, z);

                            if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
                            {
                                serverPlayer.stopRiding();
                                //serverPlayer.teleport(destWorld, x, y, z, serverPlayer.getYaw(), serverPlayer.getPitch());



                                // TEST CODE
                                Vec3d destVec = new Vec3d(x, y, z);
                                TeleportTarget teleportTarget = new TeleportTarget(destVec, null, player.getYaw(), player.getPitch());
                                FabricDimensions.teleport(serverPlayer, destWorld, teleportTarget);
                                // TEST CODE




                                serverPlayer.fallDistance = 0.0F;
                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);

                                return TypedActionResult.success(stack);
                            }
                        }
                    }
                }
            }

            serverPlayer.sendMessage((new TranslatableText("item.mining_dims.teleporter3")), true);
        }

        return TypedActionResult.success(stack);
    }

    // Testing different values
    private static int getHeightMax(RegistryKey<World> destKey)
    {
        if(destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY || destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2 || destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY5)
        {
            return 200;
        }
        else if(destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY3)
        {
            return 275;
        }
        else if(destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY4)
        {
            return 120;
        }
        else
        {
            return 90;
        }
    }

    // Testing different values
    private static int getHeightMin(RegistryKey<World> destKey)
    {
        if(destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY || destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2 || destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY5)
        {
            return 40;
        }
        else if(destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY3)
        {
            return 80;
        }
        else if(destKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY4)
        {
            return 30;
        }
        else
        {
            return 65;
        }
    }
}
