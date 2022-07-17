package com.suremoon.game.kernel.data.command_analysis;

import com.suremoon.game.door.client.ActionData;
import com.suremoon.game.door.client.CmdAnalysisItf;
import com.suremoon.game.door.client.CmdCreatorItf;
import com.suremoon.game.door.client.StateAction;

import java.util.ArrayList;

/**
 * Created by Water Moon on 2018/3/16.
 */
public class CmdAnalysis implements CmdAnalysisItf {
    CANode root;
    ActionData actionList[];
    int actions;// set actionList's max deep is 10.
    protected CmdCreatorItf nowCmdCreator;
    public CmdAnalysis() {
        root = new CANode(new ActionData(-7758521, -7758521, -7758521));
        actionList = new ActionData[32];
        actions = 0;
    }

    public void dropNAction(int n){
        if(n < 0 || n > actions)throw new IndexOutOfBoundsException();
        if(n == actions){
            actions = 0;
            return;
        }
        synchronized (actionList) {
            for (int i = 0; i < actions - n; ++i) {
                actionList[i] = actionList[i + n];
            }
            actions -= n;
        }
    }

    @Override
    public void putAction(ActionData nType){
        if(actions + 1 == actionList.length)
            dropNAction(1);
        if(actions != 0 && actionList[actions-1].equals(nType))return;
        actionList[actions++] = nType;
        tryGetCC();
    }

    public boolean addState(ActionData[] inp, CmdCreatorItf cmdCreator){
        if(inp.length > 10){
            throw new IndexOutOfBoundsException("set actionList's max deep is 10. In CmdAnalysis::addState");
        }
        AddStateJI addStateJI = new AddStateJI(inp, cmdCreator);
        return deepLook(root, addStateJI);
    }

    public boolean addState(ActionData[] inp, int type){
        return addState(inp, new CmdCreator(type));
    }

    public boolean addStateAction(ActionData[] inp, StateAction sa){
        if(inp.length > 10){
            throw new IndexOutOfBoundsException("set actionList's max deep is 10. In CmdAnalysis::addState");
        }
        AddStateActionJI addSA = new AddStateActionJI(inp, sa);
        return deepLook(root, addSA);
    }

    public ActionData[] getActionList() {
        ActionData[] res = new ActionData[actions];
        synchronized (actionList) {
            for (int i = 0; i < res.length; ++i) {
                res[i] = actionList[i];
            }
        }
        return res;
    }

    private boolean deepLook(CANode caNode, JudgeInf ji){
        return ji.doJudge(caNode);
    }

    private void tryGetCC(){
        ICPair icPair = new ICPair();
        GetInfJI gci = new GetInfJI(icPair);
        if(deepLook(root, gci)) {
            dropNAction(icPair.n);
        }
        this.nowCmdCreator = icPair.cc;
    }

    public CmdCreatorItf getCmdCreator(){
        return nowCmdCreator;
    }

    public void ClearCmdCreator(){
        this.nowCmdCreator = null;
    }

    public StateAction getStateAction(){
//        for(int i = 0; i < actions; ++i){
//            System.out.print(actionList[i]+ " ");
//        }
//        System.out.println();
        ICPair icPair = new ICPair();
        GetInfJI gci = new GetInfJI(icPair);
        deepLook(root, gci);
        return icPair.sa;
    }

    protected class DropStateJI implements JudgeInf{
        ActionData[] als;
        public DropStateJI(ActionData[] inp){
            als = inp;
        }
        public boolean do_action(CANode caNode, int index){
            if(index == als.length)return false;
            ArrayList<CANode> nodes = caNode.getNext();
            ActionData atype = als[index];
            for(int i = 0; i < nodes.size(); ++i){
                CANode node = nodes.get(i);
                if(node.getCmdId().equals(atype)){
                    if(index == als.length-1 && node.isLeaf()){
                        nodes.remove(i);
                        return true;
                    }
                    boolean canDelete = do_action(node, index + 1);
                    if(canDelete){
                        if(node.getNext().size() == 0 || node.isLeaf())
                            nodes.remove(node);
                    }
                    return canDelete;
                }
            }
            return false;//not find that action List.
        }

        @Override
        public boolean doJudge(CANode caNode) {
            return do_action(caNode, 0);
        }
    }

