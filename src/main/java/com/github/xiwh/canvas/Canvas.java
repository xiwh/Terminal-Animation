package com.github.xiwh.canvas;

import java.awt.image.BufferedImage;

public interface Canvas {
    void drawPoint(int x,int y,Color color);

    void drawLine(int x,int y,int x2,int y2,Color color);

    void drawImage(int x, int y,int w,int h, BufferedImage image);
}
