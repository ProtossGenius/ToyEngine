package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.utils.Assert;
// This interface is product by scripts/generate_bag.py, You should change scripts to fix this file.
// @Author: ProtossGenius @Time: 2025-02-04 21:49:52

public class BagManager {
    PlayerItf playerItf;

    public BagManager(PlayerItf playerItf) {
        this.playerItf = playerItf;
    }

    public PlayerItf getPlayer() {
        return playerItf;
    }

    // todo: set private.
    GoodsItf getDirect(int pos) { // get from user's bag direct.
        return getPlayer().getBag().get(pos);
    }

    /**
     * pick goods from world.
     *
     * @param goods
     * @param worldItf
     */
    void pick(GoodsItf goods, WorldItf worldItf) {
        var goodsOnHand = getBagOnHand();
        var bag = getUserBag().getBag();
        // put the goods on Hand to bag.
        goodsOnHand = addToBag(goodsOnHand);
        if (goodsOnHand != null) {
            dropGoods(goods, worldItf);
        }

        getPlayer().getBag().set(BAG_ON_HAND, goods);
    }

    private void dropGoods(GoodsItf goods, WorldItf worldItf) {
        goods.setPos(getPlayer().getPos());
        worldItf.addCalcUnit(goods);
    }

    /**
     * add goods to bag.
     *
     * @param goods
     * @return if can't add to bag, return goods.
     */
    private GoodsItf addToBag(GoodsItf goods) {
        if (goods == null) {
            return null;
        }
        var userBag = getPlayer().getBag();
        // try add to hand first.
        for (int i = 0; i < 10 && i < userBag.size(); ++i) {
            if (userBag.get(i) == null) {
                userBag.set(i, goods);
                return null;
            }
        }
        // then try add to user's bag.
        var bag = getUserBag().getBag();
        for (int i = 0; i < bag.size(); ++i) {
            if (bag.get(i) == null) {
                bag.set(i, goods);
                return null;
            }
        }
        return goods;
    }

    void swap(int oldIndex, int newIndex, WorldItf worldItf) {
        if (oldIndex == -1) {
            return;
        }
        var oldGoods = get(oldIndex);

        if (newIndex == -1) {

        }
    }

    public GoodsItf del(int pos) {
        if (pos < FIRST_BAG_VALUE)
            return getDirect(pos);
        int specialBag = pos / 10000;
        if (FIRST_BAG_VALUE + specialBag >= MAX_BAG_VALUE) {
            return null;
        }

        var bag = getDirect(FIRST_BAG_VALUE + specialBag);
        if (bag == null) {
            return null;
        }
        int index = pos % 10000;
        if (index > bag.getBag().size()) {
            return null;
        }
        return bag.getBag().get(pos % 10000);
    }

    public GoodsItf get(int pos) {
        if (pos < FIRST_BAG_VALUE)
            return getDirect(pos);
        int specialBag = pos / 10000;
        if (FIRST_BAG_VALUE + specialBag >= MAX_BAG_VALUE) {
            return null;
        }

        var bag = getDirect(FIRST_BAG_VALUE + specialBag);
        if (bag == null) {
            return null;
        }
        int index = pos % 10000;
        if (index > bag.getBag().size()) {
            return null;
        }
        return bag.getBag().get(pos % 10000);
    }


    // ################# 0 to 10 is goods in handle(bottom).  ########################
    // ################# const default equip type. ########################
    /**
     * LEFT_HAND LeftHand.
     */
    int LEFT_HAND = 10;

    /**
     * RIGHT_HAND RightHand.
     */
    int RIGHT_HAND = 11;

    /**
     * HEAD Head.
     */
    int HEAD = 12;

    /**
     * BODY Body.
     */
    int BODY = 13;

    /**
     * LEFT_LEG LeftLeg.
     */
    int LEFT_LEG = 14;

    /**
     * RIGHT_LEG RightLeg.
     */
    int RIGHT_LEG = 15;

    /**
     * LEFT_FOOT LeftFoot.
     */
    int LEFT_FOOT = 16;

    /**
     * RIGHT_FOOT RightFoot.
     */
    int RIGHT_FOOT = 17;

    // ################### const goods for special bag. #######################
    // index of First bag type.
    int FIRST_BAG_VALUE = 18;
    /**
     * USER_BAG user's bag.
     */
    public int USER_BAG = 18;

    /**
     * BAG_ON_HAND bag on user's hand.
     */
    public int BAG_ON_HAND = 19;

    public int MAX_BAG_VALUE = 20;

    /**
     * 设置 LeftHand.
     */
    public void loadLeftHand(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == LEFT_HAND, "Need Goods Type is LeftHand");
        swap(LEFT_HAND, newIndex, worldItf);
    }

    /**
     * 获得 LeftHand.
     */
    public GoodsItf getLeftHand() {
        return getDirect(LEFT_HAND);
    }


    /**
     * 设置 RightHand.
     */
    public void loadRightHand(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == RIGHT_HAND, "Need Goods Type is RightHand");
        swap(RIGHT_HAND, newIndex, worldItf);
    }

    /**
     * 获得 RightHand.
     */
    public GoodsItf getRightHand() {
        return getDirect(RIGHT_HAND);
    }


    /**
     * 设置 Head.
     */
    public void loadHead(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == HEAD, "Need Goods Type is Head");
        swap(HEAD, newIndex, worldItf);
    }

    /**
     * 获得 Head.
     */
    public GoodsItf getHead() {
        return getDirect(HEAD);
    }


    /**
     * 设置 Body.
     */
    public void loadBody(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == BODY, "Need Goods Type is Body");
        swap(BODY, newIndex, worldItf);
    }

    /**
     * 获得 Body.
     */
    public GoodsItf getBody() {
        return getDirect(BODY);
    }


    /**
     * 设置 LeftLeg.
     */
    public void loadLeftLeg(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == LEFT_LEG, "Need Goods Type is LeftLeg");
        swap(LEFT_LEG, newIndex, worldItf);
    }

    /**
     * 获得 LeftLeg.
     */
    public GoodsItf getLeftLeg() {
        return getDirect(LEFT_LEG);
    }


    /**
     * 设置 RightLeg.
     */
    public void loadRightLeg(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == RIGHT_LEG, "Need Goods Type is RightLeg");
        swap(RIGHT_LEG, newIndex, worldItf);
    }

    /**
     * 获得 RightLeg.
     */
    public GoodsItf getRightLeg() {
        return getDirect(RIGHT_LEG);
    }


    /**
     * 设置 LeftFoot.
     */
    public void loadLeftFoot(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == LEFT_FOOT, "Need Goods Type is LeftFoot");
        swap(LEFT_FOOT, newIndex, worldItf);
    }

    /**
     * 获得 LeftFoot.
     */
    public GoodsItf getLeftFoot() {
        return getDirect(LEFT_FOOT);
    }


    /**
     * 设置 RightFoot.
     */
    public void loadRightFoot(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == RIGHT_FOOT, "Need Goods Type is RightFoot");
        swap(RIGHT_FOOT, newIndex, worldItf);
    }

    /**
     * 获得 RightFoot.
     */
    public GoodsItf getRightFoot() {
        return getDirect(RIGHT_FOOT);
    }


    /**
     * 获得 user's bag.
     */
    public GoodsItf getUserBag() {
        return getDirect(USER_BAG);
    }


    /**
     * 获得 bag on user's hand.
     */
    public GoodsItf getBagOnHand() {
        return getDirect(BAG_ON_HAND);
    }

}
