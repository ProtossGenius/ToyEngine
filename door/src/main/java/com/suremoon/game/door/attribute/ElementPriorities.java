package com.suremoon.game.door.attribute;

/**
 * ÔªËØÊôÐÔ
 */
public class ElementPriorities {
    // ½ð
    public double metal;
    // Ä¾
    public double wood;
    // Ë®
    public double water;
    //»ð
    public double fire;
    //ÍÁ
    public double earth;
    // À×
    public double thunder;
    // ·ç
    public double wind;
    // ±ù
    public double ice;

    public ElementPriorities(double metal, double wood, double water, double fire, double earth, double thunder, double wind, double ice) {
        this.metal = metal;
        this.wood = wood;
        this.water = water;
        this.fire = fire;
        this.earth = earth;
        this.thunder = thunder;
        this.wind = wind;
        this.ice = ice;
    }

    public ElementPriorities(double ad, double ap){
        this.metal = ad;
        this.fire = ap;
    }

    ElementPriorities setFiveElement(double metal, double wood, double water, double fire, double earth){
        this.metal = metal;
        this.wood = wood;
        this.water = water;
        this.fire = fire;
        this.earth = earth;
        return this;
    }

    ElementPriorities setThreeRare(double thunder, double wind, double ice){
        this.thunder = thunder;
        this.wind = wind;
        this.ice = ice;
        return this;
    }

    public ElementPriorities() {
    }
}
