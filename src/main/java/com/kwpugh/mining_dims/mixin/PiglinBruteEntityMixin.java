package com.kwpugh.mining_dims.mixin;

import com.kwpugh.mining_dims.MiningDims;
import com.kwpugh.mining_dims.init.MiningDimsRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

@Mixin(PiglinBruteEntity.class)
public abstract class PiglinBruteEntityMixin extends AbstractPiglinEntity implements CrossbowUser
{
	public PiglinBruteEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world)
	{
		super(entityType, world);
	}

	@Inject(method="initEquipment",at=@At("TAIL"),cancellable = true)
    private void miningDimsInitEquipment(Random random, LocalDifficulty difficulty, CallbackInfo ci)
    {
		RegistryKey<World> registryKey = world.getRegistryKey();
		if(registryKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2)
        {
			if(MiningDims.CONFIG.GENERAL.enablePiglinBruteGear)
			{
				this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_SWORD));
				this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.NETHERITE_HELMET));
				this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.NETHERITE_CHESTPLATE));
				this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.NETHERITE_LEGGINGS));
				this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.NETHERITE_BOOTS));
			}
		}
    }

	@Inject(method="damage",at=@At("HEAD"),cancellable = true)
	public void miningdimsDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
	{
		RegistryKey<World> registryKey = world.getRegistryKey();
		if(!this.world.isClient() && registryKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2)
		{
			if((source.getAttacker() instanceof PlayerEntity) && source.isProjectile())
			{
				source.getAttacker().damage(DamageSource.GENERIC, 5.0F);
			}
		}
	}

    @Inject(method="initialize",at=@At("TAIL"),cancellable = true)
    public void miningdimsInitialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag, CallbackInfoReturnable<EntityData> cir)
    {
    	this.miningdimsApplyAttributeModifiers();
    	this.updateEnchantments(random, difficulty);
    }

    private void miningdimsApplyAttributeModifiers()
    {
    	double health = MiningDims.CONFIG.GENERAL.piglinBruteMaxHealth;
		double attack = MiningDims.CONFIG.GENERAL.piglinBruteAttackDamageBonus;
		double armor = MiningDims.CONFIG.GENERAL.piglinBruteArmorBonus;
		double speed = MiningDims.CONFIG.GENERAL.piglinBruteMovementBonus;

		RegistryKey<World> registryKey = world.getRegistryKey();
		if(registryKey == MiningDimsRegistry.MININGDIMS_WORLD_KEY2)
        {
	        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("MiningDims Health Bonus", health, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addPersistentModifier(new EntityAttributeModifier("MiningDims Attack Bonus", attack, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(new EntityAttributeModifier("MiningDims Armor Bonus", armor, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addPersistentModifier(new EntityAttributeModifier("MiningDims Movement Bonus", speed, EntityAttributeModifier.Operation.ADDITION));
		}
    }
}