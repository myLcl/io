package io;

import java.io.CharArrayReader;

import org.junit.Test;

/**
 *CharArrayReader
 */
public class ChatArrayTest {

	@Test
	public void test01() throws Exception {
		char[] a = {'0','1','2','3'};
		
		CharArrayReader car = new CharArrayReader(a);
		
		for (int i = 0; i < a.length; i++) {
			if(car.ready() ) {
				char read =(char) car.read();
				System.out.println(read);
			}
		}
	}
}
