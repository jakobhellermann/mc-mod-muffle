package com.github.jakobhellermann.muffle;

import com.github.jakobhellermann.muffle.blockentities.SoundMufflerBlockEntity;
import com.github.jakobhellermann.muffle.blocks.SoundMuffler;
import com.github.jakobhellermann.muffle.utils.BoundedStringCache;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Muffle implements ModInitializer {
    public static final String MOD_ID = "muffle";

    public static final Block SOUND_MUFFLER = new SoundMuffler(Block.Settings.of(Material.WOOL).strength(0.8F, 1.0F));
    public static BlockEntityType<SoundMufflerBlockEntity> SOUND_MUFFLER_ENTITY;

    public static BoundedStringCache recentSounds = new BoundedStringCache(10);

    // if onlyOneLevelDown is true, there will be e.g. only entity.chicken, not e.c.death, e.c.ambient, e.c.etc
    public static List<String> allSoundsInOrder(boolean onlyOneLevelDown) {
        Stream<String> soundStream = Registry.SOUND_EVENT.getIds().stream().map(Identifier::toString);
        if (!onlyOneLevelDown) {
            return soundStream.sorted().collect(Collectors.toList());
        } else {
            return soundStream.map(id -> {
                int firstDotIdx = id.indexOf('.');
                int secondDotIdx = id.indexOf('.', firstDotIdx + 1);
                if (firstDotIdx != -1 && secondDotIdx != -1) {
                    return id.substring(0, secondDotIdx);
                } else {
                    return id;
                }
            }).sorted().distinct().collect(Collectors.toList());
        }
    }

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, SoundMuffler.ID, SOUND_MUFFLER);
        Registry.register(Registry.ITEM, SoundMuffler.ID, new BlockItem(SOUND_MUFFLER, new Item.Settings().group(ItemGroup.MISC)));

        SOUND_MUFFLER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, SoundMuffler.ID, BlockEntityType.Builder.create(SoundMufflerBlockEntity::new, SOUND_MUFFLER).build(null));
    }
}
