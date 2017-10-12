package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *ObjectInputStream , ObjectOutputStream , Serializable
 */
public class ObjectStreamTest {
	public static void main(String[] args) throws Exception {
		//写
		FileOutputStream fos = new FileOutputStream("D:" + File.separator + "ao" + File.separator + "new.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(new T());
		oos.close();
		
		//读
		FileInputStream fis = new FileInputStream("D:" + File.separator + "ao" + File.separator + "new.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		T object =(T)ois.readObject();
		System.out.println(object.i + "====" + object.j);
	}
}

class T implements Serializable{
	 transient int i = 3; //transient 修饰的不参与序列化,不管原来的值是多少,通过流读取输出的就是0
	 int j = 4;
}