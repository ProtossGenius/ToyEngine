package com.suremoon.gametest;

import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.ag_pc_client.resource.image.xml_init.XmlAnalysisFactory;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Water Moon on 2018/3/2.
 */
public class PreViewer extends JFrame {
    public PreViewer() {
        super("PreViewer");
        setBounds(200, 200, 600, 600);
        bi = new BufferedImage(GameConfiger.gc.DESIGN_SCREEN_WIDTH, GameConfiger.gc.DESIGN_SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        cache = bi.getGraphics();

    }

    BufferedImage bi;
    Graphics cache;
    Rectangle showRect = new Rectangle(100, 100, 100, 100);
    protected AGSAdapter agsAdapter;
    protected String name;
    protected int intervalTime = 60;
    protected PointF direct = PointF.DIRECTION_ZERO;

    public void showAgsAdapter() {
        setVisible(true);
        long start = System.currentTimeMillis();

        while (true) {
            long now = System.currentTimeMillis();
            cache.clearRect(0, 0, getWidth(), getHeight());
            agsAdapter.show(cache, name, new GRect(showRect), direct, now - start, intervalTime, true, new Point(0, 0));
            getGraphics().drawImage(bi, 0, 0, getWidth(), getHeight(), null);
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public AGSAdapter getAgsAdapter() {
        return agsAdapter;
    }

    public void setAgsAdapter(AGSAdapter agsAdapter, String name) {
        this.agsAdapter = agsAdapter;
        this.name = name;
    }

    public void setPicAreaArray(PicAreaArray paa) {
        AGSAdapter agsAdapter = new AGSAdapter();
        agsAdapter.getResList().put(name = "Resource", paa);
        setAgsAdapter(agsAdapter, name);
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public void setDirect(PointF direct) {
        this.direct = direct;
    }

    public void setShowRect(Rectangle showRect) {
        this.showRect = showRect;
    }

    public static void main(String[] args) throws Exception {
        PreViewer pv = new PreViewer();
        pv.setVisible(true);
        AGSAdapter agsAdapter = new AGSAdapter();
        pv.setAgsAdapter(agsAdapter, "Effect");
        XmlAnalysisFactory xaf = XmlAnalysisFactory.getXAF();
        xaf.Init(agsAdapter.getResList(), "resource/Effect/FlowerFireSA.xml");
        pv.showAgsAdapter();
    }

}
