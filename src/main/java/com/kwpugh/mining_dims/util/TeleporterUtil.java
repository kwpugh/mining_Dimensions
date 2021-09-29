package com.kwpugh.mining_dims.util;

import com.kwpugh.mining_dims.MiningDims;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
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
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class TeleporterUtil
{
    public static TypedActionResult<ItemStack> movePlayer(RegistryKey<World> dimKey, World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getStackInHand(hand);
        ItemStack stack1 = player.getStackInHand(hand);
        RegistryKey<World> registryKey = world.getRegistryKey();

        if (world.isClient) return TypedActionResult.success(stack);

        if (MiningDims.CONFIG.GENERAL.enableReturnToBed && player.isSneaking())  // RETURN PLAYER TO BED
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

        if(!player.isSneaking())  // RETURN PLAYER SOMEWHERE IN OVERWORLD
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

            // Check a number of times for a safe spot
            for (int i = 1; i < 6; i++)
            {
                if (i == 1)
                {
                    serverPlayer.sendMessage((new TranslatableText("item.mining_dims.teleporter1")), true);   //checking...
                }

                if (i > 1 && i < 6)
                {
                    serverPlayer.sendMessage((new TranslatableText("item.mining_dims.teleporter2")), true);
                }

                BlockPos playerLoc = player.getBlockPos();
                Random rand = new Random();

                // Use players current x and z for starting point
                int x = (int) Math.round(playerLoc.getX()) + rand.nextInt(10 + 5) - 5;
                int y = 150;
                int z = (int) Math.round(playerLoc.getZ()) + rand.nextInt(10 + 5) - 5;

                Chunk chunk = destWorld.getChunk(x >> 4, z >> 4);
                Biome biome = destWorld.getBiome(new BlockPos(x, y, z));

                //Lets not dump the player in the middle of a body of water, shall we?
                if (MiningDims.CONFIG.GENERAL.enableOceanCheck)
                {
                    if((biome.getCategory().getName().equals("ocean")))
                    {
                        continue;
                    }
                }

                //Let's avoid putting them underground
                while (y > 60)
                {
                    y--;
                    BlockPos groundPos = new BlockPos(x, y - 2, z);

                    if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR) &&
                            (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.BEDROCK) &&
                                    (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.LAVA) &&
                                            (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.ACACIA_BUTTON) &&
                                                    (y - 2) > 60))))
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
                                serverPlayer.teleport(destWorld, x, y, z, serverPlayer.getYaw(), serverPlayer.getPitch());
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

    public void setPositionAndUpdate(ServerPlayerEntity player, World world, BlockPos bedLoc)
    {
        player.teleport(bedLoc.getX() + 0.5F, bedLoc.getY() + 0.6F, bedLoc.getZ() + 0.5F);
        player.fallDistance = 0;
    }
}




