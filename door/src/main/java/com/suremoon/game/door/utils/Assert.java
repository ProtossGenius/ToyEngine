package com.suremoon.game.door.utils;

public class Assert {
    public static void True(boolean check, String error) {
        if (!check) {
            throw new RuntimeException(error);
        }
    }
}
