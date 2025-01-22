package com.suremoon.game.door.attribute;

public class BuffEffect {
    private BuffEffectOperator operator;
    private Integer attribKey;
    private double value;
    private String key;

    public String getKey() {
        return key;
    }

    public BuffEffect(BuffEffectOperator operator, Integer attribKey, double value) {
        this.operator = operator;
        this.attribKey = attribKey;
        this.value = value;
        this.key = String.format("%s_%d", operator, attribKey);
    }

    void add(BuffEffect be) {
        if (be.operator != operator) {
            // todo: del it.
            throw new RuntimeException("need same oper");
        }
        this.value += be.value;
    }

    void remove(BuffEffect be) {
        if (be.operator != operator) {
            // todo: del it;
            throw new RuntimeException("need same oper");
        }
        this.value += be.value;
    }

    void parseBuff(SMAttribute attrib) {
        double rawValue = attrib.getAttribute(attribKey);
        double afterValue = operator.doCalc(rawValue, value);
        attrib.setAttribute(attribKey, afterValue);
    }

    BuffEffectOperator getOperator() {
        return operator;
    }

    BuffEffect createTotalBuffEffect() {
        return new BuffEffect(operator, attribKey, operator.getDefaultValue());
    }
}
