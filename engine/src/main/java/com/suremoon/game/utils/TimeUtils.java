package com.suremoon.game.utils;

import java.util.concurrent.Callable;

public class TimeUtils {
  public static void timeCost(Callable<Object> run) throws Exception{
    timeCost(run, "%d", 0L);
  }

  public static void timeCost(Callable<Object> run, String fmt, Long mt) throws Exception {
    Long begin = System.currentTimeMillis();
    run.call();
    Long end = System.currentTimeMillis();
    if ((end - begin) > mt) {
      System.out.printf(fmt, (end - begin));
    }
  }

  public static void safeTimeCost(Callable<Object> run, String fmt, Long mt) {
    try{
      timeCost(run, fmt, mt);
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

}
