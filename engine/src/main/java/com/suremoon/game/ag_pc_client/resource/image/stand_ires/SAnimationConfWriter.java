package com.suremoon.game.ag_pc_client.resource.image.stand_ires;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Water Moon on 2018/3/28.
 */
public class SAnimationConfWriter {
    protected String confPath;
    protected TreeMap<String, Integer> rcs;
    protected ArrayList<Point> aniRes;//xΪConfId,yΪSRId
    protected String describe;
    public SAnimationConfWriter(String confPath) {
        this.confPath = confPath;
        rcs = new TreeMap<>();
        aniRes = new ArrayList<>();
    }

    public void addNext(String resConf, int rid){
        int rcid = -1;
        if(rcs.containsKey(resConf)){
            rcid = rcs.get(resConf);
        }else{
            rcs.put(resConf, rcid = rcs.size());
        }
        aniRes.add(new Point(rcid, rid));
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void write() throws IOException {
        FileWriter fw = new FileWriter(confPath);
        fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Root>\n");
        fw.write("\t<Type>SA</Type>\n");
        fw.write("\t<Describe>" + describe + "</Describe>\n");
        fw.write("\t<ResConfList>\n");
        Iterator<Map.Entry<String, Integer>> it = rcs.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> kv = it.next();
            fw.write("\t\t<ResConfig ConfId = \"" + kv.getValue() + "\" ConfPath = \"" + kv.getKey() + "\"/>\n");
        }
        fw.write("\t</ResConfList>\n");
        fw.write("\t<PAList>\n");
        for(int i = 0; i < aniRes.size(); ++i){
            Point p = aniRes.get(i);
            fw.write("\t\t<PicArea ConfId=\"" + p.x + "\" SRId = \"" + p.y + "\"/>\n");
        }
        fw.write("\t</PAList>\n");

        fw.write("</Root>");
        fw.flush();
        fw.close();
    }

//    public static void main(String[] args) throws IOException {
//        SAnimationConfWriter sacw = new SAnimationConfWriter("resource/FlowerFireSA.xml");
//        for(int i = 1; i <= 30; ++i){
//            sacw.addNext("resource/Effect/FlowerFire.xml", i);
//        }
//        sacw.write();
//    }
}
