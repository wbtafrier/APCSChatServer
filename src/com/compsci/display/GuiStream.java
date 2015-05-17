package com.compsci.display;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import com.compsci.format.GuiTextHandler;

public class GuiStream extends OutputStream {

	public static GuiStream guiStream = new GuiStream();
	public static PrintStream printStream = new PrintStream(guiStream, true);
	
	@Override
	public void write(int b) throws IOException {
		
		write(String.valueOf((char)b));
	}

	@Override
    public void write(byte[] b, int off, int len) throws IOException {
		write(new String(b, off, len));
    }
 
    @Override
    public void write(byte[] b) throws IOException {
    	write(new String(b, 0, b.length));
    }
    
    public void write(String s) throws IOException {
    	GuiTextHandler.writeToGui("SYSTEM", s);
    }
}
