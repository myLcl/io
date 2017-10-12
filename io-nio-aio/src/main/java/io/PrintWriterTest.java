package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.junit.Test;

/**
 * 测试PrintWriter
 */
public class PrintWriterTest {

	/**
	 * 包装一个OutputStream
	 */
	@Test
	public void test01() throws Exception {
		File f = new File("dir.txt");
		FileOutputStream fos = new FileOutputStream(f);
		
		PrintWriter pw = new PrintWriter(fos);
		pw.write("klm");
		pw.close();
	}
	
	/**
	 * 包装一个File
	 */
	@Test
	public void test02() throws Exception {
		File f = new File("dir.txt");
		PrintWriter pw = new PrintWriter(f);
		
		pw.write("opq");
		pw.close();
	}
	
	/**
	 * 包装一个String
	 */
	@Test
	public void test03() throws Exception {
		PrintWriter pw = new PrintWriter("dir.txt");
		
		pw.write("zxc");
		pw.close();
	}
	
	
}
