package com.suremoon.game.ag_pc_client.resource.image.xml_init;

import com.springmoon.sm_form.config.EasyConfig;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.door.client.PicAreaArrayItf;
import com.suremoon.game.kernel.linux_use.PathDeal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Water Moon on 2018/2/26.
 */
public class XmlAnalysisFactory {
    protected XmlAnalysisFactory() {
        als = new HashMap<>();
    }

    protected static boolean isFirst = true;
    public static final XmlAnalysisFactory xaf = new XmlAnalysisFactory();

    public static XmlAnalysisFactory getXAF() {
        if (isFirst) {
            isFirst = false;
            Init();
        }
        return xaf;
    }

    protected static void Init() {
        new NADNXmlAnalysis();
        new T12XmlAnalysis();
        new T12SXmlAnalysis();
        new RLSRLXmlAnalysis();
        new SAXmlAnalysis();
    }

    protected Map<String, ResXmlAnalysis> als;

    public void putAnalysis(String name, ResXmlAnalysis rxa) {
        als.put(name, rxa);
    }

    public void putAnalysis(ResXmlAnalysis rxa) {
        als.put(rxa.getName(), rxa);
    }

    public ResXmlAnalysis getRXA(String name) {
        return als.get(name);
    }

    public void Init(Map<String, PicAreaArrayItf> res, String xml_path) throws Exception {
        String base_path = PathDeal.getLinuxFile(xml_path).getParent();
        EasyConfig ec = new EasyConfig(xml_path);
        String type = ec.getConfig("Type").getValue();
        XmlAnalysisFactory xaf = XmlAnalysisFactory.getXAF();
        ResXmlAnalysis rxa = xaf.getRXA(type);
        rxa.analysis(ec, res, base_path);
    }
}
