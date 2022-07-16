package com.suremoon.game.door.attribute;

import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

import java.util.Arrays;

/**
 * Created by Water Moon on 2017/12/7.
 */
public abstract class SMAttribute implements SerializeAble {
    protected double attributes[];
    public SMAttribute(int attributeNum){
        attributes = new double[attributeNum];
    }
    public SMAttribute(SMAttribute another){
        this.attributes = Arrays.copyOf(another.attributes, another.attributes.length);
    }
    public int attributeNum(){
        return attributes.length;
    }
    public double getAttribute(int AttributeType){
        return attributes[AttributeType];
    }
    public void setAttribute(int AttributeType, double value){
        attributes[AttributeType] = value;
    }

    public void parseFromBytes(ByteStream byteStream){
        for(int i = 0; i < attributes.length; ++i){
            attributes[i] = byteStream.getDouble();
        }
    }

    public byte[] encodeToBytes(){
        byte[][] tmpList = new byte[attributes.length][];
        for(int i = 0; i < attributes.length; ++i){
            tmpList[i] = CJDeal.double2bytes(attributes[i]);
        }
        return CJDeal.ByteArrayConnect(tmpList);
    }
}
