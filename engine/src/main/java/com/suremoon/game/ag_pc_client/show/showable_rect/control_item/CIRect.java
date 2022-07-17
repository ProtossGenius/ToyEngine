package com.suremoon.game.ag_pc_client.show.showable_rect.control_item;

import com.suremoon.game.ag_pc_client.show.showable_rect.control_item.input_about.EventIncoming;
import com.suremoon.game.door.gometry.GRect;


/**
 * 认为控件（ControlItem）是一个矩形，可以响应鼠标和键盘事件。
 */
public abstract class CIRect extends GRect implements EventIncoming {
    protected int state;//press, mouse pass etc. show-use. should only change by input-event.
    public int getState() {
        return state;
    }
}
