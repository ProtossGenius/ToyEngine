package com.suremoon.game.kernel.initer;

import com.suremoon.game.door.tools.IDManager;

import java.io.File;

public interface InitListItf {
    void init(Progress progress) throws Exception;

    default void loadList(String path, String cfgSuffix, NameInit nameInit, Progress progress)
            throws Exception {
        File f = new File(path);
        File[] effectCfgs = f.listFiles();
        if (effectCfgs == null) {
            return;
        }
        progress.setTotal(effectCfgs.length);
        for (File effectCfg : effectCfgs) {
            try {
                String name = effectCfg.getName();
                if (!name.endsWith(cfgSuffix)) {
                    continue;
                }

                name = name.substring(0, name.length() - cfgSuffix.length());
                IDManager.getID(name);
                nameInit.Init(name);
            } finally {
                progress.done();
            }
        }
    }

    interface NameInit {
        void Init(String name) throws Exception;
    }
}
