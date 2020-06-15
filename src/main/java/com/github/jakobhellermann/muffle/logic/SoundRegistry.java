package com.github.jakobhellermann.muffle.logic;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SoundRegistry {
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
}
