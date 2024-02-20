package com.suremoon.game.door.observer;

import java.util.function.Consumer;

public interface IObserverMgr {
    /**
     * register a observer.
     *
     * @param en
     * @param callback
     */
    void register(ObserverEnum en, Consumer<Object> callback);

    /**
     * submit message to observer.
     *
     * @param en
     */
    void submit(ObserverEnum en, Object obj);

    //TODO: remove
}
