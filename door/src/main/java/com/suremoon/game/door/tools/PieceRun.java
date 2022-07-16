package com.suremoon.game.door.tools;

public class PieceRun {
    public interface Run{
        void run();
    }
    int pieceTime = 15;
    public PieceRun(int pieceTime){
        this.pieceTime = pieceTime;
    }

    boolean run(Run run) {
        return DoPeaceRun(this.pieceTime, run);
    }

    /**
     * @param PIECE_TIME
     * how many mill-sec to run it.
     * @param run
     * action to do
     * @return
     * true: run-finish in PIECE_TIME ,false not
     */
    public static boolean DoPeaceRun(int PIECE_TIME, Run run) {
        long start = System.currentTimeMillis();
        run.run();
        long piece = PIECE_TIME + start - System.currentTimeMillis();
        if(piece < 0){
            return false;
        }
        try {
            Thread.sleep(piece);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return  true;
    }
}
