package com.suremoon.game.door.units_itf.skill_about;

public class SkillInfo {
    public SkillInfo(int cd, int cost, SkillDo skillDo) {
        this.cd = cd;
        this.cost = cost;
        this.skillDo = skillDo;
    }

    public int cost;
    public int cd; // ms
    public long lastTime = 0;
    public SkillDo skillDo;
}
