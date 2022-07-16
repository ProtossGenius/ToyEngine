package com.suremoon.game.door.factorys;

import com.suremoon.game.door.units_itf.StateItf;

public interface StateFactory {
    StateItf productState(String state);
}
