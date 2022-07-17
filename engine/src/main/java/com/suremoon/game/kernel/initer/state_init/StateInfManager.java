package com.suremoon.game.kernel.initer.state_init;

import com.suremoon.game.door.factorys.StateFactory;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.data.units.State;

import java.util.HashMap;

/**
 * Created by Water Moon on 2018/3/5.
 */
public class StateInfManager implements StateFactory {
    public static final StateInfManager sm = new StateInfManager();
    public static StateInfManager getSM(){
        return sm;
    }
    public StateInfManager(){
        state_loopable = new HashMap<>();
    }
    HashMap<String, Boolean> state_loopable;
    public void resize(int size){
        state_loopable.clear();
        ptr = 0;
    }
    int ptr;
    public void put(String state_name, String bool){
        bool = bool.toLowerCase();
        boolean b = bool.equals("true");
        state_loopable.put(state_name, b);
    }
    private boolean StateShouldLoop(String state){
        return state_loopable.get(state);
    }

    @Override
    public State productState(String state){
        if(!state_loopable.containsKey(state)){
            System.err.println("No such state in map. state = " + state);
            throw new NullPointerException();
        }
        if(!state_loopable.containsKey(state)) {
            if(state_loopable.containsKey("paused")){
                state = "paused";
            }
        }
        State ags = new State();
        ags.setUnitType(IDManager.getID(state));
        ags.setLoop(StateShouldLoop(state));
//        ags.setStateDo(StateDoFactory.sdf.getSd(state));
        return ags;
    }

}
