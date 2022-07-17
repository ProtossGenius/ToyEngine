package com.suremoon.game.kernel.game_run;

import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.Status;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.tools.PieceRun;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HourglassBox extends Thread{
    WorldMgrItf worldMgr;
    WorldItf world;
    LinkedList<Hourglass> hourglasses = new LinkedList<>();
    ConcurrentLinkedQueue<GRectItf> waiting = new ConcurrentLinkedQueue<>();
    public HourglassBox(WorldMgrItf worldMgr, WorldItf world){
        this.worldMgr = worldMgr;
        this.world = world;
        for(int i = 0; i < 16; ++i){
            Hourglass it = new Hourglass(worldMgr, world, this);
            hourglasses.add(it);
            it.start();
        }
    }

    public void acceptSand(GRectItf rect){
        waiting.add(rect);
    }

    private void distributionGRect(){
        int per = waiting.size() / hourglasses.size() + 1;
        for(Hourglass it : hourglasses){
            int expectedCapacity = per;
            for(; expectedCapacity > 0; --expectedCapacity){
                if(!waiting.isEmpty()){
                    it.acceptSand(waiting.poll());
                }
            }
            if(waiting.isEmpty()){
                break;
            }
        }
    }


    @Override
    public void run() {
        while (worldMgr.getStatus() != Status.Stop){
            PieceRun.DoPeaceRun(100, ()->{
                distributionGRect();
            });
        }
    }
}
