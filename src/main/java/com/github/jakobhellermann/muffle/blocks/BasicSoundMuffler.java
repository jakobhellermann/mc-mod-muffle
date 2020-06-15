package com.github.jakobhellermann.muffle.blocks;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.blockentities.BasicSoundMufflerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

public class BasicSoundMuffler extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier(Muffle.MOD_ID, "basic_sound_muffler");

    public BasicSoundMuffler() {
        super(Settings.of(Material.WOOL).strength(0.8F).sounds(BlockSoundGroup.WOOL));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new BasicSoundMufflerBlockEntity();
    }
}
