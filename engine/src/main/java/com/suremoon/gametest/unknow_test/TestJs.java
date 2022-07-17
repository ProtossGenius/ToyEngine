package com.suremoon.gametest.unknow_test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/** Created by Water Moon on 2018/4/17. */
public class TestJs {
  public static void main(String[] args) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine se = manager.getEngineByName("javascript");
    try {
      //            se.eval("var ccc = Java.type('test.watermoon.game.unknow_test.TestCalc');");
      //            se.eval("var doCalc = new ccc();");
      se.eval("function doAdd(calc, a, b){return calc.add(a, b);}");
      se.eval("function doChange(calc){calc.x = 5;}");

      if (se instanceof Invocable) {
        Invocable invoke = (Invocable) se;
        TestCalc tc = new TestCalc();
        Integer ti = (Integer) invoke.invokeFunction("doChange", tc);
        System.out.println(tc.x);
      }
    } catch (Exception e) {
      System.out.println("Error happened in TestJs, error message is: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
