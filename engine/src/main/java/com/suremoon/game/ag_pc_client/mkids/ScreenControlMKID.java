package com.suremoon.game.ag_pc_client.mkids;

import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import java.awt.event.KeyEvent;

public class ScreenControlMKID extends MKInpDeal {
  UnitItf player;
  GameScreenItf gs;

  public ScreenControlMKID(MKInpDeal mkid, UnitItf player, GameScreenItf gameScreen) {
    super(mkid);
    this.player = player;
    this.gs = gameScreen;
    focusPlayer();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      focusPlayer();
    }
  }

  public void focusPlayer() {
    gs.setFocusPoint(
        player.getX() + player.getWidth() / 2 - GameConfiger.DESIGN_SCREEN_WIDTH / 2,
        player.getY() + player.getHeight() / 2 - GameConfiger.DESIGN_SCREEN_HEIGHT / 2);
  }
}
