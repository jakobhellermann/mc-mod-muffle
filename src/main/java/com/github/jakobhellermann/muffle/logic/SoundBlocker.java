package com.github.jakobhellermann.muffle.logic;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;

public interface SoundBlocker {
    int getRange();

    boolean isSoundBlocked(SoundEvent sound, SoundCategory category);
}
