package com.github.xiwh.terminal.buffer;

import java.io.*;
import java.util.Arrays;

public class PrintBufferStream extends PrintStream{
    OutputStreamImpl outputStream;
    private PrintBufferStream(OutputStreamImpl outputStream) {
        super(outputStream);
        this.outputStream = outputStream;
    }

    public synchronized String print(){
        String content = this.outputStream.getContent();
        close();
        return content;
    }

    public static PrintBufferStream newInstance(){
        OutputStreamImpl outputStream = new OutputStreamImpl();
        return new PrintBufferStream(outputStream);
    }

    private static class OutputStreamImpl extends ByteArrayOutputStream {
        String getContent(){
            return new String(buf,0,count);
        }
    }
}
