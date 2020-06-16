package com.github.jakobhellermann.muffle.blockentities;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.logic.SoundModifier;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class SoundAmplifierBlockEntity extends BlockEntity implements SoundModifier {
    public static int RANGE = 5;
    public static int AMPLIFICATION_FACTOR = 20;

    public SoundAmplifierBlockEntity() {
        super(Muffle.SOUND_AMPLIFIER_BLOCK_ENTITY);
    }

    @Override
    public int getRange() {
        return SoundAmplifierBlockEntity.RANGE;
    }

    @Override
    public boolean isSoundModified(SoundEvent sound, SoundCategory category) {
        return true;
    }

    @Override
    public float newVolume(float volume) {
        return volume * SoundAmplifierBlockEntity.AMPLIFICATION_FACTOR;
    }

    /*
    @Override
    public float newPitch(float pitch) {
        return pitch;
    }
    */
}
