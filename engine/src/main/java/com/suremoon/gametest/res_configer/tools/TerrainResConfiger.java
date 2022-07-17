package com.suremoon.gametest.res_configer.tools;

import com.suremoon.game.ag_pc_client.resource.image.ImageFactory;
import com.suremoon.game.ag_pc_client.resource.image.SMImage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Water Moon on 2018/4/14.
 */
public class TerrainResConfiger {
    public static void main(String[] args) throws Exception{
        File f_pic = new File("resource/maps/Autotiles"), f_xml = new File("resource/maps");
        File[] pics = f_pic.listFiles();
        for(int i = 0; i < pics.length; ++i){
            writeTcXml(pics[i]);
        }
    }

    static void writeTcXml(File pic) throws IOException {
        String path = "Autotiles/" + pic.getName();
        SMImage smi = ImageFactory.getSMImage("resource/maps/" + path);
        String tt = getTerrainType(smi), tn = getTerrainName(pic), lns = "1";
        if(tt.equals("T12S")){
            lns = getT12SLns(smi);
        }
        doTcWrite(path, tn, tt, lns);
    }

    static void doTcWrite(String path, String terrainName, String terrainType, String typeLength) throws IOException {
        FileWriter fw = new FileWriter("resource/maps/" + terrainName + ".xml");
        fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Root>\n");
        fw.write("\t<Type>" + terrainType + "</Type>\n");
        if(terrainType.equals("T12S")){
            fw.write("<Length>" + typeLength + "</Length>\n");
        }
        fw.write("<ResName>" + path + "</ResName>\n");
        fw.write("</Root>\n");
        fw.flush();
        fw.close();
    }

    static String getTerrainName(File pic){
        return pic.getName().split("\\.")[0];
    }

    static String getTerrainType(SMImage smi) {
        if(smi.getImageWidth() > smi.getImageHeight()){
            return "T12S";
        }
        return "T12";
    }
    static String getT12SLns(SMImage smi){
        return Integer.toString(smi.getImageWidth() / 3 * 4 / smi.getImageHeight());
    }
}
