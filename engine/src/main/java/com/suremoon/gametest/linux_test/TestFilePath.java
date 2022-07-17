package com.suremoon.gametest.linux_test;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.config.exceptions.NoSuchConfigException;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import org.xml.sax.SAXException;

public class TestFilePath {
  public static void main(String[] args)
      throws ParserConfigurationException, NoSuchConfigException, SAXException,
          TransformerConfigurationException, IOException {
    File f = new File("resource\\T_lumberjack\\config.xml");
    System.out.println(f.getAbsoluteFile());
    System.out.println(f.getCanonicalPath());
    System.out.println(f.getPath());
    EasyConfig ec = new EasyConfig(getLinuxPath("resource\\T_lumberjack\\config.xml"));
  }

  public static String getLinuxPath(String path) throws IOException {
    return new File(path).getCanonicalPath().replaceAll("\\\\", "/");
  }
}
