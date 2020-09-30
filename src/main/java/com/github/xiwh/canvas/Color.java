package com.github.xiwh.canvas;

import org.jline.utils.Colors;

public class Color {
    //Reduce the color sampling rate by 8 times
    //0xffffff%8 = 0x1fffff
    private static final byte[] colors256 = new byte[0x1fffff];

    private int rgb = 0;
    private int r,g,b;
    public Color(int r, int g,int b){
        rgb = (r<<16) + (g<<8) + b;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(int rgb){
        this.rgb = rgb;
        this.r = (rgb & 0x00ff0000) >> 16;
        this.g = (rgb & 0x0000ff00) >> 8;
        this.b = rgb & 0xff;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public int getRed() {
        return r;
    }

    public int getGreen() {
        return g;
    }

    public int getBlue() {
        return b;
    }

    public static int to256Color(int rgb){
        int r = rgb >> 16;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        int index = (int) (r*0.5f*16384 + g*0.5f*128 + b*0.5f) & 0x1fffff;
        System.out.println("index:"+index);
        byte val = colors256[index];
        if(val==0){
            int v = Colors.roundRgbColor(r,g,b, 256);
            if(v==0){
                v = 16;
            }
            System.out.println("nev:"+v);
            colors256[index] = (byte) (v);
            return v;
        }else {
            int v = Byte.toUnsignedInt(val);
            System.out.println("v:"+v);
            return v;
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Color{");
        sb.append("rgb=").append(rgb);
        sb.append(", r=").append(r);
        sb.append(", g=").append(g);
        sb.append(", b=").append(b);
        sb.append('}');
        return sb.toString();
    }
}
