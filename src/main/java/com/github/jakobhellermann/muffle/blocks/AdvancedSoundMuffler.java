package com.github.jakobhellermann.muffle.blocks;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.blockentities.AdvancedSoundMufflerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

public class AdvancedSoundMuffler extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier(Muffle.MOD_ID, "advanced_sound_muffler");

    public AdvancedSoundMuffler() {
        super(Settings.copy(Blocks.IRON_BLOCK));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new AdvancedSoundMufflerBlockEntity();
    }
}
