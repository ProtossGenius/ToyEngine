package com.suremoon.game.door.factorys;

import com.suremoon.game.door.units_itf.BuffItf;

public class Factories {
    private UnitFactory unitFactory;
    private StateFactory stateFactory;
    private TerrainFactory terrainFactory;
    private EffectFactory effectFactory;
    private CommandFactory commandFactory;
    private BuffFactory buffFactory;
    public Factories(){}

    public Factories(UnitFactory unitFactory, StateFactory stateFactory, TerrainFactory terrainFactory,
                     EffectFactory effectFactory, CommandFactory commandFactory, BuffFactory buffFactory) {
        this.unitFactory = unitFactory;
        this.stateFactory = stateFactory;
        this.terrainFactory = terrainFactory;
        this.effectFactory = effectFactory;
        this.commandFactory = commandFactory;
        this.buffFactory = buffFactory;
    }


    public BuffFactory getBuffFactory() {
        return buffFactory;
    }

    public void setBuffFactory(BuffFactory buffFactory) {
        this.buffFactory = buffFactory;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }

    public void setUnitFactory(UnitFactory unitFactory) {
        this.unitFactory = unitFactory;
    }

    public StateFactory getStateFactory() {
        return stateFactory;
    }

    public void setStateFactory(StateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }

    public TerrainFactory getTerrainFactory() {
        return terrainFactory;
    }

    public void setTerrainFactory(TerrainFactory terrainFactory) {
        this.terrainFactory = terrainFactory;
    }

    public EffectFactory getEffectFactory() {
        return effectFactory;
    }

    public void setEffectFactory(EffectFactory effectFactory) {
        this.effectFactory = effectFactory;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }
}
