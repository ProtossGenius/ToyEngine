package com.suremoon.gametest.res_configer;

import com.suremoon.game.configers.unit_resource.config_creater.CreateNADNConfig;

/** Created by Water Moon on 2018/1/13. */
public class TestNADNConfig {
  public static void main(String[] args) throws Exception {
    CreateNADNConfig cnc = new CreateNADNConfig("resources/T_lumberjack", 8);
    cnc.toXml();
  }
}
