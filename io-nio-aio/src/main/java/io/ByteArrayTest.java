package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;

/**
 * ByteArrayInputStream, ByteArrayOutputStream
 */
public class ByteArrayTest {

	/**
	 * 字节
	 */
	@Test
	public void test01() throws Exception {
		byte[] b = {0,1,2,3,4,5,6,7,8,9,10};
		
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		
		for (int i = 0; i < 4; i++) {
			if(bais.available() > 0) {
				int read = bais.read();
				System.out.println(read);
			}
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(b, 1, 3);
		System.out.println(baos);
	}
}
