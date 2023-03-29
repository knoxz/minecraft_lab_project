package net.kaupenjoe.tutorialmod.block.entity;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.entity.custom.AnimatedBlockEntity;
import net.kaupenjoe.tutorialmod.block.entity.custom.GemCuttingStationBlockEntity;
import net.kaupenjoe.tutorialmod.block.entity.custom.ModSignBlockEntity;
import net.kaupenjoe.tutorialmod.block.entity.custom.PotionThrowerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TutorialMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemCuttingStationBlockEntity>> GEM_CUTTING_STATION_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("gem_cutting_station_block_entity", () ->
                    BlockEntityType.Builder.of(GemCuttingStationBlockEntity::new,
                            ModBlocks.GEM_CUTTING_STATION.get()).build(null));

    public static final RegistryObject<BlockEntityType<PotionThrowerBlockEntity>> POTION_THROWER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("potion_thrower_block_entity", () ->
                    BlockEntityType.Builder.of(PotionThrowerBlockEntity::new,
                            ModBlocks.POTION_THROWER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.EBONY_WALL_SIGN.get(),
                            ModBlocks.EBONY_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> ANIMATED_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("animated_block_entity", () ->
                    BlockEntityType.Builder.of(AnimatedBlockEntity::new,
                            ModBlocks.ANIMATED_BLOCK.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
