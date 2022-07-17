package com.suremoon.game.ag_pc_client.resource.image.stand_ires;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Water Moon on 2018/3/27.
 */
public class SIResConfigWriter {
    protected String confPath, picPath;
    protected ArrayList<StandIResConf.ResConfNode> rcns;
    String transColor;
    public SIResConfigWriter(String confPath, String picPath) {
        this.confPath = confPath;
        this.picPath = picPath;
        rcns = new ArrayList<>();
        try {
            transColor = getTransColor(new File(picPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addResConf(StandIResConf.ResConfNode rc){
        rcns.add(rc);
    }

    protected String getTransColor(File f) throws IOException {
        BufferedImage bi = ImageIO.read(f);
        int color = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int max_times = 0;
        for(int i = 0; i < bi.getWidth(); ++i){
            int tmp = bi.getRGB(i, 10);
            int times = 0;
            if(map.containsKey(tmp)){
                times = map.get(tmp);
                ++times;
                map.put(tmp, times);
            }else{
                times = 1;
                map.put(tmp, times);
            }
            if(tmp != color && times > max_times){
                color = tmp;
            }
        }
        color <<= 8;
        color >>= 8;
        String c_16 = Integer.toString(color, 16);
        while(c_16.length() < 6){
            c_16 = "0" + c_16;
        }
        return "0x" + c_16;
    }

    public void setTransColor(String transColor) {
        this.transColor = transColor;
    }

    public void write() throws IOException {
        FileWriter fw = new FileWriter(confPath);
        fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Root>\n");
        fw.write("\t<Type>SIRC</Type>\n");
        fw.write("\t<PicPath>" + picPath + "</PicPath>\n");
        fw.write("\t<TransColor>" + transColor + "</TransColor>\n");
        fw.write("\t<ResList>\n");
        for(int i = 0; i < rcns.size(); ++i){
            StandIResConf.ResConfNode rc = rcns.get(i);
            fw.write("\t\t<ResConfig Id = \"" + rc.getId() + "\" Describe = \"" + rc.getDescribe() + "\" StartPoint = \"" + PointToString(rc.getStart()) + "\" EndPoint = \"" + PointToString(rc.getEnd()) + "\"/>\n");
        }
        fw.write("\t</ResList>\n");
        fw.write("</Root>");
        fw.flush();
        fw.close();
    }
    public static String PointToString(Point p){
        return p.x + ", " + p.y;
    }

//    public static void main(String[] args) throws IOException {
//        SMImage img = ImageFactory.getSMImage("resource/Effect/FlowerFire.jpg");
//        int width = img.getImageWidth(), height = img.getImageHeight();
//        SIResConfigWriter sircw = new SIResConfigWriter("resource/Effect/FlowerFire.xml", "resource/Effect/FlowerFire.jpg");
//        //6, 5
//        int ptr = 1;
//        for(int i = 0; i < 6; ++i){
//            for(int j = 0; j < 5; ++j){
//                StandIResConf.ResConfNode rcn = new StandIResConf.ResConfNode(ptr, img, "no-need", new Point(width * j / 5, height * i / 6), new Point(width * (j+1) / 5, height * (i+1) / 6));
//                ++ptr;
//                sircw.addResConf(rcn);
//            }
//        }
//        sircw.write();
//
//    }

}
