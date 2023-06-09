package net.kaupenjoe.tutorialmod.block.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.entity.custom.AnimatedBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedBlockModel extends AnimatedGeoModel<AnimatedBlockEntity> {
    @Override
    public ResourceLocation getModelLocation(AnimatedBlockEntity object) {
        return new ResourceLocation(TutorialMod.MOD_ID, "geo/animated_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AnimatedBlockEntity object) {
        return new ResourceLocation(TutorialMod.MOD_ID, "textures/machines/animated_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AnimatedBlockEntity animatable) {
        return new ResourceLocation(TutorialMod.MOD_ID, "animations/animated_block.animation.json");
    }
}