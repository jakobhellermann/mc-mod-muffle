package com.github.jakobhellermann.muffle.mixin;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundSystem.class)
public class SoundSystemMixin {
    @Shadow
    private float getSoundVolume(SoundCategory soundCategory) {
        throw new AssertionError();
    }

    @Inject(at = @At("HEAD"), method = "getAdjustedVolume(Lnet/minecraft/client/sound/SoundInstance;)F", cancellable = true)
    private void getAdjustedVolume(SoundInstance soundInstance, CallbackInfoReturnable cir) {
        float volume = soundInstance.getVolume() * getSoundVolume(soundInstance.getCategory());
        System.out.println("sound " + soundInstance.getId().toString() + " has volume " + volume);
        cir.setReturnValue(volume);
        cir.cancel();
    }
}
