package com.suremoon.game.door.code_tools;

public class JarLoader {

    public static Object NewClass(String className, ObjectDo od) throws Exception {
        Class<?> clz = Class.forName(className);
        Object ins = clz.newInstance();
        od.Do(ins);

        return ins;
    }

    public interface ObjectDo{
        void Do(Object o);
        ObjectDo Null = o -> {};
    }

}