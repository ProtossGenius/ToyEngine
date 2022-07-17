package com.suremoon.game.ag_pc_client.controller;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Water Moon on 2017/12/4.
 */
public class SMKeyBoard implements KeyListener{
    boolean [] keyList = new boolean[500];//true is pressed
    public int lastKey;
    public SMKeyBoard(){
        for(int i = 0; i < keyList.length; ++i){
            keyList[i] = false;
        }
        lastKey = 0;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode >= keyList.length){
            System.err.println("Err: keycode:" + keyCode);
            return;
        }
        if(keyCode != KeyEvent.VK_SHIFT && keyCode != KeyEvent.VK_CONTROL && keyCode != KeyEvent.VK_ALT)
            lastKey = keyCode;
        keyList[keyCode] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode >= keyList.length){
            System.err.println("Err: keycode:" + keyCode);
            return;
        }
        keyList[lastKey = keyCode] = false;
    }
    public boolean isKeyPressed(int keyCode){
        if(keyCode >= keyList.length){
            System.err.println("Err: keycode:" + keyCode);
            return false;
        }
        return keyList[keyCode];
    }

    //===================================getters and setters===================================

    public int getLastKey() {
        return lastKey;
    }
}
