package com.github.jakobhellermann.muffle.gui;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.blockentities.AdvancedSoundMufflerBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import spinnery.common.container.BaseContainer;

public class SoundMufflerContainer extends BaseContainer {
    public static final Identifier ID = new Identifier(Muffle.MOD_ID, "sound_muffler_container");

    public Text name;
    public PlayerEntity player;

    public AdvancedSoundMufflerBlockEntity blockEntity;

    public SoundMufflerContainer(int synchronizationID, Text name, PlayerInventory playerInventory, BlockPos blockEntityPos) {
        super(synchronizationID, playerInventory);

        this.name = name;
        this.player = playerInventory.player;

        this.blockEntity = (AdvancedSoundMufflerBlockEntity) getWorld().getBlockEntity(blockEntityPos);
    }
}
