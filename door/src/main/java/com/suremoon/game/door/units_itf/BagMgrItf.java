package com.suremoon.game.door.units_itf;
import com.suremoon.game.door.utils.Assert;
import com.suremoon.game.door.kernel.WorldItf;
// This interface is product by scripts/generate_bag.py, You should change scripts to fix this file.
// @Author: ProtossGenius @Time: 2025-02-04 16:30:59

public interface BagMgrItf {
    GoodsItf get(int pos);

    void pick(GoodsItf goods, WorldItf worldItf);

    void swap(int oldIndex, int newIndex, WorldItf worldItf);

    // ################# default goods type. ########################


     /**
      * LEFT_HAND LeftHand.
      */
     int LEFT_HAND= 0;


     /**
      * RIGHT_HAND RightHand.
      */
     int RIGHT_HAND= 1;


     /**
      * HEAD Head.
      */
     int HEAD= 2;


     /**
      * BODY Body.
      */
     int BODY= 3;


     /**
      * LEFT_LEG LeftLeg.
      */
     int LEFT_LEG= 4;


     /**
      * RIGHT_LEG RightLeg.
      */
     int RIGHT_LEG= 5;


     /**
      * LEFT_FOOT LeftFoot.
      */
     int LEFT_FOOT= 6;


     /**
      * RIGHT_FOOT RightFoot.
      */
     int RIGHT_FOOT= 7;


    /**
     * 设置 LeftHand.
     */
    default void loadLeftHand(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == LEFT_HAND, "Need Goods Type is LeftHand");
        swap(LEFT_HAND, newIndex, worldItf);
    }

    /**
     * 获得 LeftHand.
     */
    default GoodsItf getLeftHand() {
        return get(LEFT_HAND);
    }


    /**
     * 设置 RightHand.
     */
    default void loadRightHand(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == RIGHT_HAND, "Need Goods Type is RightHand");
        swap(RIGHT_HAND, newIndex, worldItf);
    }

    /**
     * 获得 RightHand.
     */
    default GoodsItf getRightHand() {
        return get(RIGHT_HAND);
    }


    /**
     * 设置 Head.
     */
    default void loadHead(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == HEAD, "Need Goods Type is Head");
        swap(HEAD, newIndex, worldItf);
    }

    /**
     * 获得 Head.
     */
    default GoodsItf getHead() {
        return get(HEAD);
    }


    /**
     * 设置 Body.
     */
    default void loadBody(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == BODY, "Need Goods Type is Body");
        swap(BODY, newIndex, worldItf);
    }

    /**
     * 获得 Body.
     */
    default GoodsItf getBody() {
        return get(BODY);
    }


    /**
     * 设置 LeftLeg.
     */
    default void loadLeftLeg(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == LEFT_LEG, "Need Goods Type is LeftLeg");
        swap(LEFT_LEG, newIndex, worldItf);
    }

    /**
     * 获得 LeftLeg.
     */
    default GoodsItf getLeftLeg() {
        return get(LEFT_LEG);
    }


    /**
     * 设置 RightLeg.
     */
    default void loadRightLeg(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == RIGHT_LEG, "Need Goods Type is RightLeg");
        swap(RIGHT_LEG, newIndex, worldItf);
    }

    /**
     * 获得 RightLeg.
     */
    default GoodsItf getRightLeg() {
        return get(RIGHT_LEG);
    }


    /**
     * 设置 LeftFoot.
     */
    default void loadLeftFoot(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == LEFT_FOOT, "Need Goods Type is LeftFoot");
        swap(LEFT_FOOT, newIndex, worldItf);
    }

    /**
     * 获得 LeftFoot.
     */
    default GoodsItf getLeftFoot() {
        return get(LEFT_FOOT);
    }


    /**
     * 设置 RightFoot.
     */
    default void loadRightFoot(int newIndex, WorldItf worldItf) {
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == RIGHT_FOOT, "Need Goods Type is RightFoot");
        swap(RIGHT_FOOT, newIndex, worldItf);
    }

    /**
     * 获得 RightFoot.
     */
    default GoodsItf getRightFoot() {
        return get(RIGHT_FOOT);
    }

}
