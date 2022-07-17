package com.suremoon.game.door.attribute;

/** 元素属性 */
public class ElementPriorities {
  // 金
  public double metal;
  // 木
  public double wood;
  // 水
  public double water;
  // 火
  public double fire;
  // 土
  public double earth;
  // 雷
  public double thunder;
  // 风
  public double wind;
  // 冰
  public double ice;

  public ElementPriorities(
      double metal,
      double wood,
      double water,
      double fire,
      double earth,
      double thunder,
      double wind,
      double ice) {
    this.metal = metal;
    this.wood = wood;
    this.water = water;
    this.fire = fire;
    this.earth = earth;
    this.thunder = thunder;
    this.wind = wind;
    this.ice = ice;
  }

  public ElementPriorities(double ad, double ap) {
    this.metal = ad;
    this.fire = ap;
  }

  ElementPriorities setFiveElement(
      double metal, double wood, double water, double fire, double earth) {
    this.metal = metal;
    this.wood = wood;
    this.water = water;
    this.fire = fire;
    this.earth = earth;
    return this;
  }

  ElementPriorities setThreeRare(double thunder, double wind, double ice) {
    this.thunder = thunder;
    this.wind = wind;
    this.ice = ice;
    return this;
  }

  public ElementPriorities() {}
}
