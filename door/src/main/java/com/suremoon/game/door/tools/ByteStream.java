package com.suremoon.game.door.tools;

import java.io.UnsupportedEncodingException;

/**
 * Created by Water Moon on 2017/12/28.
 */
public class ByteStream {
    byte[] bts;
    int ptr;
    public ByteStream(byte[] inp){
        bts = inp;
        ptr = 0;
    }
    public ByteStream(){
        bts = new byte[0];
        ptr = 0;
    }
    public void setBytes(byte[] bytes){
        ptr = 0;
        bts = bytes;
    }
    public int getInteger(){
        byte[] bt = getBytes(4);
        return CJDeal.byte2int(bt);
    }
    public long getLong(){
        byte[] bt = getBytes(8);
        return CJDeal.bytes2long(bt);
    }
    public double getDouble(){
        byte[] bt = getBytes(8);
        return CJDeal.bytes2double(bt);
    }
    public byte[] getBytes(int size){
        if(ptr + size > bts.length) {
            throw new NullPointerException("ptr = " + ptr + "size = " + size + " bts.length = " + bts.length);
            //should throw error
        }
        byte[] res = new byte[size];

        for(int i = 0; i < size; ++i){
            res[i] = bts[ptr++];
        }
        return res;
    }
    public String getString(){
        int strLen = getInteger();
        byte[] res= getBytes(strLen);
        return CJDeal.byte2string(res);
    }

    public int size() {
        return Math.max(bts.length - ptr, 0);
    }
}
