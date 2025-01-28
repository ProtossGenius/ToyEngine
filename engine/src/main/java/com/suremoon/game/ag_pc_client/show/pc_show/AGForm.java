package com.suremoon.game.ag_pc_client.show.pc_show;

import com.suremoon.game.ag_pc_client.mkids.MKInpDeal;
import com.suremoon.game.ag_pc_client.mkids.UiMKID;
import com.suremoon.game.ag_pc_client.show.FrequencyCollector;
import com.suremoon.game.ag_pc_client.show.adapters.effect.EffectSAGetter;
import com.suremoon.game.ag_pc_client.show.adapters.unit.UnitSAGetter;
import com.suremoon.game.ag_pc_client.show.showable_rect.string_show.RollingString;
import com.suremoon.game.ag_pc_client.show.showable_rect.string_show.StringShow;
import com.suremoon.game.ag_pc_client.ui.IGameUI;
import com.suremoon.game.ag_pc_client.ui.NoneUI;
import com.suremoon.game.door.client.ScreenInfoUpdateItf;
import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.netabout.message.MsgEffect;
import com.suremoon.game.door.netabout.message.MsgScreenInfo;
import com.suremoon.game.door.netabout.message.MsgString;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.observer.ObserverEnum;
import com.suremoon.game.door.observer.ObserverMgr;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.game.kernel.data.units.Effect;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.game_run.WorldMgr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Water Moon on 2018/3/11.
 */
public class AGForm extends JFrame implements ScreenInfoUpdateItf {
    private final MKInpDeal mkid = UiMKID.instace;
    protected GameScreenItf gs;
    BufferedImage bi;
    Graphics cache;
    MsgScreenInfo screenInfo = new MsgScreenInfo();
    WorldMgrItf worldMgr;
    RollingString rollingString;
    FrequencyCollector nps = new FrequencyCollector(1000);
    FrequencyCollector fps = new FrequencyCollector(1000);
    int lastWorldId = 0;
    private IGameUI ui = new NoneUI();

    public AGForm(String path, int index) throws Exception {
        super();
        setLayout(null);
        this.worldMgr = new WorldMgr(path);
        WorldItf currentWorld = worldMgr.getWorld(index);
        rollingString =
                new RollingString(
                        new StringShow(
                                "",
                                new Rectangle(
                                        GameConfiger.DESIGN_SCREEN_WIDTH - 500,
                                        50,
                                        500,
                                        GameConfiger.DESIGN_SCREEN_HEIGHT - 50)));
        bi =
                new BufferedImage(
                        GameConfiger.DESIGN_SCREEN_WIDTH,
                        GameConfiger.DESIGN_SCREEN_HEIGHT,
                        BufferedImage.TYPE_INT_ARGB);
        cache = bi.getGraphics();
        gs = currentWorld.getGameMap().createGameScreen();
        gs.setMoveLength(35);
        gs.setFocusPoint(0, 0);
        setBounds(0, 0, 600, 600);
        GameConfiger.gc.setScreenSize(600, 600);
        enableInputMethods(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addComponentListener(
                new ComponentAdapter() {
                    public void componentResized(ComponentEvent e) {
                        super.componentResized(e);
                        GameConfiger.gc.setScreenSize(AGForm.this.getWidth(), AGForm.this.getHeight());
                    }
                });
        setUndecorated(true);
        getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        UiMKID.instace.setGameUI(ui);
        mkid.setParent(this);
    }


    public void setUI(IGameUI gameUI) {
        this.ui = gameUI;
        UiMKID.instace.setGameUI(gameUI);
    }

    @Override
    public void update(MsgScreenInfo screenInfo) {
        synchronized (this.screenInfo) {
            nps.Tick();
            this.screenInfo = screenInfo;
            AGMessage[] talkMsg = this.screenInfo.getShowInfo()[3];
            for (int i = 0; i < talkMsg.length; ++i) {
                String str = ((MsgString) talkMsg[i]).getStr();
                System.out.println(str);
                rollingString.insertString(str);
            }
        }
    }

    public WorldMgrItf getWorldMgr() {
        return worldMgr;
    }

    public MsgScreenInfo getScreenInfo() {
        return screenInfo;
    }

    public GameScreenItf getGameScreen() {
        return gs;
    }

    public void draw(Graphics g) {

    }

    public WorldItf getCurrentWorld() {
        return worldMgr.getWorld(lastWorldId);
    }

    public void gameLoop() {

        while (true) {
            try {
                long start = System.currentTimeMillis();
                MsgScreenInfo info;
                synchronized (this.screenInfo) {
                    info = this.screenInfo;
                }
                if (info.getWorldIndex() != lastWorldId) {
                    lastWorldId = info.getWorldIndex();
                    gs.setGameMap(worldMgr.getWorld(lastWorldId).getGameMap());
                    this.mkid.keyPressed(new KeyEvent(this, KeyEvent.VK_SPACE, 0, 0, KeyEvent.VK_SPACE, ' '));
                }

                AGMessage[][] msgs = info.getShowInfo();
                gs.screenMove();
                cache.clearRect(0, 0, 1000, 1000);
                gs.showMap(cache);
                ObserverMgr.mgr.submit(ObserverEnum.GOODS, msgs[4]);
                if (msgs[0].length != 0) {
                    ObserverMgr.mgr.submit(ObserverEnum.SELECTED_GOODS, ((MsgUnit) msgs[0][0]).selectedGoods);
                }
                for (int i = 0; i < msgs[1].length; ++i) {
                    Unit unit = new Unit((MsgUnit) msgs[1][i]);
                    UnitSAGetter.getUsag().show(cache, unit, gs.getFocusPoint());
                }
                for (int i = 0; i < msgs[2].length; ++i) {
                    Effect e = new Effect((MsgEffect) msgs[2][i]);
                    EffectSAGetter.getTSAG().show(cache, e, gs.getFocusPoint());
                }
                StringShow clientInfo =
                        new StringShow(
                                "GRect num: "
                                        + (msgs[1].length + msgs[2].length)
                                        + "\n"
                                        + "Fps : "
                                        + fps.See()
                                        + "\n"
                                        + "Nps : "
                                        + nps.See(),
                                new Rectangle(GameConfiger.DESIGN_SCREEN_WIDTH - 230, 30, 200, 800));
                clientInfo.drawOn(cache);
//        Point mousePos = mkid.getLastPoint();
//        StringShow showMousePos =
//            new StringShow(
//                "mouse's real pos = (" + mousePos.x + ", " + mousePos.y + ")",
//                new Rectangle(100, 30, 600, 200));
//        showMousePos.setColor(Color.RED);
//        showMousePos.drawOn(cache);
                rollingString.drawOn(cache);
                ui.draw(cache);
                getGraphics().drawImage(bi, 0, 0, getWidth(), getHeight(), null);
                fps.Tick();
                long passTime = System.currentTimeMillis() - start;
                if (passTime < 15) {
                    Thread.sleep(15 - passTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