    protected class GetInfJI implements JudgeInf{
        ICPair icp;
        /**
         * todo 此处可以优化 2018/3/23
         * dropableSize 是指目前得到的事件队列并不能够匹配成功,但是在某些条件下却可以判断一些旧的命令可以抛弃掉
         * 不能匹配成功的被迫退出情况包括两种------一种是直到最后一个事件都未能寻找到目标,另一种是在寻找的途中发
         * 现并没有适配的命令,被迫退出.后者是需要丢弃事件进行重新判断的,因为暂时客户端的处理压力不会太大,因此这一部
         * 分暂时不必要进行优化,但是在某些情况下,客户端的处理变大之后,这里就需要进行优化了.
         */
        int dropableSize = 0;
        public GetInfJI(ICPair icp){
            this.icp = icp;
        }
        public boolean do_action(CANode caNode, int index){
            if(index == actions)return false;
            ArrayList<CANode> nodes = caNode.getNext();
            ActionData type = actionList[index];
            for(int i = 0; i < nodes.size(); ++i){
                CANode cn = nodes.get(i);
                if(cn.getCmdId().equals(type)){
                    if(cn.getSa().sa_type != StateAction.NOTHING){
                        icp.sa =cn.getSa();
                    }
                    if(cn.isLeaf()) {
                        icp.cc = cn.getCmdCreater();
                        icp.n = index+1;
                        icp.sa = cn.getSa();
                        return true;
                    }else{
                        return do_action(cn, index + 1);
                    }
                }
            }
            dropableSize = 1;
            return false;
        }
        @Override
        public boolean doJudge(CANode caNode) {
            while(true){//set mas deep is 10
                if(!do_action(caNode, 0)){
                    if(dropableSize == 0)return false;
                    dropNAction(dropableSize);
                    dropableSize = 0;
                    return do_action(caNode, 0);
                }else return true;
            }
        }
    }

    protected class AddStateActionJI implements JudgeInf{
        ActionData[] inp;
        StateAction sa;

        public AddStateActionJI(ActionData[] inp, StateAction sa) {
            this.inp = inp;
            this.sa = sa;
        }

        @Override
        public boolean doJudge(CANode caNode) {
            return do_action(caNode, 0);
        }

        public boolean do_action(CANode caNode, int index){
            if(index == inp.length)return false;//已经越界了
            ArrayList<CANode> nodes = caNode.getNext();
            ActionData atype = inp[index];
            for(int i = 0; i < nodes.size(); ++i){
                CANode cn = nodes.get(i);
                if(cn.getCmdId().equals(atype)){
                    //只为已有的非叶子节点的节点添加状态下的动作，因为如果是叶子节点则表示动作已完成，不需要特殊的动作。
                    if(cn.isLeaf()){
                        return false;
                    }
                    if(index == inp.length - 1){
                        cn.setSa(sa);
                        return true;
                    }
                    return do_action(cn, index + 1);
                }
            }
            //在for循环外没有代码意味着不会新创叶子节点，因为如果新创叶子节点的话CmdCreater将是null影响其他地方。
            return false;
        }
    }

    protected class AddStateJI implements JudgeInf{
        ActionData[] inp;
        CmdCreatorItf cmdCreator;
        public AddStateJI(ActionData[] inp, CmdCreatorItf cmdCreator) {
            this.inp = inp;
            this.cmdCreator = cmdCreator;
        }

        @Override
        public boolean doJudge(CANode caNode) {
            return do_action(caNode, 0);
        }
        public boolean do_action(CANode caNode, int index){
            if(index == inp.length)return false;//this means has this must cannot
            ArrayList<CANode> nodes = caNode.getNext();
            ActionData atype = inp[index];
            for(int i = 0; i < nodes.size(); ++i){
                CANode cn = nodes.get(i);
                if(cn.getCmdId().equals(atype)){
                    if(cn.isLeaf()){
                        return false;
                    }
                    /**
                    else if(atype != KeyEvent.VK_CONTROL && atype != KeyEvent.VK_SHIFT && atype != KeyEvent.VK_ALT){
                         如果不是这三个特殊的按键，则说明
                         return false;
                        shift不用于分析，只要shift出现就意味着命令为添加(add)而不是设置(set),该项判断在事件处理相关的地方进行.

                     }
                     */
                    return do_action(cn, index + 1);
                }
            }
            //if exist the node whose cmdId == that id then can't goto there, so there should create node.
            if(index == inp.length-1){//isLeaf
                CANode newNode = new CANode(inp[index], cmdCreator);
                nodes.add(newNode);
                return true;
            }else{
                CANode newNode = new CANode(inp[index]);
                nodes.add(newNode);
                return do_action(newNode, index + 1);
            }
        }

    }

    /**
     * 作为形参传入，基于java的一些特性，对它的改变会被报存并传出。
     */
    static class ICPair{
        public CmdCreatorItf cc;
        public int n;
        public StateAction sa = new StateAction();
    }

    /**
     * 用于递归处理，作为一个
     */
    interface JudgeInf{
        boolean doJudge(CANode caNode);
        /**
         * for(int i = 0; i < caNode.getNext().size(); ++i){
         *     if(can){
         *          do sth.
         *         deepLook(caNode.getNext().get(i), this);
         *     }else{
         *         do another thing.
         *     }
         * }
         */
    }

}
