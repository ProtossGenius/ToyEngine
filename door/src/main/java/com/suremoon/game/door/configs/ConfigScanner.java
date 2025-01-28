package com.suremoon.game.door.configs;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.BiConsumer;

public class ConfigScanner {
    public static <T extends Annotation> void findAnnotatedClasses(Class<T> annotationClass, BiConsumer<T, Class<?>> regConfig, String... packageNames) throws IOException, ClassNotFoundException {
        for (String packageName : packageNames) {
            var classes = getClassesInPackage(packageName, annotationClass);
            for (Class<?> claxx : classes) {
                if (claxx.isAnnotationPresent(annotationClass)) {
                    T cmdCfg = claxx.getAnnotation(annotationClass);
                    regConfig.accept(cmdCfg, claxx);
                }
            }
        }
    }

    public static Set<Class<?>> getClassesInPackage(String packageName, Class<? extends Annotation> annotationClass) throws IOException, ClassNotFoundException {
        ImmutableSet.Builder<Class<?>> builder = ImmutableSet.builder();
        ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
        for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClassesRecursive(packageName)) {
            builder.add(classInfo.load());
        }

        return builder.build();
    }

}
