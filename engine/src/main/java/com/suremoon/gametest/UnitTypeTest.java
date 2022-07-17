package com.suremoon.gametest;

import java.util.Scanner;

/**
 * Created by Water Moon on 2017/11/29.
 */
public class UnitTypeTest {
    public static void main(String[] args) throws Exception {
//        InitManager.init();
//        for (int i = 0; i < IDManager.size(); i++) {
//            System.out.println(i + ":\t\t" + IDManager.getName(i));
//        }
//        MKInpDeal mkids = new MKInpDeal(new GameRun());
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String num = scanner.next();
            System.out.println("case " + num + ":");
            int idx = scanner.nextInt(), idy = scanner.nextInt();

            System.out.println("idx = " + idx + ";idy =" + idy + ";");
            System.out.println("break;");
        }
    }
}
