package com.github.jakobhellermann.muffle.mixin;

import com.github.jakobhellermann.muffle.blockentities.SoundMufflerBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class ClientWorldSoundMixin extends World {
    protected ClientWorldSoundMixin(MutableWorldProperties mutableWorldProperties, RegistryKey<World> registryKey, RegistryKey<DimensionType> registryKey2, DimensionType dimensionType, Supplier<Profiler> profiler, boolean bl, boolean bl2, long l) {
        super(mutableWorldProperties, registryKey, registryKey2, dimensionType, profiler, bl, bl2, l);
    }

    @Environment(EnvType.CLIENT)
    @Inject(at = @At("HEAD"), method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V", cancellable = true)
    private void playSound(double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean bl, CallbackInfo ci) {
        boolean blocked = this.blockEntities.stream()
                .filter(blockEntity -> blockEntity instanceof SoundMufflerBlockEntity)
                .map(blockEntity -> (SoundMufflerBlockEntity) blockEntity)
                .filter(blockEntity -> {
                    double distance = blockEntity.getPos().getSquaredDistance(x, y, z, false);
                    return distance < blockEntity.getRange();
                })
                .anyMatch(blockEntity -> blockEntity.isSoundBlocked(sound, category));

        if (blocked) {
            ci.cancel();
        }
    }
}
