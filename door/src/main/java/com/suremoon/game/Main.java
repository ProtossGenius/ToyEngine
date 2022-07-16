package com.suremoon.game;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * ÓÃÓÚÉú³ÉJar°üµÄ
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        for(int i = 0; i < 10; ++i){
            list.add(i);
        }

        for(Iterator<Integer> it = list.iterator(); it.hasNext(); ){
            System.out.println(it.next());
            it.remove();
        }

        System.out.println(list.size());

        Rectangle rect = new Rectangle(0, 0, 100, 100);
        System.out.println(rect.contains(1, 1));
        System.out.println(rect.contains(0, 0));
        System.out.println(rect.contains(0, 100));
        System.out.println(rect.contains(100, 100));
    }
}