//import com.kwpugh.mining_dims.MiningDims;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.Material;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.sound.SoundCategory;
//import net.minecraft.sound.SoundEvents;
//import net.minecraft.text.TranslatableText;
//import net.minecraft.util.Hand;
//import net.minecraft.util.TypedActionResult;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.registry.RegistryKey;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.chunk.Chunk;
//
//import java.util.Random;
//
//public class TeleporterUtil
//{
//    public static TypedActionResult<ItemStack> movePlayer(RegistryKey<World> dimKey, World world, PlayerEntity player, Hand hand)
//    {
//        ItemStack stack = player.getStackInHand(hand);
//        ItemStack stack1 = player.getStackInHand(hand);
//        RegistryKey<World> registryKey = world.getRegistryKey();
//
//        if (world.isClient) return TypedActionResult.success(stack);
//
//        if (MiningDims.CONFIG.GENERAL.enableReturnToBed && player.isSneaking())  // RETURN PLAYER TO BED
//        {
//            ServerWorld serverWorld = ((ServerWorld)world).getServer().getWorld(World.OVERWORLD);
//            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
//
//            if(serverPlayer.getSpawnPointPosition() != null) //player bed location not null
//            {
//                BlockPos bedLoc = serverPlayer.getSpawnPointPosition(); //get player bed position
//                serverPlayer.stopRiding();
//
//                serverPlayer.teleport(serverWorld, bedLoc.getX() + 0.5F, bedLoc.getY(), bedLoc.getZ() + 0.5F, serverPlayer.getYaw(), serverPlayer.getPitch());
//                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
//
//                player.sendMessage((new TranslatableText("item.mining_dims.teleporter4")), true);   //Welcome Home!
//
//                TypedActionResult.success(stack1);
//            }
//            else
//            {
//                player.sendMessage((new TranslatableText("item.mining_dims.teleporter5")), true);  //Set a bed spawn first!
//
//                TypedActionResult.success(stack);
//            }
//        }
//
//        if(!player.isSneaking())  // RETURN PLAYER SOMEWHERE IN OVERWORLD
//        {
//            ServerWorld destWorld = null;
//            ServerWorld overWorld = ((ServerWorld)world).getServer().getWorld(World.OVERWORLD);
//            ServerWorld targetWorld = ((ServerWorld)world).getServer().getWorld(dimKey);
//
//            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
//
//            RegistryKey<World> currentWorldKey = world.getRegistryKey();
//            if(currentWorldKey == dimKey)
//            {
//                destWorld = overWorld;
//            }
//            else
//            {
//                destWorld = targetWorld;
//            }
//
//            // Check a number of times for a safe spot
//            for (int i = 1; i < 6; i++)
//            {
//                if (i == 1)
//                {
//                    serverPlayer.sendMessage((new TranslatableText("item.mining_dims.teleporter1")), true);   //checking...
//                }
//
//                if (i > 1 && i < 6)
//                {
//                    serverPlayer.sendMessage((new TranslatableText("item.mining_dims.teleporter2")), true);
//                }
//
//                BlockPos playerLoc = player.getBlockPos();
//                Random rand = new Random();
//
//                // Use players current x and z for starting point
//                int x = (int) Math.round(playerLoc.getX()) + rand.nextInt(10 + 5) - 5;
//                int y = 150;
//                int z = (int) Math.round(playerLoc.getZ()) + rand.nextInt(10 + 5) - 5;
//
//                Chunk chunk = destWorld.getChunk(x >> 4, z >> 4);
//                Biome biome = destWorld.getBiome(new BlockPos(x, y, z));
//
//                //Lets not dump the player in the middle of a body of water, shall we?
//                if (MiningDims.CONFIG.GENERAL.enableOceanCheck)
//                {
//                    if((biome.getCategory().getName().equals("ocean")))
//                    {
//                        continue;
//                    }
//                }
//
//                //Let's avoid putting them underground
//                while (y > 60)
//                {
//                    y--;
//                    BlockPos groundPos = new BlockPos(x, y - 2, z);
//
//                    if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR) &&
//                            (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.BEDROCK) &&
//                            (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.LAVA) &&
//                            (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.WATER) &&
//                            (y - 2) > 60))))
//                    {
//                        BlockPos legPos = new BlockPos(x, y - 1, z);
//
//                        if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
//                        {
//                            BlockPos headPos = new BlockPos(x, y, z);
//
//                            if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
//                            {
//                                serverPlayer.stopRiding();
//                                serverPlayer.teleport(destWorld, x, y, z, serverPlayer.getYaw(), serverPlayer.getPitch());
//                                serverPlayer.fallDistance = 0.0F;
//
//                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
//
//                                return TypedActionResult.success(stack);
//                            }
//                        }
//                    }
//                }
//            }
//
//            serverPlayer.sendMessage((new TranslatableText("item.mining_dims.teleporter3")), true);
//        }
//
//        return TypedActionResult.success(stack);
//    }
//
//    public void setPositionAndUpdate(ServerPlayerEntity player, World world, BlockPos bedLoc)
//    {
//        player.teleport(bedLoc.getX() + 0.5F, bedLoc.getY() + 0.6F, bedLoc.getZ() + 0.5F);
//        player.fallDistance = 0;
//    }
//}
