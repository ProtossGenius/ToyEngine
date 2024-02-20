package com.suremoon.game.door.observer;

public enum ObserverEnum {
    GOODS(Object.class),
    ;

    private final Class clazz;

    ObserverEnum(Class c) {
        if (c == null) throw new RuntimeException("ObserverEnum clazz can't be null");
        this.clazz = c;
    }

    public boolean checkClass(Object c) {
        return c == null || clazz.isInstance(c);
    }
}
