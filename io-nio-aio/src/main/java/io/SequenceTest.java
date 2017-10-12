package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;

public class SequenceTest {

	/**
	 *合并两个流 
	 */
	@Test
	public void test01() throws Exception {
		String path = "D:" + File.separator + "ao" + File.separator + "new.txt";
		
		OutputStream os = new FileOutputStream(new File(path));
	    InputStream is1 = new FileInputStream(new File("dir.txt"));
	    InputStream is2 = new FileInputStream(new File("dir1.txt"));

	    //关键 --> 将两个输入添加进合并流
	    SequenceInputStream sis = new SequenceInputStream(is1, is2);

	    //按字节数组读取
	    byte[] buffer = new byte[1024];
	    int count = 0;
	    while ((count = sis.read(buffer)) != -1) {
	        os.write(buffer, 0, count);
	    }

	    os.flush();
	    os.close();
	    sis.close();
	}
	
	/**
	 *合并多个流 
	 */
	@Test
	public void test02() throws Exception {
		String path = "D:" + File.separator + "ao" + File.separator + "new.txt";
		
		OutputStream os = new FileOutputStream(new File(path));
	    InputStream is1 = new FileInputStream(new File("dir.txt"));
	    InputStream is2 = new FileInputStream(new File("dir1.txt"));
	    InputStream is3 = new FileInputStream(new File("dir2.txt"));

	    //关键 --> 通过 Vector 添加多个输入流
	    Vector<InputStream> vector = new Vector<InputStream>(3);
	    vector.add(is1);
	    vector.add(is2);
	    vector.add(is3);
	    
	   //将多个输入流添加进合并流
	    Enumeration<InputStream> en = vector.elements();
	    SequenceInputStream sis = new SequenceInputStream(en);

	   //按字节数组读取
	    byte[] buffer = new byte[1024];
	    int count = 0;
	    while ((count = sis.read(buffer)) != -1) {
	        os.write(buffer, 0, count);
	    }

	    os.flush();
	    os.close();
	    sis.close();
	}
}
