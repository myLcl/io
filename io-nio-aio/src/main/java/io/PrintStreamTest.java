package io;

import java.io.File;
import java.io.PrintStream;

import org.junit.Test;

/**
 * PrintStream
 */
public class PrintStreamTest {

	@Test
	public void test01() throws Exception {
		byte[] b = {1,2,3,4,5};
		File f= new File("dir.txt");
		
		PrintStream ps = new PrintStream(f);
		ps.write(b);
		
		ps.print(b);
		
		
	}
}
