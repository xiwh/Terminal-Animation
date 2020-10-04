package com.github.xiwh.terminal;

public interface Terminal {
    int getWidth();
    int getHeight();
    void updateSize();
    void clearScreen();
    void begin();
    void rollback();
    void print(String str);
}
