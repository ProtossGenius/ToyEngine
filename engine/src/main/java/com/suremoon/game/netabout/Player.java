package com.suremoon.game.netabout;

import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.netabout.message.MsgCommand;
import com.suremoon.game.door.netabout.message.MsgGoods;
import com.suremoon.game.door.netabout.message.MsgString;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.kernel.data.map.GameScreen;
import com.suremoon.game.kernel.data.units.Command;
import com.suremoon.game.kernel.data.units.Unit;

import java.util.LinkedList;
import java.util.Queue;

public class Player extends Unit implements PlayerItf {
    private final Queue<String> messages = new LinkedList<>();
    private WorldItf world;
    private GameScreenItf screen;
    private GameMapItf gm;

    public Player(MsgUnit mu) {
        super(mu);
    }

    public Player(int uType) {
        super(uType);
    }

    public Player(Unit u) {
        super(u, null);
    }

    @Override
    public void doCalc(WorldItf world, WorldMgrItf worldMgr) {
        super.doCalc(world, worldMgr);
        this.gm = world.getGameMap();
        this.world = gm.getWorld();
        if (screen != null) {
            screen.setGameMap(gm);
        } else {
            screen = new GameScreen(gm);
        }
    }

    @Override
    public GameScreenItf getScreen() {
        return this.screen;
    }

    @Override
    public WorldItf getWorld() {
        return world;
    }

    @Override
    public void acceptCmd(MsgCommand cmd) {
        super.acceptCmd(new Command(cmd));
    }

    @Override
    public MsgString[] getMessages(int maxLength) {
        MsgString[] res;
        synchronized (this.messages) {
            int realLength = Math.min(maxLength, this.messages.size());
            res = new MsgString[realLength];
            for (int i = 0; i < realLength; ++i) {
                res[i] = new MsgString(this.messages.poll());
            }
        }
        return res;
    }

    @Override
    public MsgGoods[] getBagInfo() {
        return getBag().stream().map(MsgGoods::new).toArray(MsgGoods[]::new);
    }

    @Override
    public void dropBagGoods(WorldItf world, int index) {
        if (index > getBag().size()) {
            return;
        }

        getBag().set(index, null);
    }

    @Override
    public void addMessage(String msg) {
        synchronized (this.messages) {
            if (this.messages.size() > 50) {
                this.messages.poll();
            }

            this.messages.add(msg);
        }
    }
}
