package com.github.xiwh.terminal;

import com.github.xiwh.terminal.buffer.PrintBufferStream;
import com.github.xiwh.utils.StringUtils;
import org.jline.terminal.Size;
import org.jline.terminal.TerminalBuilder;
import java.io.IOException;
import java.io.PrintStream;

public class TerminalImpl implements Terminal {
    private int defaultWidth;
    private int defaultHeight;
    private int width;
    private int height;
    private boolean isBegin = false;
    private org.jline.terminal.Terminal jlineTerminal;
    private PrintStream systemPrintStream;
    private PrintBufferStream systemPrintBufferStream;


    public TerminalImpl(int defaultWidth,int defaultHeight) throws IOException {
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
        jlineTerminal = TerminalBuilder
                .builder()
                .jansi(true)
                .jna(true)
                .build();
        this.width = defaultWidth;
        this.height = defaultHeight;
//        updateSize();
    }

    public TerminalImpl() throws IOException {
        this(80,24);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void updateSize() {
        Size size = jlineTerminal.getSize();
        int col = size.getColumns();
        int row = size.getRows();
        if(col<10){
            width = defaultWidth;
        }else{
            width = col;
        }
        if(row<2){
            height = defaultHeight;
        }else{
            height = row;
        }
    }

    @Override
    public void clearScreen() {
        System.out.println(StringUtils.repeatString("\r\n",getHeight()));
    }

    @Override
    public void begin() {
        synchronized (this){
            synchronized (System.out){
                clearScreen();
                if(systemPrintBufferStream!=null){
                    systemPrintBufferStream.close();
                }
                systemPrintBufferStream = PrintBufferStream.newInstance();
                systemPrintStream = System.out;
                System.setOut(systemPrintBufferStream);
            }
            this.isBegin = true;
        }
    }

    @Override
    public void rollback() {
        synchronized (this) {
            checkBegin();
            synchronized (System.out) {
                System.setOut(systemPrintStream);
                System.out.print(systemPrintBufferStream.print());
                systemPrintStream = null;
                systemPrintBufferStream = null;
            }
        }
    }

    private void checkBegin(){
        if (!isBegin) {
            throw new RuntimeException("Terminal not began");
        }
    }

    @Override
    public void print(String str) {
        checkBegin();
        systemPrintStream.print(str);
    }
}
