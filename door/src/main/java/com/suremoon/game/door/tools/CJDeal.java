package com.suremoon.game.door.tools;

import java.io.UnsupportedEncodingException;

public class CJDeal {
    public CJDeal() {
    }


    public static String getJString(byte[] inp) throws UnsupportedEncodingException {
        int length = CStringLen(inp);
        String tmp = new String(inp, "GB2312");
        if (tmp.length() > 1) {
            tmp = tmp.substring(0, length);
        }

        return tmp;
    }

    public static int CStringLen(byte[] inp) {
        int res;
        for(res = 0; res < inp.length && inp[res] != 0; ++res) {
        }

        return res;
    }

    public static byte[] string2bytes(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("GB2312");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();;
            bytes = str.getBytes();
        }

        return CJDeal.ByteArrayConnect(int2byte(bytes.length), bytes);
    }

    public static String byte2string(byte[] bytes) {
        try {
            return new String(bytes, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return  new String(bytes);
    }

    public static byte[] int2byte(int res) {
        byte[] targets = new byte[]{(byte)(res & 255), (byte)(res >> 8 & 255), (byte)(res >> 16 & 255), (byte)(res >>> 24)};
        return targets;
    }

    public static byte[] ByteArrayConnect(byte[]... arrays) {
        int length = 0;

        for(int i = 0; i < arrays.length; ++i) {
            length += arrays[i].length;
        }

        byte[] res = new byte[length];
        int ptr = 0;

        for(int i = 0; i < arrays.length; ++i) {
            for(int j = 0; j < arrays[i].length; ++j) {
                res[ptr++] = arrays[i][j];
            }
        }

        return res;
    }

    public static int byte2int(byte[] arr) {
        int targets = arr[0] & 255 | arr[1] << 8 & '\uff00' | arr[2] << 24 >>> 8 | arr[3] << 24;
        return targets;
    }

    public static byte[] double2bytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        return long2bytes(value);
    }

    public static double bytes2double(byte[] arr) {
        return Double.longBitsToDouble(bytes2long(arr));
    }

    public static byte[] long2bytes(Long value) {
        byte[] byteRet = new byte[8];

        for(int i = 0; i < 8; ++i) {
            byteRet[i] = (byte)((int)(value >> 8 * i & 255L));
        }

        return byteRet;
    }

    public static Long bytes2long(byte[] arr) {
        long value = 0L;

        for(int i = 0; i < 8; ++i) {
            value |= (long)(arr[i] & 255) << 8 * i;
        }

        return value;
    }
}
