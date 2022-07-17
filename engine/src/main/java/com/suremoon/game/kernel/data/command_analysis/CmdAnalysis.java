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
         * todo �˴������Ż� 2018/3/23
         * dropableSize ��ָĿǰ�õ����¼����в����ܹ�ƥ��ɹ�,������ĳЩ������ȴ�����ж�һЩ�ɵ��������������
         * ����ƥ��ɹ��ı����˳������������------һ����ֱ�����һ���¼���δ��Ѱ�ҵ�Ŀ��,��һ������Ѱ�ҵ�;�з�
         * �ֲ�û�����������,�����˳�.��������Ҫ�����¼����������жϵ�,��Ϊ��ʱ�ͻ��˵Ĵ���ѹ������̫��,�����һ��
         * ����ʱ����Ҫ�����Ż�,������ĳЩ�����,�ͻ��˵Ĵ�����֮��,�������Ҫ�����Ż���.
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
            if(index == inp.length)return false;//�Ѿ�Խ����
            ArrayList<CANode> nodes = caNode.getNext();
            ActionData atype = inp[index];
            for(int i = 0; i < nodes.size(); ++i){
                CANode cn = nodes.get(i);
                if(cn.getCmdId().equals(atype)){
                    //ֻΪ���еķ�Ҷ�ӽڵ�Ľڵ����״̬�µĶ�������Ϊ�����Ҷ�ӽڵ����ʾ��������ɣ�����Ҫ����Ķ�����
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
            //��forѭ����û�д�����ζ�Ų����´�Ҷ�ӽڵ㣬��Ϊ����´�Ҷ�ӽڵ�Ļ�CmdCreater����nullӰ�������ط���
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
                         �����������������İ�������˵��
                         return false;
                        shift�����ڷ�����ֻҪshift���־���ζ������Ϊ���(add)����������(set),�����ж����¼�������صĵط�����.

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
     * ��Ϊ�βδ��룬����java��һЩ���ԣ������ĸı�ᱻ���沢������
     */
    static class ICPair{
        public CmdCreatorItf cc;
        public int n;
        public StateAction sa = new StateAction();
    }

    /**
     * ���ڵݹ鴦����Ϊһ��
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
