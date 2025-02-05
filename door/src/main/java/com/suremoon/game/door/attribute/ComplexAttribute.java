package com.suremoon.game.door.attribute;

import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

import java.util.function.Function;

/**
 * @Note: this file product by scripts/generate_complex_attribute.py, you should change scripts to fix this file.
 * , Or, your another change will be cover. @Author: ProtossGenius @Time: 2025-02-04 21:49:52
 */
public class ComplexAttribute implements SerializeAble {
    AttributeAdapter basic;
    AttributeAdapter max;
    AttributeAdapter cur;
    BuffManager buffManager;

    public ComplexAttribute(AttributeAdapter basic, Function<String, Buff> buffGetter) {
        this.basic = basic;
        this.max = new AttributeAdapter(basic);
        this.cur = new AttributeAdapter(basic);
        buffManager = new BuffManager(basic, max, buffGetter);
    }

    public BuffManager getBuffManager() {
        return buffManager;
    }

    @Override
    public void parseFromBytes(ByteStream byteStream) {
        basic.parseFromBytes(byteStream);
        max.parseFromBytes(byteStream);
        cur.parseFromBytes(byteStream);
    }

    @Override
    public byte[] encodeToBytes() {
        return CJDeal.ByteArrayConnect(basic.encodeToBytes(), max.encodeToBytes(), cur.encodeToBytes());
    }


    /**
     * 设置Basic 血量.
     */
    public void setBasicHp(double value) {
        this.basic.setHp(value);
    }

    /**
     * 获得Basic 血量.
     */
    public double getBasicHp() {
        return this.basic.getHp();
    }

    /**
     * 获得Max 血量.
     */
    public double getMaxHp() {
        return this.max.getHp();
    }

    /**
     * 设置 血量.
     */
    public void setHp(double value) {
        this.cur.setHp(value);
    }

    /**
     * Init 血量.
     */
    public void initHp(double value) {
        this.cur.setHp(value);
        this.basic.setHp(value);
    }

    /**
     * 获得 血量.
     */
    public double getHp() {
        return this.cur.getHp();
    }


    /**
     * 设置Basic 蓝量.
     */
    public void setBasicMp(double value) {
        this.basic.setMp(value);
    }

    /**
     * 获得Basic 蓝量.
     */
    public double getBasicMp() {
        return this.basic.getMp();
    }

    /**
     * 获得Max 蓝量.
     */
    public double getMaxMp() {
        return this.max.getMp();
    }

    /**
     * 设置 蓝量.
     */
    public void setMp(double value) {
        this.cur.setMp(value);
    }

    /**
     * Init 蓝量.
     */
    public void initMp(double value) {
        this.cur.setMp(value);
        this.basic.setMp(value);
    }

    /**
     * 获得 蓝量.
     */
    public double getMp() {
        return this.cur.getMp();
    }


    /**
     * 设置Basic 速度.
     */
    public void setBasicSpd(double value) {
        this.basic.setSpd(value);
    }

    /**
     * 获得Basic 速度.
     */
    public double getBasicSpd() {
        return this.basic.getSpd();
    }

    /**
     * 获得Max 速度.
     */
    public double getMaxSpd() {
        return this.max.getSpd();
    }

    /**
     * 设置 速度.
     */
    public void setSpd(double value) {
        this.cur.setSpd(value);
    }

    /**
     * Init 速度.
     */
    public void initSpd(double value) {
        this.cur.setSpd(value);
        this.basic.setSpd(value);
    }

    /**
     * 获得 速度.
     */
    public double getSpd() {
        return this.cur.getSpd();
    }


    /**
     * 设置Basic 金.
     */
    public void setBasicMetal(double value) {
        this.basic.setMetal(value);
    }

    /**
     * 获得Basic 金.
     */
    public double getBasicMetal() {
        return this.basic.getMetal();
    }

    /**
     * 获得Max 金.
     */
    public double getMaxMetal() {
        return this.max.getMetal();
    }

    /**
     * 设置 金.
     */
    public void setMetal(double value) {
        this.cur.setMetal(value);
    }

    /**
     * Init 金.
     */
    public void initMetal(double value) {
        this.cur.setMetal(value);
        this.basic.setMetal(value);
    }

    /**
     * 获得 金.
     */
    public double getMetal() {
        return this.cur.getMetal();
    }


    /**
     * 设置Basic 木.
     */
    public void setBasicWood(double value) {
        this.basic.setWood(value);
    }

    /**
     * 获得Basic 木.
     */
    public double getBasicWood() {
        return this.basic.getWood();
    }

    /**
     * 获得Max 木.
     */
    public double getMaxWood() {
        return this.max.getWood();
    }

