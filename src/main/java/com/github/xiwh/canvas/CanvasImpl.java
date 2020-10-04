package com.github.xiwh.canvas;

import java.awt.image.BufferedImage;

public class CanvasImpl implements Canvas {
    private int w,h;
    private int[] pixels = null;

    public int[] getPixels() {
        return pixels;
    }

    public int getPixel(int x,int y){
        if(x<0||x>=w||y<0||y>=h){
            return 0;
        }
        return pixels[y*w+x];
    }

    public CanvasImpl(int w, int h){
        updateSize(w, h);
    }

    private void updateSize(int w,int h){
        if(w*h != this.w*this.h){
            pixels = new int[w*h];
        }
        this.w = w;
        this.h = h;
    }

    @Override
    public void drawPoint(int x, int y, Color color) {
        if(x<0||x>=w||y<0||y>=h){
            return;
        }
        pixels[y*w+x] = color.get256Color();
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        int x,y;
        int dx=Math.abs(x2-x1),dy=Math.abs(y2-y1);
        int p=2*dy-dx;
        int twody=2*dy,twodysubdx=2*(dy-dx);

        if(x1>x2)
        {
            x=x2;
            y=y2;
            x2=x1;
            y2=y1;
        }
        else
        {
            x=x1;
            y=y1;
        }
        drawPoint(x,y,color);
        while (x<x2)
        {
            x++;
            if(p<0)
            {
                p+=twody;
            }
            else
            {
                y++;
                p+=twodysubdx;
            }
            drawPoint(x,y,color);
        }
    }

    @Override
    public void drawImage(int x, int y, int w, int h, BufferedImage image) {
        double rate = (double) image.getWidth()/w;
        int i,j,imgX,imgY;
        for(i=0;i<h;i++){
            for(j=0;j<w;j++){
//                imgX = (int) (rate*j);
//                imgY = (int) (rate*i);
                drawPoint(j,i,new Color(image.getRGB(j, i)));
            }
        }
    }


}
