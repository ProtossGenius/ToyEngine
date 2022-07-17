package com.suremoon.game.ag_pc_client.resource.image.res_type;

import com.suremoon.game.door.client.PicAreaArrayItf;
import com.suremoon.game.door.code_tools.Pair;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;

import java.awt.*;
import java.util.Map;

/**
 * Created by Water Moon on 2017/11/28.
 */
public class NADNResourceIniter {
    public static void initResource(String base_path, Color trans, int direct_nums, Map<String, PicAreaArrayItf> m, int num_length, String ext, Pair<String, Integer>... actions){
        for(int i = 0; i < actions.length; ++i){
            initResource(base_path, trans,direct_nums, m, num_length, actions[i].getKey(), actions[i].getValue(), ext);
        }
    }
    public static void initResource(String base_path, Color trans, int direct_nums, Map<String, PicAreaArrayItf> m, int num_length, String name, int length, String ext){
        ConfigResInf cri = new NADNConfigRes();
        String [][] pga = cri.getResList(base_path, name, direct_nums, length, num_length, ext);
        String []directList = NADNConfigRes.directList;
        for(int i = 0; i < pga.length; ++i){
            m.put(name + " " + directList[i], new PicAreaArray(pga[i], trans));
        }
    }
}
