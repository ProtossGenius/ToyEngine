package com.suremoon.game.ag_pc_client.resource.image.res_type;

/**
 * Created by Water Moon on 2017/11/29.
 */
public class NADNConfigRes implements ConfigResInf {//--> '_' means space.like ->|name e0001.bmp|<-
    public NADNConfigRes(){}
    public static final String directList[] = {"e", "w", "s", "n", "se", "sw", "ne", "nw"};
    @Override
    public String[] getResList(String base_path, String name, String direct, int length, int num_length, String ext){
        String []rt = new String[length];
        for (int i = 0; i < length; i++) {
            rt[i] = base_path + "/" + name + " "+ direct + String.format("%0" + num_length + "d", i) + "." + ext;
        }
        return rt;
    }
    @Override
    public String[][] getResList(String base_path, String name, int directs, int length, int num_length, String ext){// directs =is= (4 or 8), another think is 4
        if (directs == 1){
            String[][] res = new String[1][];
            res[0] = getResList(base_path, name, "", length, num_length, ext);
            return res;
        }
        if(directs != 4 && directs != 8)directs = 4;
        String [][]rt = new String[directs][];
        for (int i = 0; i < directs; i++) {
            rt[i] = getResList(base_path, name, directList[i], length, num_length, ext);
        }
        return rt;
    }
}
