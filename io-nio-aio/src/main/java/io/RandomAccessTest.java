package io;

import java.io.File;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * 测试RandomAccessFile
 */
public class RandomAccessTest {

	/**
	 * 向文件写入内容
	 */
	@Test
	public void test01() throws Exception {
		File f = new File("dir.txt");
		
		RandomAccessFile r = new RandomAccessFile(f, "rw");
		r.writeChars("abc\n");
		r.writeChars("def");
		
		r.close();
	}
	
	/**
	 * 读出文件中内容
	 */
	@Test
	public void test02() throws Exception {
		File f = new File("dir.txt");
		
		RandomAccessFile r = new RandomAccessFile(f, "r");
		r.seek(6); //跳过6个字节
		
		byte[] b = new byte[100];
		r.read(b, 0, b.length);
		System.out.println(new String(b));
	}
	
	/**
	 * 向文件末尾写内容
	 */
	@Test
	public void test03() throws Exception {
		File f = new File("dir.txt");
		
		RandomAccessFile r = new RandomAccessFile(f, "rw");
		long length = r.length();
		r.seek(length);
		r.writeChars("\nhig");
		r.close();
		
		
	}
}
