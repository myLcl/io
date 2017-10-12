package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.Test;

/**
 * FileWriter , FileReader , FilterInputStream, FileOutputStream
 */
public class FileStreamTest {

	/**
	 * FileWriter , FileReader
	 */
	@Test
	public void test01() throws Exception {
		File f = new File("D:" + File.separator + "ao" + File.separator + "new.txt");
		
		FileWriter fw = new FileWriter(f);
		fw.write("你好!");
		fw.close();
		
		FileReader fr = new FileReader(f);
		char[] c1 = new char[10];
		fr.read(c1, 0, c1.length);
		System.out.println(new String(c1));
		fr.close();
		
	}
	
	/**
	 * FilterInputStream , FileOutputStream
	 */
	@Test
	public void test02() throws Exception {
		File f = new File("D:" + File.separator + "ao" + File.separator + "new.txt");
		
		//FileOutputStream
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(2);
		
		//FilterInputStream
		FileInputStream fis = new FileInputStream(f);
		int tag = 0;
		
		while( (tag = fis.read()) != -1 ) {
			//fis是字节流，若是有汉字就显示不正常了,使用reader就解决了
			System.out.println((char)tag);
		}
		
		fos.close();
		fis.close();
	}
}
