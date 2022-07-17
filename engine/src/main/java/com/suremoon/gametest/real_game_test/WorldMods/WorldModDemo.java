package com.suremoon.gametest.real_game_test.WorldMods;

import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.mods.WorldModItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.gametest.real_game_test.ai_about.FirstFarmerAI;
import com.suremoon.gametest.real_game_test.diedos.MonsterDieDo;

public class WorldModDemo implements WorldModItf {
  @Override
  public void Do(WorldItf gr) {
    UnitItf farmer = gr.productUnit(IDManager.getID("T_lumberjack"));
    farmer.setPos(5000, 5000);
    farmer.getAttribute().setSpd(150);
    farmer.setDieDo(MonsterDieDo.Instance);
    farmer.setUnitRem(new FirstFarmerAI());
    //        farmer.addCmd(CmdInfManager.CIM.productCommand(IDManager.getID("CmdAttack"), farmer,
    // new Point(100, 100), null, gr.getGm()));
    //        farmer.addCmd(CmdInfManager.CIM.productCommand(IDManager.getID("CmdWalk"), farmer, new
    // Point(500, 500), null, gr.getGm()));
    //        farmer.addCmd(CmdInfManager.CIM.productCommand(IDManager.getID("CmdWalk"), farmer, new
    // Point(5000, 5000), null, gr.getGm()));
    gr.getGameMap().getUnitMgr().addUnit(farmer);
    EffectItf e = gr.productEffect(IDManager.getID("LightingStrike"), farmer);
    e.setPos(5500, 5500);
    gr.getGameMap().getEffectMgr().addEffect(e);
    e = gr.productEffect(IDManager.getID("heal"), farmer);
    e.setPos(4500, 4500);
    gr.getGameMap().getEffectMgr().addEffect(e);
  }

  @Override
  public String ModName() {
    return "WorldModDemo";
  }
}
