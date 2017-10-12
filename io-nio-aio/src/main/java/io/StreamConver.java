package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

/**
 * 
 */
public class StreamConver {

	/**
	 * OutputStreamWriter
	 */
	@Test
	public void test01() throws Exception {
		File f = new File("dir.txt");
		FileOutputStream fos = new FileOutputStream(f);
		
		OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
		osw.write("你好");
		osw.write("cvb");
		osw.close();
	}
	
	/**
	 * InputStreamReader
	 */
	@Test
	public void test02() throws Exception {
		File f = new File("dir.txt");
		FileInputStream fos = new FileInputStream(f);
		
		InputStreamReader osr = new InputStreamReader(fos, "utf-8");
		
		char[] c = new char[100];
		osr.read(c, 0, c.length);
		System.out.println(new String(c));
		osr.close();
	}
}
