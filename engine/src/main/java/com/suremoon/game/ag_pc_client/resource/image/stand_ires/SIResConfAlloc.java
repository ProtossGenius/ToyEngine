package com.suremoon.game.ag_pc_client.resource.image.stand_ires;

import com.springmoon.sm_form.config.exceptions.NoSuchConfigException;
import com.springmoon.sm_form.config.exceptions.NotLeafException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Water Moon on 2018/3/27.
 */
public class SIResConfAlloc {
    public HashMap<String, StandIResConf> sircs;
    public static final SIResConfAlloc SIRCA = new SIResConfAlloc();

    public static SIResConfAlloc getSirca(){
        return SIRCA;
    }

    protected SIResConfAlloc(){
        sircs = new HashMap<>();
    }

    public StandIResConf getSIRC(String confPath) throws TransformerConfigurationException, SAXException, NotLeafException, ParserConfigurationException, NoSuchConfigException, IOException {
        if(sircs.containsKey(confPath)){
            return sircs.get(confPath);
        }
        StandIResConf rc = new StandIResConf(confPath);
        sircs.put(confPath, rc);
        return rc;
    }

    public StandIResConf sgetSIRC(String confPath){//safe get, not throw exceptions
        try {
            return getSIRC(confPath);
        }  catch (Exception e) {
            e.printStackTrace();
        }finally {
            return null;
        }
    }
}
