package com.suremoon.game.ag_pc_client.show;

import com.suremoon.game.ag_pc_client.ui.format.StringShow;
import com.suremoon.game.door.attribute.ComplexAttribute;
import com.suremoon.game.kernel.data.units.Unit;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Water Moon on 2017/12/12. 单位需要显示的额外信息,如血条/
 */
public class UnitMoreShow {
    public static BufferedImage hpRed, hpGreen, hpOrange, mpBlue, mpCyan, mpGray;

    static {
        hpRed = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        hpGreen = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        hpOrange = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        mpBlue = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        mpCyan = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        mpGray = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        setImgFill(hpRed, Color.RED);
        setImgFill(hpGreen, Color.GREEN);
        setImgFill(hpOrange, Color.ORANGE);
        setImgFill(mpBlue, Color.BLUE);
        setImgFill(mpCyan, Color.CYAN);
        setImgFill(mpGray, Color.GRAY);
    }

    static void setImgFill(Image img, Color color) {
        Graphics gp = img.getGraphics();
        gp.setColor(color);
        gp.fillRect(0, 0, 10, 10);
    }

    private static void resetTrans(BufferedImage image, int trans) {
        image.setRGB(0, 0, transRGB(image.getRGB(0, 0), trans).getRGB());
    }

    public static void show(Graphics gp, Unit unit, Point focusPoint) {
        double hc = (double) unit.getHeight() / 100;
        BufferedImage hpImg, mpImg;
        ComplexAttribute ca = unit.getAttribute();
        double hp = 1;
        if (0 != ca.getMaxHp()) {
            hp = ca.getHp() / ca.getMaxHp();
        }
        if (hp > 1) {
            hp = 1;
        }
        if (hp < 0) {
            hp = 0;
        }

        if (hp <= 0.3) hpImg = hpRed;
        else if (hp <= 0.6) hpImg = hpOrange;
        else hpImg = hpGreen;
        resetTrans(hpImg, unit.getTransparency());
        double mp = 1;
        if (0 != ca.getMaxMp()) {
            mp = ca.getMp() / ca.getMaxMp();
        }
        if (mp > 1) mp = 1;
        if (mp < 0) mp = 0;
        if (mp <= 0.3) {
            mpImg = mpGray;
        } else if (mp <= 0.6) mpImg = mpCyan;
        else mpImg = mpBlue;
        resetTrans(mpImg, unit.getTransparency());
        gp.setColor(transColor(Color.BLACK, unit.getTransparency()));
        gp.drawRect(
                (int) unit.getPos().getX() - focusPoint.x,
                (int) (unit.getPos().getY() + hc * 5) - focusPoint.y,
                unit.getWidth(),
                (int) (hc * 5));
        gp.drawImage(
                hpImg,
                (int) unit.getPos().getX() - focusPoint.x,
                (int) (unit.getPos().getY() + hc * 5) - focusPoint.y,
                (int) (unit.getWidth() * hp),
                (int) (hc * 5),
                null);
        gp.drawRect(
                (int) unit.getPos().getX() - focusPoint.x,
                (int) (unit.getPos().getY() + hc * 11) - focusPoint.y,
                unit.getWidth(),
                (int) (hc * 5));
        gp.drawImage(
                mpImg,
                (int) unit.getPos().getX() - focusPoint.x,
                (int) (unit.getPos().getY() + hc * 11) - focusPoint.y,
                (int) (unit.getWidth() * mp),
                (int) (hc * 5),
                null);
        StringShow ss =
                new StringShow(
                        unit.getShowName(),
                        new Rectangle(
                                (int) unit.getPos().getX() - focusPoint.x,
                                (int) (unit.getPos().getY()) - focusPoint.y,
                                unit.getWidth(),
                                50));
        ss.setColor(transColor(Color.YELLOW, unit.getTransparency()));
        ss.drawOn(gp);
        gp.setColor(Color.BLACK);
    }

    private static Color transColor(Color c, int trans) {
        return transRGB(c.getRGB(), trans);
    }

    private static Color transRGB(int rgb, int trans) {
        return new Color(rgb & 0x00ffffff | (trans << 24), true);
    }
}
