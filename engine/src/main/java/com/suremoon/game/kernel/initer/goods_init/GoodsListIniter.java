package com.suremoon.game.kernel.initer.goods_init;

import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Progress;

/**
 * Created by Water Moon on 2018/3/5.
 */
public class GoodsListIniter implements InitListItf {
    @Override
    public void init(Progress progress) throws Exception {
        loadList("configs/goods_config", ".xml", name -> new GoodsIniter(name).init(progress), progress);
    }
}
