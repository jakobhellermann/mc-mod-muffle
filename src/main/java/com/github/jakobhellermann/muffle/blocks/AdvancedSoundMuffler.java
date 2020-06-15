package com.github.jakobhellermann.muffle.blocks;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.blockentities.AdvancedSoundMufflerBlockEntity;
import com.github.jakobhellermann.muffle.gui.SoundMufflerContainer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
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
import net.minecraft.text.TranslatableText;
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
        AdvancedSoundMufflerBlockEntity e = (AdvancedSoundMufflerBlockEntity) world.getBlockEntity(pos);
        StringBuilder log = new StringBuilder("Blocklist: ");
        for(String id : e.getBlocklist()) {
            log.append(id);
            log.append(", ");
        }
        System.out.println(log);
        System.out.println("Range: " + e.getRange());

        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(SoundMufflerContainer.ID, player, (buffer) -> {
                buffer.writeText(new TranslatableText(this.getTranslationKey()));
                buffer.writeBlockPos(pos);
            });
        }
        return ActionResult.SUCCESS;
    }
}