    /**
     * 设置 木.
     */
    public void setWood(double value) {
        this.cur.setWood(value);
    }

    /**
     * Init 木.
     */
    public void initWood(double value) {
        this.cur.setWood(value);
        this.basic.setWood(value);
    }

    /**
     * 获得 木.
     */
    public double getWood() {
        return this.cur.getWood();
    }


    /**
     * 设置Basic 水.
     */
    public void setBasicWater(double value) {
        this.basic.setWater(value);
    }

    /**
     * 获得Basic 水.
     */
    public double getBasicWater() {
        return this.basic.getWater();
    }

    /**
     * 获得Max 水.
     */
    public double getMaxWater() {
        return this.max.getWater();
    }

    /**
     * 设置 水.
     */
    public void setWater(double value) {
        this.cur.setWater(value);
    }

    /**
     * Init 水.
     */
    public void initWater(double value) {
        this.cur.setWater(value);
        this.basic.setWater(value);
    }

    /**
     * 获得 水.
     */
    public double getWater() {
        return this.cur.getWater();
    }


    /**
     * 设置Basic 火.
     */
    public void setBasicFire(double value) {
        this.basic.setFire(value);
    }

    /**
     * 获得Basic 火.
     */
    public double getBasicFire() {
        return this.basic.getFire();
    }

    /**
     * 获得Max 火.
     */
    public double getMaxFire() {
        return this.max.getFire();
    }

    /**
     * 设置 火.
     */
    public void setFire(double value) {
        this.cur.setFire(value);
    }

    /**
     * Init 火.
     */
    public void initFire(double value) {
        this.cur.setFire(value);
        this.basic.setFire(value);
    }

    /**
     * 获得 火.
     */
    public double getFire() {
        return this.cur.getFire();
    }


    /**
     * 设置Basic 土.
     */
    public void setBasicEarth(double value) {
        this.basic.setEarth(value);
    }

    /**
     * 获得Basic 土.
     */
    public double getBasicEarth() {
        return this.basic.getEarth();
    }

    /**
     * 获得Max 土.
     */
    public double getMaxEarth() {
        return this.max.getEarth();
    }

    /**
     * 设置 土.
     */
    public void setEarth(double value) {
        this.cur.setEarth(value);
    }

    /**
     * Init 土.
     */
    public void initEarth(double value) {
        this.cur.setEarth(value);
        this.basic.setEarth(value);
    }

    /**
     * 获得 土.
     */
    public double getEarth() {
        return this.cur.getEarth();
    }


    /**
     * 设置Basic 雷.
     */
    public void setBasicThunder(double value) {
        this.basic.setThunder(value);
    }

    /**
     * 获得Basic 雷.
     */
    public double getBasicThunder() {
        return this.basic.getThunder();
    }

    /**
     * 获得Max 雷.
     */
    public double getMaxThunder() {
        return this.max.getThunder();
    }

    /**
     * 设置 雷.
     */
    public void setThunder(double value) {
        this.cur.setThunder(value);
    }

    /**
     * Init 雷.
     */
    public void initThunder(double value) {
        this.cur.setThunder(value);
        this.basic.setThunder(value);
    }

    /**
     * 获得 雷.
     */
    public double getThunder() {
        return this.cur.getThunder();
    }


    /**
     * 设置Basic 风.
     */
    public void setBasicWind(double value) {
        this.basic.setWind(value);
    }

    /**
     * 获得Basic 风.
     */
    public double getBasicWind() {
        return this.basic.getWind();
    }

    /**
     * 获得Max 风.
     */
    public double getMaxWind() {
        return this.max.getWind();
    }

    /**
     * 设置 风.
     */
    public void setWind(double value) {
        this.cur.setWind(value);
    }

    /**
     * Init 风.
     */
    public void initWind(double value) {
        this.cur.setWind(value);
        this.basic.setWind(value);
    }

    /**
     * 获得 风.
     */
    public double getWind() {
        return this.cur.getWind();
    }


    /**
     * 设置Basic 冰.
     */
    public void setBasicIce(double value) {
        this.basic.setIce(value);
    }

    /**
     * 获得Basic 冰.
     */
    public double getBasicIce() {
        return this.basic.getIce();
    }

    /**
     * 获得Max 冰.
     */
    public double getMaxIce() {
        return this.max.getIce();
    }

    /**
     * 设置 冰.
     */
    public void setIce(double value) {
        this.cur.setIce(value);
    }

    /**
     * Init 冰.
     */
    public void initIce(double value) {
        this.cur.setIce(value);
        this.basic.setIce(value);
    }

    /**
     * 获得 冰.
     */
    public double getIce() {
        return this.cur.getIce();
    }

}
