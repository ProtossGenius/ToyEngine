package com.suremoon.game.netabout;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.function.Supplier;

public class MessageUtil {
    enum MessageType {
        CHAT,
        CMD,
    }

    public static Supplier<String> userChatMessage(String message) {
        return () -> JSON.toJSONString(Map.of("type", MessageType.CHAT, "data", message));
    }
}
