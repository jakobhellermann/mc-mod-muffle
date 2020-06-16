package com.github.jakobhellermann.muffle.blockentities;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.logic.SoundBlocker;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class BasicSoundMufflerBlockEntity extends BlockEntity implements SoundBlocker {
    public static int RANGE = 5;

    public BasicSoundMufflerBlockEntity() {
        super(Muffle.BASIC_SOUND_MUFFLER_ENTITY);
    }

    @Override
    public int getRange() {
        return BasicSoundMufflerBlockEntity.RANGE;
    }

    @Override
    public boolean isSoundBlocked(SoundEvent sound, SoundCategory category) {
        return true;
    }
}
