package com.suremoon.game.configers.unit_resource.config_creater;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.TreeMap;
import javax.imageio.ImageIO;

/** Created by Water Moon on 2018/1/13. */
public class CreateNADNConfig {
  File file;
  LinkedList<NADN> nadn;
  String transColor = null;
  int directs;

  public CreateNADNConfig(File file, int directs) throws Exception {
    if (!file.isDirectory()) {
      throw new Exception("need in put file is a directory.");
    }
    this.directs = directs;
    this.file = file;
    nadn = init();
  }

  public CreateNADNConfig(String file, int directs) throws Exception {
    this(new File(file), directs);
  }

  protected LinkedList<NADN> init() throws IOException {
    LinkedList<NADN> lln = new LinkedList<>();
    dealDirectory(lln, file);
    return lln;
  }

  protected void dealDirectory(LinkedList<NADN> lln, File f) throws IOException {
    File[] fils = f.listFiles();
    String father = f.toString().substring(file.toString().length());
    if (father.length() > 1) father = father.substring(1);
    String action_name = null;
    String length = "0";
    for (int i = 0; i < fils.length; ++i) {
      File it = fils[i];
      if (it.isDirectory()) {
        dealDirectory(lln, it);
        continue;
      }
      if (getExtName(it.getName()).equals("txt")) continue;
      String an = getActionName(it.getName(), father);
      if (an.length() == 0) continue;
      String tl = getNum(it.getName());
      if (tl.length() == 0) continue;
      if (action_name == null) action_name = an;
      if (transColor == null) {
        transColor = getTransColor(it);
      }
      ext = getExtName(it.getName());
      if (!action_name.equals(an) || i == fils.length - 1) {
        lln.add(
            new NADN(
                action_name,
                Integer.toString(Integer.valueOf(length) + 1),
                Integer.toString(directs),
                Integer.toString(tl.length())));
        action_name = an;
      }
      length = tl;
    }
  }

  public void setTransColor(String transColor) {
    this.transColor = transColor;
  }

  protected String getTransColor(File f) throws IOException {
    BufferedImage bi = ImageIO.read(f);
    int color = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    int max_times = 0;
    for (int i = 0; i < bi.getWidth(); ++i) {
      int tmp = bi.getRGB(i, 10);
      int times = 0;
      if (map.containsKey(tmp)) {
        times = map.get(tmp);
        ++times;
        map.put(tmp, times);
      } else {
        times = 1;
        map.put(tmp, times);
      }
      if (tmp != color && times > max_times) {
        color = tmp;
      }
    }
    color <<= 8;
    color >>= 8;
    String c_16 = Integer.toString(color, 16);
    while (c_16.length() < 6) {
      c_16 = "0" + c_16;
    }
    return "0x" + c_16;
  }

  public String getActionName(String name, String father) {
    String rn = name.split("\\.")[0];
    String[] tms = rn.split(" ");
    if (rn.length() <= tms[tms.length - 1].length()) return "";
    String acn = rn.substring(0, rn.length() - tms[tms.length - 1].length() - 1);
    if (father.length() == 0) return acn;
    return father + "\\" + acn;
  }

  String ext;

  public String getExtName(String name) {
    String[] tns = name.split("\\.");
    return tns[tns.length - 1];
  }

  public static String Directs[] = new String[] {"e", "n", "ne", "nw", "s", "se", "sw", "w"};

  public String getNum(String name) {
    String rn = name.split("\\.")[0];
    String[] tms = rn.split(" ");
    String dl = tms[tms.length - 1];
    int start_index = -1;
    for (int i = 0; i < dl.length(); ++i) {
      if (dl.charAt(i) >= '0' && dl.charAt(i) <= '9') {
        start_index = i;
        break;
      }
    }
    if (start_index == -1) return "";
    boolean isErr = true;
    String tmp = dl.substring(0, start_index);
    for (int i = 0; i < Directs.length; ++i) {
      if (tmp.equals(Directs[i])) isErr = false;
    }
    if (isErr) return "";
    return dl.substring(start_index);
  }

  public void toXml() throws IOException {
    File oup = new File(file, "config.xml");
    FileWriter fw = new FileWriter(oup);
    fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n <Root>\n");
    fw.write("\t<Type>NADN</Type>\n");
    fw.write("\t<Ext>" + ext + "</Ext>\n");
    fw.write("\t<trans_color>" + transColor + "</trans_color>\n");
    fw.write("\t<Actions>\n");
    for (NADN it : nadn) {
      fw.write(
          "\t\t<action name=\""
              + it.name
              + "\" action_nums=\""
              + it.action_nums
              + "\" directs=\""
              + it.directs
              + "\" numlength=\""
              + it.num_length
              + "\"/>\n");
    }
    fw.write("\t</Actions>\n" + "</Root>\n");
    fw.flush();
    fw.close();
  }

  public static class NADN {
    public String name, action_nums, directs, num_length;

    public NADN(String name, String action_nums, String directs, String num_length) {
      this.name = name;
      this.action_nums = action_nums;
      this.directs = directs;
      this.num_length = num_length;
    }
  }
}
