package com.github.jakobhellermann.muffle.blockentities;

import com.github.jakobhellermann.muffle.Muffle;
import com.github.jakobhellermann.muffle.logic.SoundBlocker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class AdvancedSoundMufflerBlockEntity extends BlockEntity implements SoundBlocker, BlockEntityClientSerializable {
    public enum MufflerPacketValueKind {
        Range, Blocked
    }

    private int range = 6;
    private final ArrayList<String> blocklist = new ArrayList<>();

    public AdvancedSoundMufflerBlockEntity() {
        super(Muffle.ADVANCED_SOUND_MUFFLER_ENTITY);
    }

    public ArrayList<String> getBlocklist() {
        return blocklist;
    }

    public void setRange(int range) {
        System.out.println("set range to " + range);
        this.markDirty();
        this.range = range;
    }

    public boolean isBlocked(String id) {
        return this.blocklist.stream().anyMatch(blocked -> blocked.startsWith(id));
    }

    public void setBlocked(String id, boolean value) {
        markDirty();
        if (value) {
            this.blocklist.add(id);
        } else {
            this.blocklist.removeIf(i -> i.startsWith(id));
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        System.out.println("toTag with range=" + this.range);
        super.toTag(tag);

        tag.putInt("range", this.range);

        ListTag blocklistTag = new ListTag();
        for (String id : this.blocklist) blocklistTag.add(StringTag.of(id));
        tag.put("blocklist", blocklistTag);

        return tag;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        System.out.println("fromTag with range=" + tag.getInt("range"));
        super.fromTag(state, tag);
        this.range = tag.getInt("range");
        for (Tag t : tag.getList("blocklist", 8)) {
            blocklist.add(t.asString());
        }

        super.fromTag(state, tag);
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        this.fromTag(null, compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return this.toTag(compoundTag);
    }

    // sound blocker impl

    @Override
    public BlockPos getPosition() {
        return this.getPos();
    }

    @Override
    public int getRange() {
        return range;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean isSoundBlocked(SoundEvent sound, SoundCategory category) {
        String id = sound.getId().toString();
        boolean blocked = this.blocklist.contains(id);

        return blocked;
    }

}
