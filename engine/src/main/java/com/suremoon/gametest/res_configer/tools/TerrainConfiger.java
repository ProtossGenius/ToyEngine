package com.suremoon.gametest.res_configer.tools;

import net.sf.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Water Moon on 2018/4/14.
 */
public class TerrainConfiger {
    public static void main(String[] args) throws Exception {
        File tclF = new File("resource/maps");
        LinkedList<String> names = new LinkedList<>();
        File[] tcs = tclF.listFiles();
        BufferedImage bi = null;

        for(File tc: tcs){
            if(tc.isDirectory())continue;
            String name = tc.getName().split("\\.")[0];
            try {
                bi = ImageIO.read(new File("resource/maps/Autotiles/" + name + ".png"));
            } catch (IOException e) {
                System.out.println("resource/maps/Autotiles/" + name + ".png");
                return;
            }
            WalkablePair wp = new WalkablePair();
            getWalkAble(name, wp);
            String wO = wp.wO, wI = wp.wI;
            String isD = isDecoration(bi) ? "true" : "false";
            names.add(name);
            write("configs/terrain_config/" + name + "T.xml", tc.getPath(), isD, wO, wI, getColor(bi));
        }
        writeListConfig(names);
    }

    static int getColor(BufferedImage bi){
        long col_red = 0, col_green = 0, col_blue = 0;
        int size = bi.getWidth() * bi.getHeight();
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                Color c = new Color(bi.getRGB(i, j));
                col_red += c.getRed();
                col_blue += c.getBlue();
                col_green += c.getGreen();
            }
        }
        col_red /= size;
        col_green /= size;
        col_blue /= size;
        return new Color((int)col_red, (int)col_green, (int)col_blue).getRGB();
    }

    static boolean isDecoration(BufferedImage bi)  {
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                if(istrans(bi.getRGB(i, j)))
                    return true;
            }
        }
        return false;
    }
    static boolean istrans(int color){
        return (color >> 24) == 0;
    }

    static void getWalkAble(String name, WalkablePair walkable){
        String wO = "true", wI = "true";
        if(name.contains("Water") || name.contains("Ocean") || name.contains("Lava") || name.contains("Tree")){
            wO = wI = "false";
        }else if(name.contains("atmp")){
            if (name.contains("001") || name.contains("003c") || name.contains("008") || name.contains("009e")){
                //do nothing
            }
            else if(name.contains("006")){
                wI = wO = "false";
            }
            if(name.contains("007c")){
                wI = "false";
            }else
                wO = "false";
        }
        walkable.setWalkable(wO, wI);
    }

    static class WalkablePair{
        public String wO, wI;

        public WalkablePair() {
            wI = wO = "";
        }

        public void setWalkable(String wO, String wI){
            this.wO = wO;
            this.wI = wI;
        }
    }

    static void writeListConfig(LinkedList<String> names) throws Exception {
        FileWriter fw = new FileWriter("configs/terrains.xml");
        fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Root>\n" +
                "    <Terrains>\n");
        for(String name: names){
            fw.write("        <Terrain name=\"" + name + "\"></Terrain>\n");
        }
        fw.write("    </Terrains>\n" +
                "</Root>");
        fw.close();
    }

    static String colorTrans(int color){
        color &= 0x00ffffff;
        String c_16 = Integer.toString(color, 16);
        while(c_16.length() < 6){
            c_16 = "0" + c_16;
        }
        return "0x" + c_16;
    }

    static void write(String path, String resPath, String isDecorate, String walkableO, String walkableI, int color) throws Exception{
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Root>\n");
        fileWriter.write("\t<ResFile>" + resPath + "</ResFile>\n");
        fileWriter.write("\t<IsDecorate>" + isDecorate + "</IsDecorate>\n");
        fileWriter.write("\t<AverageColor>" + colorTrans(color) + "</AverageColor>\n");
        fileWriter.write("\t<Buffs>\n ");
        fileWriter.write("\t</Buffs>\n");
        JSONObject jo = new JSONObject();
        jo.put("Type", "WalkableOI");
        jo.put("WalkableO", walkableO);
        jo.put("WalkableI", walkableI);
        fileWriter.write("\t<Walkable>" + jo.toString() + "</Walkable>\n");
        fileWriter.write("</Root>");
        fileWriter.flush();
        fileWriter.close();
    }
}
