package com.suremoon.game.configers.map_resource.frames;

import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.configers.JPreview;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.initer.InitManager;
import com.suremoon.game.kernel.initer.terrain_init.TerrainInfManager;
import com.suremoon.game.door.infos.TerrainInformation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Water Moon on 2018/5/25.
 */
public class TerrainChoice extends JFrame {
    JLabel l_terrainList;
    JComboBox cb_terrainList;
    JPreview terrainView[];
    AGSAdapter adapter;
    MapEditor me;

    public TerrainChoice(MapEditor me) throws Exception {
        super("地形选择");
        setLayout(null);
        InitManager.init();
        this.me = me;
        setBounds(100, 100, 445, 275);
        initComponent();
        previewShow();
        setResizable(false);
    }

    public void setTerrainType(int val) {
        TerrainInformation ti = TerrainInfManager.getTIM().getTerrainInf(val);
        adapter = ti.getShower();
    }

    private void initComponent() {
        l_terrainList = new JLabel("地形列表");
        l_terrainList.setBounds(12, 24, 202, 23);
        add(l_terrainList);

        cb_terrainList = new JComboBox();
        cb_terrainList.setBounds(12, 53, 202, 23);
        add(cb_terrainList);
        HashMap<Integer, TerrainInformation> hash = TerrainInfManager.getTIM().getTihm();
        for (Map.Entry<Integer, TerrainInformation> it : hash.entrySet()) {
            if (!it.getValue().isDecorate)
                cb_terrainList.addItem(IDManager.getName(it.getKey()));
        }
        cb_terrainList.addActionListener((ActionEvent e) -> {
            me.previewShow();
            previewShow();
        });

        terrainView = new JPreview[12];
        final int PRE_WIDTH = 55, PRE_HEIGHT = 55;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                int idx = i * 4 + j;
                terrainView[idx] = new JPreview();
                terrainView[idx].setBounds(239 + i * PRE_WIDTH, 10 + j * PRE_HEIGHT, PRE_WIDTH, PRE_HEIGHT);
                add(terrainView[idx]);
            }
        }
    }

    protected void previewShow() {
        TerrainInformation ti = TerrainInfManager.getTIM().getTerrainInf(getTerrainName());
        adapter = ti.getShower();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                int idx = i * 4 + j;
                terrainView[idx].close();
                terrainView[idx].setAgsAdapter(adapter, "Terrain" + j+ i);
                terrainView[idx].showAgsAdapter();
            }
        }
    }

    public String getTerrainName() {
        return (String) cb_terrainList.getSelectedItem();
    }

    public AGSAdapter getAdapter() {
        return adapter;
    }
}
