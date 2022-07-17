package com.suremoon.game.kernel.game_run;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.configers.map_resource.file_deal.MapFileReader;
import com.suremoon.game.door.infos.*;
import com.suremoon.game.door.factorys.Factories;
import com.suremoon.game.door.kernel.*;
import com.suremoon.game.door.mods.WorldModItf;
import com.suremoon.game.door.code_tools.JarLoader;
import com.suremoon.game.door.units_itf.*;
import com.suremoon.game.kernel.data.map.GameMap;
import com.suremoon.game.kernel.data.units.time_tools.GameTimer;
import com.suremoon.game.door.error.ErrorDeal;
import com.suremoon.game.kernel.initer.buff_init.BuffInfManager;
import com.suremoon.game.kernel.initer.cmd_init.CmdInfManager;
import com.suremoon.game.kernel.initer.effect_init.EffectInfManager;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;
import com.suremoon.game.kernel.initer.terrain_init.TerrainInfManager;
import com.suremoon.game.kernel.initer.unit_init.UnitInfManager;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Water Moon on 2018/3/13.
 * 单一世界
 */
public class World implements WorldItf {
    protected GameMap gm;//todo: MapInfManager product
    protected GameTimer gt = GameTimer.getGt();
    private String name;
    private int worldIndex;
    Factories factories;
    HourglassBox hourglassBox;
    WorldMgrItf worldMgr;

    private Status status = Status.Ready;
    private ArrayList<WorldModItf> modItfs = new ArrayList<>();
    public World(WorldMgrItf worldMgr, int index, String cfgPath) throws Exception {
        factories = new Factories(UnitInfManager.UIM, StateInfManager.sm, TerrainInfManager.TIM,
                EffectInfManager.EIM, CmdInfManager.CIM, BuffInfManager.bim);
        if (!new File(cfgPath).exists()){
            throw new FileNotFoundException(cfgPath);
        }
        hourglassBox = new HourglassBox(worldMgr, this);
        EasyConfig ec = new EasyConfig(cfgPath);
        String mapPath = ec.getConfig("MapPath").getValue();
        this.name = ec.getConfig("Name").getValue();
        loadMap(mapPath);
        loadModCfg(ec.getConfig("ModList"));
        setWorldMgr(worldMgr, index);
    }

    private void loadModCfg(ConfigInf ec) throws Exception {
        if (ec == null){
            return;
        }

        try{
            ConfigInf[] list = ec.listConfigs();
            for (ConfigInf it : list){
                if (it == null){
                    continue;
                }
                String  line = it.getValue().trim();
                if(line.equals("") || line.startsWith("#")){
                    continue;
                }
                JarLoader.NewClass(line, (ins)->{
                    if (ins instanceof WorldModItf){
                        modItfs.add((WorldModItf) ins);
                    }else {
                        ErrorDeal.putError("In OneWorld.loadModCfg, class " + line + " not a WorldModItf");
                    }
                });
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String mapPath) throws Exception {
        //init gm
        MapFileReader mfr = new MapFileReader(mapPath);
        MapInformation mi = mfr.getMi();
        gm = new GameMap(mi);
        gm.setWorld(this);
//        gm.setUm(new UnitManager());
//        gm.setEm(new EffectManager());

        gt.Play(System.currentTimeMillis());

    }

    public void run() {
        status = Status.Running;
        // add Mods
        for (WorldModItf mod : modItfs){
            System.out.println("World [" + worldName() + "] Loading Mod [" + mod.ModName() + "], Class Name is : <" + mod.getClass().getName() + ">  ........");
            mod.Do(this);
            System.out.println("World [" + worldName() + "] Load Mod " + mod.ModName() + " Success");
        }
        hourglassBox.start();
    }

    public GameMap getGameMap() {
        return gm;
    }

    private void setWorldMgr(WorldMgrItf worldMgr, int index) {
        this.worldMgr = worldMgr;
        this.gm.setWorldMgr(worldMgr);
        this.worldIndex = index;
    }
    public String worldName() {
        return name;
    }
    public int getWorldIndex(){return worldIndex;}

    @Override
    public int getPieceTime() {
        return WorldMgr.PIECE_TIME;
    }

    @Override
    public void putCmdInf(int i, CmdInformation cmdInformation) {
        factories.getCommandFactory().putCmdInf(i, cmdInformation);
    }

    @Override
    public CmdInformation getCmdInf(int i) {
        return factories.getCommandFactory().getCmdInf(i);
    }

    @Override
    public CommandItf productCommand(int i, Point point, int i1) {
        return factories.getCommandFactory().productCommand(i, point, i1);
    }

    @Override
    public EffectItf productEffect(int i, UnitItf unitItf) {
        EffectItf effect = factories.getEffectFactory().productEffect(i, unitItf);
        pushGRectToCalcQueue(effect);
        return effect;
    }

    @Override
    public void putEffectInf(int i, EffectInformation effectInformation) {
        factories.getEffectFactory().putEffectInf(i, effectInformation);
    }

    @Override
    public EffectInformation getEffectInf(int i) {
        return factories.getEffectFactory().getEffectInf(i);
    }

    @Override
    public StateItf productState(String s) {
        return factories.getStateFactory().productState(s);
    }

    @Override
    public TerrainItf productTerrain(int i) {
        return factories.getTerrainFactory().productTerrain(i);
    }

    @Override
    public TerrainInformation getTerrainInf(int i) {
        return factories.getTerrainFactory().getTerrainInf(i);
    }

    @Override
    public void putTerrainInf(int i, TerrainInformation terrainInformation) {
        factories.getTerrainFactory().putTerrainInf(i, terrainInformation);
    }

    @Override
    public void putUnitInf(int i, UnitInformation unitInformation) {
        factories.getUnitFactory().putUnitInf(i, unitInformation);
    }

    @Override
    public UnitInformation getUnitInf(int i) {
        return factories.getUnitFactory().getUnitInf(i);
    }

    @Override
    public UnitItf productUnit(int i, double v) {
        UnitItf unit = factories.getUnitFactory().productUnit(i, v);
        pushGRectToCalcQueue(unit);
        return unit;
    }

    @Override
    public void InitUnit(UnitItf unitItf) {
        factories.getUnitFactory().InitUnit(unitItf);
    }


    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void save() {

    }

    @Override
    public void pushGRectToCalcQueue(GRectItf gRect) {
        hourglassBox.acceptSand(gRect);
    }

    @Override
    public BuffItf productBuff(String s) {
        return this.factories.getBuffFactory().productBuff(s);
    }
}
