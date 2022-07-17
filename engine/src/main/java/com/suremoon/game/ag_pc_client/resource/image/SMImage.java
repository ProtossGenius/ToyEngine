package com.suremoon.game.ag_pc_client.resource.image;

import java.awt.*;
import java.awt.image.*;

/**
 * Created by Water Moon on 2017/8/28.
 */
public class SMImage {
    public Image getImg() {
        return img;
    }

    public int getImageWidth() {
        return image_width;
    }

    public int getImageHeight() {
        return image_height;
    }

    protected Image img;
    protected int image_width;
    protected int image_height;
    SMImage(){
        this.img = null;
        this.image_height = 0;
        this.image_width = 0;
    }
    public SMImage(Image i, int width, int height){
        this.img = i;
        this.image_height = height;
        this.image_width  = width;
    }
    public SMImage(BufferedImage bi){
        image_width=bi.getWidth();
        image_height=bi.getHeight();
        img=bi.getScaledInstance(image_width,image_height,Image.SCALE_SMOOTH);
    }
    public void setTransparent(Color transparent){
        img = makeColorTransparent(img, transparent);
    }
    public SMImage(BufferedImage bi, Color transparent){
        image_width=bi.getWidth();
        image_height=bi.getHeight();
        img=bi.getScaledInstance(image_width,image_height,Image.SCALE_SMOOTH);
        setTransparent(transparent);
    }

    public static int color_range = 15;
    public static boolean same(int a, int b){
        return a>b ? a-b<=color_range : b-a<=color_range;
    }
    public static Image makeColorTransparent(Image im, final Color color) {
        if(im == null){
            return im;
        }
        ImageFilter filter = new RGBImageFilter() {
            // the color we are looking for... Alpha bits are set to opaque
            public int markerRGB = color.getRGB() | 0xFF000000;

            @Override
            public final int filterRGB(int x, int y, int rgb) {
//                if ((rgb | 0xFF000000) == markerRGB) {
                if(ColorSame(new Color(rgb), new Color(markerRGB))){
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
    public static boolean ColorSame(Color a, Color b){
//        return a.equals(b);
        return NumberSame(a.getRed(), b.getRed(), color_range) && NumberSame(a.getBlue(), b.getBlue(), color_range) && NumberSame(a.getGreen(), b.getGreen(), color_range);
    }
    public static boolean NumberSame(int a, int b, int c){
        return a > b ? a-b<=c : b-a<=c;
    }
}
