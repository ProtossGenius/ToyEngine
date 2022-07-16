package com.suremoon.game.door.infos;

import com.suremoon.game.door.code_tools.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Water Moon on 2018/5/24.
 */
public class MapInformation {
    String version;
    int cols, rows, twidth, theight;
    Map<Integer, String> tConfs = new HashMap<>();
    Pair<Integer, Integer> tmap[][];

    long terrainStart;

    public MapInformation() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Map<Integer, String> gettConfs() {
        return tConfs;
    }

    public void settConfs(Map<Integer, String> tConfs) {
        this.tConfs = tConfs;
    }

    public long getTerrainStart() {
        return terrainStart;
    }

    public void setTerrainStart(long terrainStart) {
        this.terrainStart = terrainStart;
    }

    public Pair<Integer, Integer>[][] getTmap() {
        return tmap;
    }

    public void setTmap(Pair<Integer, Integer>[][] tmap) {
        this.tmap = tmap;
    }

    public void setTerrain(int x, int y, Pair<Integer, Integer> terrain){
        if(x < 0 || y < 0 || x >= getCols() || y >= getRows()){
            return;
        }
        tmap[x][y] = terrain;
    }

    public int getTwidth() {
        return twidth;
    }

    public void setTwidth(int twidth) {
        this.twidth = twidth;
    }

    public int getTheight() {
        return theight;
    }

    public void setTheight(int theight) {
        this.theight = theight;
    }

    public Point getSize(){
        return new Point(twidth, theight);
    }

}
