
package com.suremoon.game.door.attribute;
/**
 * @Note: this file product by scripts/generate_attributes.py, you should change scripts to fix this file.
 AttributeAdapter, Or, your another change will be cover. @Author: ProtossGenius @Time: 2025-02-04 21:49:52
  */
public class AttributeAdapter extends SMAttribute {



     /**
      * Hp 血量.
      */
     public static final int Hp= 0;


     /**
      * Mp 蓝量.
      */
     public static final int Mp= 1;


     /**
      * Spd 速度.
      */
     public static final int Spd= 2;


     /**
      * Metal 金.
      */
     public static final int Metal= 3;


     /**
      * Wood 木.
      */
     public static final int Wood= 4;


     /**
      * Water 水.
      */
     public static final int Water= 5;


     /**
      * Fire 火.
      */
     public static final int Fire= 6;


     /**
      * Earth 土.
      */
     public static final int Earth= 7;


     /**
      * Thunder 雷.
      */
     public static final int Thunder= 8;


     /**
      * Wind 风.
      */
     public static final int Wind= 9;


     /**
      * Ice 冰.
      */
     public static final int Ice= 10;


    public AttributeAdapter() {
        super(11);
    }

    public AttributeAdapter(AttributeAdapter another) {
        super(another);
    }


    /**
     * 设置 血量.
     */
    public void setHp(double value) {
        setAttribute(Hp, value);
    }

    /**
     * 获得 血量.
     */
    public double getHp() {
        return getAttribute(Hp);
    }
           

    /**
     * 设置 蓝量.
     */
    public void setMp(double value) {
        setAttribute(Mp, value);
    }

    /**
     * 获得 蓝量.
     */
    public double getMp() {
        return getAttribute(Mp);
    }
           

    /**
     * 设置 速度.
     */
    public void setSpd(double value) {
        setAttribute(Spd, value);
    }

    /**
     * 获得 速度.
     */
    public double getSpd() {
        return getAttribute(Spd);
    }
           

    /**
     * 设置 金.
     */
    public void setMetal(double value) {
        setAttribute(Metal, value);
    }

    /**
     * 获得 金.
     */
    public double getMetal() {
        return getAttribute(Metal);
    }
           

    /**
     * 设置 木.
     */
    public void setWood(double value) {
        setAttribute(Wood, value);
    }

    /**
     * 获得 木.
     */
    public double getWood() {
        return getAttribute(Wood);
    }
           

    /**
     * 设置 水.
     */
    public void setWater(double value) {
        setAttribute(Water, value);
    }

    /**
     * 获得 水.
     */
    public double getWater() {
        return getAttribute(Water);
    }
           

    /**
     * 设置 火.
     */
    public void setFire(double value) {
        setAttribute(Fire, value);
    }

    /**
     * 获得 火.
     */
    public double getFire() {
        return getAttribute(Fire);
    }
           

    /**
     * 设置 土.
     */
    public void setEarth(double value) {
        setAttribute(Earth, value);
    }

    /**
     * 获得 土.
     */
    public double getEarth() {
        return getAttribute(Earth);
    }
           

    /**
     * 设置 雷.
     */
    public void setThunder(double value) {
        setAttribute(Thunder, value);
    }

    /**
     * 获得 雷.
     */
    public double getThunder() {
        return getAttribute(Thunder);
    }
           

    /**
     * 设置 风.
     */
    public void setWind(double value) {
        setAttribute(Wind, value);
    }

    /**
     * 获得 风.
     */
    public double getWind() {
        return getAttribute(Wind);
    }
           

    /**
     * 设置 冰.
     */
    public void setIce(double value) {
        setAttribute(Ice, value);
    }

    /**
     * 获得 冰.
     */
    public double getIce() {
        return getAttribute(Ice);
    }
           
}
