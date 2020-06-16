package com.github.jakobhellermann.muffle;

import com.github.jakobhellermann.muffle.blockentities.AdvancedSoundMufflerBlockEntity;
import com.github.jakobhellermann.muffle.blockentities.BasicSoundMufflerBlockEntity;
import com.github.jakobhellermann.muffle.blockentities.SoundAmplifierBlockEntity;
import com.github.jakobhellermann.muffle.blocks.AdvancedSoundMuffler;
import com.github.jakobhellermann.muffle.blocks.BasicSoundMuffler;
import com.github.jakobhellermann.muffle.blocks.SoundAmplifier;
import com.github.jakobhellermann.muffle.utils.BoundedStringCache;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class Muffle implements ModInitializer {
    public static final String MOD_ID = "muffle";

    public static final Block ADVANCED_SOUND_MUFFLER = new AdvancedSoundMuffler();
    public static final Block BASIC_SOUND_MUFFLER = new BasicSoundMuffler();
    public static final Block SOUND_AMPLIFIER = new SoundAmplifier();

    public static BlockEntityType<AdvancedSoundMufflerBlockEntity> ADVANCED_SOUND_MUFFLER_ENTITY;
    public static BlockEntityType<BasicSoundMufflerBlockEntity> BASIC_SOUND_MUFFLER_ENTITY;
    public static BlockEntityType<SoundAmplifierBlockEntity> SOUND_AMPLIFIER_BLOCK_ENTITY;

    public static BoundedStringCache recentSounds = new BoundedStringCache(10);

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, BasicSoundMuffler.ID, BASIC_SOUND_MUFFLER);
        Registry.register(Registry.BLOCK, AdvancedSoundMuffler.ID, ADVANCED_SOUND_MUFFLER);
        Registry.register(Registry.BLOCK, SoundAmplifier.ID, SOUND_AMPLIFIER);

        Registry.register(Registry.ITEM, BasicSoundMuffler.ID, new BlockItem(BASIC_SOUND_MUFFLER, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, AdvancedSoundMuffler.ID, new BlockItem(ADVANCED_SOUND_MUFFLER, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, SoundAmplifier.ID, new BlockItem(SOUND_AMPLIFIER, new Item.Settings().group(ItemGroup.MISC)));

        BASIC_SOUND_MUFFLER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BasicSoundMuffler.ID, BlockEntityType.Builder.create(BasicSoundMufflerBlockEntity::new, BASIC_SOUND_MUFFLER).build(null));
        ADVANCED_SOUND_MUFFLER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, AdvancedSoundMuffler.ID, BlockEntityType.Builder.create(AdvancedSoundMufflerBlockEntity::new, ADVANCED_SOUND_MUFFLER).build(null));
        SOUND_AMPLIFIER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, SoundAmplifier.ID, BlockEntityType.Builder.create(SoundAmplifierBlockEntity::new, SOUND_AMPLIFIER).build(null));
    }
}
