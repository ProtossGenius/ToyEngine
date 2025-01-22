package com.suremoon.game.door.attribute;

import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by Water Moon on 2017/12/7.
 */
public abstract class SMAttribute implements SerializeAble {
    protected double[] attributes;
    private Consumer<SMAttribute> onChange = null;

    public SMAttribute(int attributeNum) {
        attributes = new double[attributeNum];
    }

    public SMAttribute(SMAttribute another) {
        this.attributes = Arrays.copyOf(another.attributes, another.attributes.length);
    }

    public int attributeNum() {
        return attributes.length;
    }

    public double getAttribute(int AttributeType) {
        return attributes[AttributeType];
    }

    public void setAttribute(int AttributeType, double value) {
        attributes[AttributeType] = value;
        if (this.onChange != null) {
            this.onChange.accept(this);
        }
    }

    public void parseFromBytes(ByteStream byteStream) {
        for (int i = 0; i < attributes.length; ++i) {
            attributes[i] = byteStream.getDouble();
        }
    }

    public void setOnChange(Consumer<SMAttribute> onChange) {
        this.onChange = onChange;
    }

    public Consumer<SMAttribute> getOnChange() {
        return onChange;
    }

    public byte[] encodeToBytes() {
        byte[][] tmpList = new byte[attributes.length][];
        for (int i = 0; i < attributes.length; ++i) {
            tmpList[i] = CJDeal.double2bytes(attributes[i]);
        }
        return CJDeal.ByteArrayConnect(tmpList);
    }

    public void copy(SMAttribute raw) {
        System.arraycopy(raw.attributes, 0, attributes, 0, raw.attributes.length);
    }
}
