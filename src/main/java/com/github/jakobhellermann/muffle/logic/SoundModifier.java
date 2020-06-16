package com.github.jakobhellermann.muffle.logic;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public interface SoundModifier {
    int getRange();

    boolean isSoundModified(SoundEvent sound, SoundCategory category);

    default float newVolume(float volume) {
        return volume;
    }

    default float newPitch(float pitch) {
        return pitch;
    }
}
