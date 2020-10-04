package com.github.xiwh;

import com.github.xiwh.canvas.Canvas;
import com.github.xiwh.canvas.CanvasImpl;
import com.github.xiwh.canvas.Color;
import com.github.xiwh.terminal.Terminal;
import com.github.xiwh.terminal.TerminalImpl;
import com.github.xiwh.utils.RuntimeUtils;
import org.jline.terminal.TerminalBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.print("\033[48;5;31;38;5;93m▄█▓\033[0m\r\n\033[48;5;31;38;5;93m▄█▓\033[0m");
        BufferedImage image = ImageIO.read(new File("imgs/a.jpg"));
        Terminal terminal = new TerminalImpl(145,96);
        terminal.begin();
        CanvasImpl canvas = new CanvasImpl(terminal.getWidth(), terminal.getHeight());
        canvas.drawImage(0, 0, terminal.getWidth(), terminal.getHeight(), image);
        for (int y = 0; y < terminal.getHeight(); y+=2) {
            for (int x = 0; x < terminal.getWidth(); x++) {
                int v = canvas.getPixel(x, y);
                int v2 = canvas.getPixel(x, y+1);
                terminal.print("\033[48;5;"+v+";38;5;" + v2 + "m▄\033[0m");
            }
            terminal.print("\r\n");
        }
//        System.out.println("\033[38;5;123m#That is\033[0m");
//        System.out.println("\033[38;5;206;48;5;76m#That is\033[0m");
//        System.out.println("\033[38;2;255;82;197;48;2;155;106;0mHello\033[0m");

//        Terminal terminal = new TerminalImpl();
//        terminal.begin();
//        terminal.rollback();
    }

    public static void main2(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String s = sc.nextLine();
            System.out.println("输入值："+s);
            int rgb = Integer.valueOf(s,16);
            Color color = new Color(rgb);
            int c = Color.to256Color(rgb);
            System.out.println("#" + Integer.toHexString(rgb));
            System.out.println("\033[38;5;" + c + "m" + c + "\033[0m");
            System.out.println("\033[38;2;" + color.getRed() + ";" + color.getGreen() + ";" + color.getBlue() + "mHello\033[0m");
        }

//        System.out.println("\033[47;31mhello world\033[0m");
//        System.out.println("\033[38;5;123m#That is\033[0m");
//        System.out.println("\033[38;5;206;48;5;76m#That is\033[0m");
//        System.out.println("\033[38;2;255;82;197;48;2;155;106;0mHello\033[0m");

//        Terminal terminal = new TerminalImpl();
//        terminal.begin();
//        terminal.rollback();
    }
}
