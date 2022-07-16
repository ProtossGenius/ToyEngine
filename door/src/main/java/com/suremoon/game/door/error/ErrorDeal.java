package com.suremoon.game.door.error;

import com.suremoon.game.door.tools.MSLoger;

/**
 * Created by Water Moon on 2018/3/5.
 */
public class ErrorDeal {
    public static void putError(String str){
        System.err.println(str);
        MSLoger.writeFLog(str);
    }
}
