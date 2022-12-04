package com.kwpugh.mining_dims.items;

import com.kwpugh.mining_dims.init.MiningDimsRegistry;
import com.kwpugh.mining_dims.util.TeleporterUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

import java.util.List;

public class MiningTeleporter extends BaseTeleporter
{
    public MiningTeleporter(Item.Settings settings)
    {
        super(settings);
    }

    private RegistryKey<World> dimKey = MiningDimsRegistry.MININGDIMS_WORLD_KEY;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getStackInHand(hand);
        TeleporterUtil.movePlayer(dimKey, world, player, hand);

        return TypedActionResult.success(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(Text.translatable("item.mining_dims.teleporter.desc").formatted(Formatting.GREEN));
    }
}