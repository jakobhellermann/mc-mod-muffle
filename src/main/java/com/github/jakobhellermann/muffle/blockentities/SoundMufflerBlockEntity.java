package com.github.jakobhellermann.muffle.blockentities;

import com.github.jakobhellermann.muffle.Muffle;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class SoundMufflerBlockEntity extends BlockEntity {

    public SoundMufflerBlockEntity() {
        super(Muffle.SOUND_MUFFLER_ENTITY);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        return tag;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
    }
}
