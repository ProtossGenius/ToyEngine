package com.suremoon.game.netabout;

import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.netabout.message.MsgCommand;
import com.suremoon.game.door.netabout.message.MsgGoods;
import com.suremoon.game.door.netabout.message.MsgString;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.units_itf.BagManager;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.kernel.data.map.GameScreen;
import com.suremoon.game.kernel.data.units.Command;
import com.suremoon.game.kernel.data.units.Unit;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

public class Player extends Unit implements PlayerItf {
    private final Queue<String> messages = new LinkedList<>();
    private WorldItf world;
    private GameScreenItf screen;
    private GameMapItf gm;
    private BagManager bagManager;

    public Player(MsgUnit mu) {
        super(mu);
    }

    public Player(int uType) {
        super(uType);
    }

    public Player(Unit u) {
        super(u, null);
        for (int i = 0; i < BagManager.MAX_BAG_VALUE; ++i) {
            getBag().add(null);
        }
        this.bagManager = new BagManager(this);
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
    public void addMessage(Supplier<String> msg) {
        synchronized (this.messages) {
            if (this.messages.size() > 5000) {
                this.messages.poll();
            }

            this.messages.add(msg.get());
        }
    }

    @Override
    public BagManager getBagManager() {
        return this.bagManager;
    }
}
