package net.kaupenjoe.tutorialmod.block.entity.custom;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.potion.ModPotions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PotionThrowerBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public PotionThrowerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.POTION_THROWER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);

    }

    @Override
    public boolean triggerEvent(int pId, int pType) {
        return super.triggerEvent(pId, pType);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Potion Thrower!");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return null;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, PotionThrowerBlockEntity pBlockEntity) {
        if (pLevel == null || pLevel.isClientSide || pLevel.getGameTime() % 20 != 0) {
            return;
        }

        TutorialMod.LOGGER.debug("POTION THROWER TICK!");

        double range = 20.0D;
        AABB area = new AABB(pPos).inflate(range);
        List<LivingEntity> nearbyEntities = pLevel.getEntitiesOfClass(LivingEntity.class, area, entity -> entity instanceof LivingEntity && !(entity instanceof Player));

        if (!nearbyEntities.isEmpty()) {
            for (LivingEntity target : nearbyEntities) {
                TutorialMod.LOGGER.debug("Throwing potion at " + target);
                pBlockEntity.throwPotion(target);
            }
        }


    }

    private void throwPotion(LivingEntity target) {
        ThrownPotion potionEntity = new ThrownPotion(level, worldPosition.getX() + 0.5, worldPosition.getY() + 1.5, worldPosition.getZ() + 0.5);
        potionEntity.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.WINGARDIUM_LEVIOSA_POTION.get()));

        double d0 = target.getX() - potionEntity.getX();
        double d1 = target.getY(0.3333333333333333D) - potionEntity.getY();
        double d2 = target.getZ() - potionEntity.getZ();
        double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));

        potionEntity.shoot(d0, d1 + d3 * 0.2D, d2, 1.6F, 12.0F);

        level.addFreshEntity(potionEntity);
    }
}
