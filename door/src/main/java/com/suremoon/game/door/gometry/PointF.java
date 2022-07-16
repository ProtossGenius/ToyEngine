package com.suremoon.game.door.gometry;

import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

import java.awt.*;

/**
 * Created by Water Moon on 2017/9/6.
 */
public class PointF implements SerializeAble {
    public double X, Y;
    public double getX() {
        return X;
    }
    public int getIntX(){return (int) X;}
    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }
    public int getIntY(){return (int) Y;}
    public void setY(double y) {
        Y = y;
    }
    public PointF(int x, int y){
        this.X = x;
        this.Y = y;
    }
    public PointF(Point p){
        this.X = p.getX();
        this.Y = p.getY();
    }
    public PointF(double x, double y){
        this.X = x;
        this.Y = y;
    }
    public PointF(PointF pf){
        this.X = pf.X;
        this.Y = pf.Y;
    }
    public PointF(){
        X = Y = 0;
    }
    public double getDistance(PointF pf){
        return getDistance(pf, this);
    }
    public static double getDistance(PointF pf1, PointF pf2){
        double dx = pf1.getX() - pf2.getX(), dy = pf1.getY() - pf2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    public PointF Add(PointF pf){
        return new PointF(this.getX() + pf.getX(), this.getY() + pf.getY());
    }
    public void AddAs(PointF pf){
        X += pf.X;
        Y += pf.Y;
    }
    public PointF Subtract(PointF pf){
        return new PointF(X - pf.X, Y - pf.Y);
    }
    public void SubtractAs(PointF pf){
        X -= pf.X;
        Y -= pf.Y;
    }
    public PointF SinMultipy(double num){
        double length = Math.sqrt(X * X + Y * Y);
        return new PointF(X * num / length, Y * num / length);
    }
    public void SinMultipyAs(double num){
        double length = Math.sqrt(X * X + Y * Y);
        X *= num;
        X /= length;
        Y *= num;
        Y /= length;
    }
    public PointF Multipy(double num){
        return new PointF(X * num, Y * num);
    }
    public void MultipyAs(double num){
        X *= num;
        Y *= num;
    }

    public PointF Division(double num){
        if(num == 0)throw new ArithmeticException("/ by zero");
        return new PointF(X / num, Y / num);
    }
    public void DivisionAs(double num){
        if(num == 0)throw new ArithmeticException("/ by zero");
        X /= num;
        Y /= num;
    }
    public void setPoint(double x, double y){
        X = x;
        Y = y;
    }

    public boolean notLegitimacy(){
        return Double.isNaN(X + Y);
    }

    /**
     *
     * @param pf Ä¿±êµã
     * @return a Point whose X * X + Y * Y == 1, if dis is zero then return DIRECTION_ZERO
     */
    public PointF getDirection(PointF pf){
        return getDirection(this, pf);
    }
    public static PointF getDirection(PointF pf1, PointF pf2){
        double dis = getDistance(pf1, pf2);
        if(dis == 0)return new PointF(0, 0);
        PointF res = pf2.Subtract(pf1);
        return res.Division(dis);
    }
    public Point toPoint(){
        return new Point((int)X, (int)Y);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PointF){
            return ((PointF) obj).getX() == X && ((PointF) obj).getY() == Y;
        }
        if(obj instanceof Point){
            return ((double)((Point) obj).x) == X && ((double)((Point) obj).y) == Y;
        }
        return false;
    }
    @Override
    public String toString(){
        return "PointF(" + getX() + ", " + getY() + ")";
    }
    public final static PointF DIRECTION_ZERO = new PointF(0, 0);

    @Override
    public void parseFromBytes(ByteStream byteStream) {
        this.X = byteStream.getDouble();
        this.Y = byteStream.getDouble();
    }

    @Override
    public byte[] encodeToBytes() {
        return CJDeal.ByteArrayConnect(CJDeal.double2bytes(this.X), CJDeal.double2bytes(this.Y));
    }
}
