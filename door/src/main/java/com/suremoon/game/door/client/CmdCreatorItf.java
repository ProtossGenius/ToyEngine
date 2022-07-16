package com.suremoon.game.door.client;

import com.suremoon.game.door.units_itf.CommandItf;

import java.awt.*;

public interface CmdCreatorItf {
    CommandItf productCommand(int target, Point lastPoint);
}
