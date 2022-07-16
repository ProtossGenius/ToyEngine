package com.suremoon.game.door.client;

import com.suremoon.game.door.tools.IDManager;

public interface CmdAnalysisItf {
    /**
     * @param nType when client running and get an input event call this function.
     */
    void putAction(ActionData nType);
    default void putAction(int dataType, int dataValue, int valueType){
        putAction(new ActionData(dataType, dataValue, valueType));
    }

    boolean addState(ActionData[] inp, int cmdType);
    default boolean addState(ActionData[] inp, String cmdName){
        return addState(inp, IDManager.getID(cmdName));
    }
    @Deprecated
    default boolean addState(int[] inp, int cmdType){
        return addState(oldErrorDataTrans(inp), cmdType);
    }
    @Deprecated
    default boolean addState(int[] inp, String cmdName){
        return addState(oldErrorDataTrans(inp), IDManager.getID(cmdName));
    }
    /**
     *
     * @param cmdType cmd_type
     * @return is add success.
     */
    default  boolean addState(int cmdType){ return addState(getActionList(), cmdType);  }

    // return saved actions in this analysis-worker.
    ActionData[] getActionList();

    boolean addStateAction(ActionData[] inp, StateAction sa);

    @Deprecated
    default boolean addStateAction(int[] inp, StateAction sa){
        return addStateAction(oldErrorDataTrans(inp), sa);
    }
    CmdCreatorItf getCmdCreator();

    StateAction getStateAction();

    /**
     * @param inp old error data.
     * @return now use.
     */
    @Deprecated
    default ActionData[] oldErrorDataTrans(int[] inp){
        ActionData[] res = new ActionData[inp.length * 2];
        for(int i = 0, j = 0; i < inp.length; ++i){
            if(inp[i] < 0){
                res[j++] = new ActionData(ActionData.DATA_TYPE_MOUSE, -inp[i], ActionData.VALUE_TYPE_PRESS);
                res[j++] = new ActionData(ActionData.DATA_TYPE_MOUSE, -inp[i], ActionData.VALUE_TYPE_REPLASE);
            }else{
                res[j++] = new ActionData(ActionData.DATA_TYPE_KEY_BOARD, inp[i], ActionData.VALUE_TYPE_PRESS);
                res[j++] = new ActionData(ActionData.DATA_TYPE_KEY_BOARD, inp[i], ActionData.VALUE_TYPE_REPLASE);
            }
        }

        return res;
    }
}
