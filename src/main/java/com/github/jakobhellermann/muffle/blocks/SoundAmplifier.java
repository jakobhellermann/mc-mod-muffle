package com.github.jakobhellermann.muffle.blocks;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.blockentities.SoundAmplifierBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

public class SoundAmplifier extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier(Muffle.MOD_ID, "sound_amplifier");

    public SoundAmplifier() {
        super(Settings.copy(Blocks.STONE));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new SoundAmplifierBlockEntity();
    }
}
