package com.kwpugh.mining_dims.items;

import com.kwpugh.pugh_lib.api.CustomRecipeRemainder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseTeleporter extends Item implements CustomRecipeRemainder
{
    public BaseTeleporter(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public boolean hasRecipeRemainder()
    {
        return true;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stackIn)
    {
        ItemStack stack = stackIn.copy();

        return stack;
    }
}