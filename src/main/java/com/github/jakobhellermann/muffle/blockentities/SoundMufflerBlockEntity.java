package com.github.jakobhellermann.muffle.blockentities;

import com.github.jakobhellermann.muffle.Muffle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

import java.util.ArrayList;

public class SoundMufflerBlockEntity extends BlockEntity {
    private int range = 6;
    private final ArrayList<String> blocklist = new ArrayList<>();

    public SoundMufflerBlockEntity() {
        super(Muffle.SOUND_MUFFLER_ENTITY);
    }

    public int getRange() {
        return range;
    }

    public ArrayList<String> getBlocklist() {
        return blocklist;
    }

    public void setRange(int range) {
        this.markDirty();
        this.range = range;
    }

    public boolean addBlockedSound(String id) {
        this.markDirty();
        return this.blocklist.add(id);
    }

    public boolean removeBlockedSound(String id) {
        this.markDirty();
        return this.blocklist.remove(id);
    }

    @Environment(EnvType.CLIENT)
    public boolean isSoundBlocked(SoundEvent sound, SoundCategory category) {
        String id = sound.getId().toString();
        boolean blocked = this.blocklist.contains(id);

        System.out.println("playSound invoked: " + id + ", category: " + category.getName() + ", blocked: " + blocked);
        return blocked;
    }

    @Override
    public void markDirty() {
        System.out.println("marked dirty");
        super.markDirty();
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        tag.putInt("range", this.range);

        ListTag blocklistTag = new ListTag();
        for (String id : this.blocklist) blocklistTag.add(StringTag.of(id));
        tag.put("blocklist", blocklistTag);

        return tag;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.range = tag.getInt("range");
        for (Tag t : tag.getList("blocklist", 8)) {
            blocklist.add(t.asString());
        }

        super.fromTag(state, tag);
    }
}
