package com.github.jakobhellermann.muffle.utils;

import java.util.Arrays;

public class BoundedStringCache {
    private final String[] items;
    private int currentIdx;

    public BoundedStringCache(int size) {
        currentIdx = 0;
        items = new String[size];
    }

    public String[] getItems() {
        return this.items;
    }

    public void add(String value) {
        this.items[currentIdx] = value;
        currentIdx = (currentIdx + 1) % this.items.length;
    }

    public boolean contains(String value) {
        for (String s : this.items)
            if (s != null && s.equals(value))
                return true;
        return false;
    }

    public void addIfMissing(String value) {
        if (!this.contains(value)) {
            this.add(value);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}

