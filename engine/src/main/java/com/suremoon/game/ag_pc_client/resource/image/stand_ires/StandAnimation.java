package com.suremoon.game.ag_pc_client.resource.image.stand_ires;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.config.exceptions.NoSuchConfigException;
import com.springmoon.sm_form.config.exceptions.NotLeafException;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.PicArea;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;

/**
 * Created by Water Moon on 2018/3/27.
 */
public class StandAnimation {
    String describe;
    PicAreaArray paa;
    StandIResConf sircs[];

    public StandAnimation(String confPath) throws ParserConfigurationException, NoSuchConfigException, SAXException, TransformerConfigurationException, IOException, NotLeafException {
        this(new EasyConfig(confPath));
    }

    public StandAnimation(ConfigInf ec) throws NotLeafException, NoSuchConfigException, SAXException, ParserConfigurationException, TransformerConfigurationException, IOException {
        describe = getValue(ec, "Describe");
        ConfigInf rcc = ec.getConfig("ResConfList");
        ConfigInf rcs[] = rcc.listConfigs();
        sircs = new StandIResConf[rcs.length];
        for(int i = 0; i < rcs.length; ++i){
            String confId = rcs[i].getValue("ConfId"),
                    cPath = rcs[i].getValue("ConfPath");
            sircs[Integer.parseInt(confId)] =SIResConfAlloc.getSirca().getSIRC(cPath);
        }
        ConfigInf res = ec.getConfig("PAList");
        ConfigInf[] rs = res.listConfigs();
        PicArea[] pas = new PicArea[rs.length];
        for(int i = 0; i < rs.length; ++i){
            String confId = rs[i].getValue( "ConfId"), srid =  rs[i].getValue("SRId");
            pas[i] = sircs[Integer.parseInt(confId)].getRes(Integer.parseInt(srid));
        }
        paa = new PicAreaArray(pas);
    }

    public final static String getValue(ConfigInf ci, String type) throws NoSuchConfigException, NotLeafException {
        return ci.getConfig(type).getValue();
    }

    public PicAreaArray getPicAraArray() {
        return paa;
    }

//    public static void main(String[] args) throws TransformerConfigurationException, SAXException, NotLeafException, ParserConfigurationException, NoSuchConfigException, IOException {
//        StandAnimation sa = new StandAnimation("resource/FlowerFireSA.xml");
//        PicAreaArray pa = sa.getPicAraArray();
//        PreViewer pv = new PreViewer();
//        pv.setPicAreaArray(pa);
//        pv.setIntervalTime(60);
//        pv.setVisible(true);
//        pv.showAgsAdapter();
//    }

}
