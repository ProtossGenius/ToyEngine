package com.suremoon.game.door.code_tools;

public class Pair<TKey, TVal> {
    TKey key;
    TVal value;

    public Pair(TKey key, TVal value) {
        this.key = key;
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    public TVal getValue() {
        return value;
    }

    public void setValue(TVal value) {
        this.value = value;
    }
}
