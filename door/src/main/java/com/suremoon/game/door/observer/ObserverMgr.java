package com.suremoon.game.door.observer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class ObserverMgr implements IObserverMgr {
    public static ObserverMgr mgr = new ObserverMgr();
    private final ConcurrentHashMap<ObserverEnum, List<Consumer<Object>>> registerMap = new ConcurrentHashMap<>();
    ExecutorService es = new ThreadPoolExecutor(10, 15, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    private ObserverMgr() {

    }

    @Override
    public void register(ObserverEnum en, Consumer<Object> callback) {
        this.registerMap.compute(en, (e, l) -> {
            if (l == null) {
                return new LinkedList<>(Collections.singletonList(callback));
            }
            l.add(callback);
            return l;
        });
    }

    @Override
    public synchronized void submit(ObserverEnum en, Object obj) {
        var list = this.registerMap.get(en);
        if (list == null) return;
        for (var cb : list) {
            es.submit(() -> cb.accept(obj));
        }
    }
}
