package com.suremoon.game.kernel.game_run;

import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.Status;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.enums.LeaveStatus;
import com.suremoon.game.door.tools.PieceRun;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.UnitItf;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Hourglass extends Thread {
    private WorldMgrItf worldMgr;
    private WorldItf world;
    private int size;
    private ConcurrentLinkedQueue<GRectItf> current = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<GRectItf> finished = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<GRectItf> waiting = new ConcurrentLinkedQueue<>();
    private HourglassBox father;
    private long pieceTime;

    public Hourglass(WorldMgrItf worldMgr, WorldItf world, HourglassBox father) {
        this.worldMgr = worldMgr;
        this.world = world;
        this.father = father;
        this.pieceTime = world.getPieceTime();
    }

    private void swap() {
        ConcurrentLinkedQueue<GRectItf> tmp = current;
        current = finished;
        finished = tmp;
    }

    void acceptSand(GRectItf rect) {
        if (rect == null) return;
        waiting.add(rect);
        ++size;
    }

    public int size() {
        return this.size;
    }

    private void deal(ConcurrentLinkedQueue<GRectItf> queue, long start) {
        while (!queue.isEmpty()) {
            if (System.currentTimeMillis() - start >= pieceTime) {
                pieceTime += 5;
                break;
            }
            GRectItf rect = queue.poll();
            rect.doCalc(world, worldMgr);
            if (rect.isDrop()) {
                if (rect instanceof UnitItf) {
                    world.getGameMap().getUnitMgr().removeUnit((UnitItf) rect);
                } else if (rect instanceof EffectItf) {
                    world.getGameMap().getEffectMgr().removeEffect((EffectItf) rect);
                }
                rect.setDrop(false);
                continue;
            }

            if (rect instanceof UnitItf) {
                UnitItf unit = (UnitItf) rect;
                if (unit.getLeaveStatus() != LeaveStatus.None) {
                    unit.setLeaveStatus(LeaveStatus.Left);
                    world.getGameMap().getUnitMgr().removeUnit((UnitItf) rect);
                    unit.getLeaveAction().AfterLeave(unit, world, worldMgr);
                    continue;
                }
            }
            finished.add(rect);
        }
        while (!queue.isEmpty()) {
            father.acceptSand(queue.poll());
        }
    }

    void actionPerPiece(long start) {
        deal(waiting, start);
        deal(current, start);
        swap();
    }

    @Override
    public void run() {
        while (world.getStatus() != Status.Stop) {
            long start = System.currentTimeMillis();
            boolean canFinish =
                    PieceRun.DoPeaceRun(
                            (int) pieceTime,
                            () -> {
                                if (world.getStatus() == Status.Running) {
                                    actionPerPiece(start);
                                }
                            });
            if (canFinish && pieceTime > world.getPieceTime()) {
                pieceTime = Math.max(pieceTime - 5, world.getPieceTime());
            }
        }
        while (!finished.isEmpty()) {
            father.acceptSand(finished.poll());
        }
        size = 0;
    }
}
