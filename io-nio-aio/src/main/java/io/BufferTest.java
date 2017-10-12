package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.Test;

/**
 * BufferedWriter 和  BufferedReader
 */
public class BufferTest {

	@Test
	public void test01() throws Exception {
		File f = new File("D:" + File.separator + "ao" + File.separator + "new.txt");
		//写
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		char[] cbuf = new char[]{'1','2','3','4'};
		bw.write(cbuf , 0, cbuf.length);
		bw.flush();
		bw.close();
		
		//读
		BufferedReader br = new BufferedReader(new FileReader(f));
		for (int i = 0; i < cbuf.length; i++) {
			if(br.ready()) {
				char temp =(char) br.read();
				System.out.println(temp);
			}

		}
	}
}
