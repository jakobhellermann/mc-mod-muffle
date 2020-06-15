package com.github.jakobhellermann.muffle.blocks;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.blockentities.AdvancedSoundMufflerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AdvancedSoundMuffler extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier(Muffle.MOD_ID, "advanced_sound_muffler");

    public AdvancedSoundMuffler() {
        super(Settings.copy(Blocks.IRON_BLOCK));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new AdvancedSoundMufflerBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        AdvancedSoundMufflerBlockEntity blockEntity = (AdvancedSoundMufflerBlockEntity) world.getBlockEntity(pos);

        assert blockEntity != null;
        System.out.println("Range: " + blockEntity.getRange());
        blockEntity.setRange(blockEntity.getRange() + 1);

        int size = Registry.SOUND_EVENT.getIds().size();
        System.out.println("sound event reg size: " + size);

        return super.onUse(state, world, pos, player, hand, hit);
    }
}
