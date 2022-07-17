package com.suremoon.game.ag_pc_client.resource.image.stand_ires;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.config.exceptions.NoSuchConfigException;
import com.springmoon.sm_form.config.exceptions.NotLeafException;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.ImageFactory;
import com.suremoon.game.ag_pc_client.resource.image.PicArea;
import com.suremoon.game.ag_pc_client.resource.image.SMImage;
import java.awt.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import org.xml.sax.SAXException;

/** Created by Water Moon on 2018/3/27. */
public class StandIResConf {
  protected SMImage smi;
  ResConfNode[] confLists;

  public StandIResConf(String confPath)
      throws ParserConfigurationException, NoSuchConfigException, SAXException,
          TransformerConfigurationException, IOException, NotLeafException {
    this(new EasyConfig(confPath));
  }

  public StandIResConf(ConfigInf ec) throws NoSuchConfigException, NotLeafException {
    String imgPath = ec.getConfig("PicPath").getValue();
    String transColor = ec.getConfig("TransColor").getValue();
    smi = ImageFactory.getSMImage(imgPath);
    smi.setTransparent(new Color(HexToInt(transColor)));
    ec.open("ResList");
    ConfigInf cis[] = ec.listConfigs();
    confLists = new ResConfNode[cis.length + 1];
    confLists[0] =
        new ResConfNode(
            0, smi, "Null", new Point(0, 0), new Point(smi.getImageWidth(), smi.getImageHeight()));
    for (int i = 0; i < cis.length; ++i) {
      String sid = getValue(cis[i], "Id"),
          start = getValue(cis[i], "StartPoint"),
          end = getValue(cis[i], "EndPoint"),
          describe = getValue(cis[i], "Describe");
      int id = Integer.parseInt(sid);
      confLists[id] = new ResConfNode(id, smi, describe, toPoint(start), toPoint(end));
    }
  }

  static int HexToInt(String num) {
    return Integer.parseInt(num.replaceAll("^0[x|X]", ""), 16);
  }
  /**
   * @param ci
   * @param type
   * @return
   * @throws NoSuchConfigException
   * @throws NotLeafException
   */
  public static final String getValue(ConfigInf ci, String type)
      throws NoSuchConfigException, NotLeafException {
    return ci.getValue(type);
  }

  public static final Point toPoint(String inp) {
    String[] xy = inp.split(",");
    xy[0] = xy[0].trim();
    xy[1] = xy[1].trim();
    return new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
  }

  public static class ResConfNode {
    protected int id;
    protected String describe;
    protected Point start, end;
    protected PicArea pa;

    public ResConfNode(int id, SMImage smi, String describe, Point start, Point end) {
      this.id = id;
      this.describe = describe;
      this.start = start;
      this.end = end;
      pa = new PicArea(smi, start, end);
    }

    public int getId() {
      return id;
    }

    public String getDescribe() {
      return describe;
    }

    public PicArea getRes() {
      return pa;
    }

    public Point getStart() {
      return start;
    }

    public Point getEnd() {
      return end;
    }
  }

  public int getSize() {
    return confLists.length;
  }

  public ResConfNode getResConf(int id) {
    return confLists[id];
  }

  public PicArea getRes(int id) {
    return confLists[id].getRes();
  }
}
