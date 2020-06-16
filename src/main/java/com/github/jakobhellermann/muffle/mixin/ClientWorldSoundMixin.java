package com.github.jakobhellermann.muffle.mixin;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.logic.SoundBlocker;
import com.github.jakobhellermann.muffle.logic.SoundModifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class ClientWorldSoundMixin extends World {
    protected ClientWorldSoundMixin(MutableWorldProperties properties, RegistryKey<World> registryKey, DimensionType dimensionType, Supplier<Profiler> supplier, boolean bl, boolean bl2, long l) {
        super(properties, registryKey, dimensionType, supplier, bl, bl2, l);
    }

    @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/PositionedSoundInstance;<init>(Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFDDD)V"), method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V")
    private void modifySound(Args args, double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean bl) {
        for (BlockEntity blockEntity : this.blockEntities) {
            if (!(blockEntity instanceof SoundModifier)) continue;
            SoundModifier soundModifier = (SoundModifier) blockEntity;

            double distance = blockEntity.getPos().getSquaredDistance(x, y, z, false);
            int range = soundModifier.getRange();
            if (distance > (range * range) + 1) continue;

            if (!soundModifier.isSoundModified(sound, category)) continue;

            args.set(2, soundModifier.newVolume(volume));
            args.set(3, soundModifier.newPitch(pitch));
        }
    }


    @Environment(EnvType.CLIENT)
    @Inject(at = @At("HEAD"), method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V", cancellable = true)
    private void playSound(double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean bl, CallbackInfo ci) {
        Muffle.recentSounds.addIfMissing(sound.getId().toString());

        for (BlockEntity blockEntity : this.blockEntities) {
            if (!(blockEntity instanceof SoundBlocker)) continue;
            SoundBlocker soundBlocker = (SoundBlocker) blockEntity;

            double distance = blockEntity.getPos().getSquaredDistance(x, y, z, false);
            int range = soundBlocker.getRange();
            if (distance > (range * range) + 1) continue;

            if (soundBlocker.isSoundBlocked(sound, category)) {
                ci.cancel();
                return;
            }
        }
    }
}
