package com.suremoon.game.ag_pc_client.resource.image.res_type;

/** Created by Water Moon on 2018/1/13. */
public interface ConfigResInf {
  String[] getResList(
      String base_path, String name, String direct, int length, int num_length, String ext);

  String[][] getResList(
      String base_path, String name, int directs, int length, int num_length, String ext);
}
