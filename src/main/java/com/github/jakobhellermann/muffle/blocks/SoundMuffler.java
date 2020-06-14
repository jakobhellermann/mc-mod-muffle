package com.github.jakobhellermann.muffle.blocks;

import com.github.jakobhellermann.muffle.blockentities.SoundMufflerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class SoundMuffler extends Block implements BlockEntityProvider {
    public SoundMuffler(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new SoundMufflerBlockEntity();
    }
}
