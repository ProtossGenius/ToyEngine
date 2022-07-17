package com.suremoon.gametest.progress;

import com.suremoon.game.ag_pc_client.show.pc_show.DraftForm;
import com.suremoon.game.door.attribute.ComplexAttribute;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.initer.InitManager;
import com.suremoon.game.kernel.initer.Progress;

import java.awt.*;

public class TestProgress {

  public static void main(String[] args) {
    DraftForm draftForm = new DraftForm();
    draftForm.setTitle("Loading resource");
    draftForm.setBounds(100, 100, 800, 800);
    Progress mainProgress = new Progress();
    Unit u = new Unit(0);
    mainProgress.setCallback(
        (total, current) -> {
          if (total == 0) {
            System.out.println("total = 0");
            return;
          }

          u.getAttribute().setMaxHp(total);
          u.getAttribute().setHp(current);
        },
        () -> {
          System.out.println("success.");
        });
    Progress subProgress = new Progress();
    subProgress.setCallback(
        (total, current) -> {
          System.out.println("call back: " + current + "/" + total);
          if (total == 0) {
            System.out.println("is 0");
            return;
          }

            u.getAttribute().setMaxMp(total);
            u.getAttribute().setMp(current);
        },
        () -> {
          throw new RuntimeException("success");
        });
    new Thread(
            () -> {
              try {
                InitManager.init(mainProgress, subProgress);
              } catch (Exception e) {
                e.printStackTrace();
              }
            })
        .start();

    draftForm.draw(
        graphics -> {
            ComplexAttribute attribute = u.getAttribute();
            graphics.setColor(Color.BLUE);
            graphics.fillRect(0, 100, (int)(600 * attribute.getHp() / attribute.getMaxHp()), 100);
            graphics.setColor(Color.GREEN);
            graphics.fillRect(0, 300, (int)(600 * attribute.getMp() / attribute.getMaxMp()), 100);
        });
  }
}
