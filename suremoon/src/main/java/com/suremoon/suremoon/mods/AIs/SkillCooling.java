package com.suremoon.suremoon.mods.AIs;

import com.suremoon.game.door.attribute.AttributeAdapter;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;

import java.util.HashMap;
import java.util.Map;

public class SkillCooling implements UnitRemItf {
    private Map<String, SkillInfo> skillInfoMap = new HashMap<>();

    @Override
    public void underAttack(UnitItf owner, UnitItf unitItf, double v) {
    }

    @Override
    public void doCalc(UnitItf unitItf, WorldItf worldItf, WorldMgrItf worldMgrItf) {
    }

    @Override
    public void interactive(UnitItf self, PlayerItf playerItf, WorldItf world, WorldMgrItf worldMgr, Object input) {

    }

    public boolean useSkill(String skillName, AttributeAdapter attr) {
        SkillInfo info = skillInfoMap.get(skillName);
        if (info == null) return false;
        if (System.currentTimeMillis() - info.lastTime < info.cd) {
            return false;
        }
        if (attr.getMp() < info.cost) {
            return false;
        }
        attr.setMp(attr.getMp() - info.cost);
        info.lastTime = System.currentTimeMillis();
        skillInfoMap.put(skillName, info);
        return true;
    }

    public SkillCooling addSkillInfo(String skillName, int cd, int cost) {
        skillInfoMap.put(skillName, new SkillInfo(cd, cost));
        return this;
    }

    public SkillInfo getSkillInfo(String skillName) {
        return skillInfoMap.get(skillName);
    }

    public static class SkillInfo {
        public SkillInfo(int cd, int cost) {
            this.cd = cd;
            this.cost = cost;
        }

        public int cost;
        public int cd; // ms
        public long lastTime = 0;
    }

    public Map<String, SkillInfo> getSkillInfoMap() {
        return skillInfoMap;
    }
}
