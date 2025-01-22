package com.suremoon.game.door.attribute;

public enum BuffEffectOperator {
    ADD(0, Double::sum), // +
    MUL(1, (a, b) -> a * b), // *
    ;

    private final OperatorCalc calcFunc;

    public double getDefaultValue() {
        return defaultValue;
    }

    private final double defaultValue;

    public double doCalc(double raw, double buffer) {
        return this.calcFunc.calc(raw, buffer);
    }

    BuffEffectOperator(double defaultValue, OperatorCalc calcFunc) {
        this.defaultValue = defaultValue;
        this.calcFunc = calcFunc;
    }

    interface OperatorCalc {
        double calc(double raw, double buffer);
    }
}
