package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * DataOutputStream , DataInputStream
 *注意: 写入的顺序要和读取的顺序一致
 */
public class DataStreamTest {

	public static void main(String[] args) throws Exception {
		String path = "D:" + File.separator + "ao" + File.separator + "new.txt";
		
		//写
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(path)));
        dos.writeBoolean(true);
        dos.writeByte((byte) 0x41);
        dos.writeChar((char) 0x4243);
        dos.writeShort((short) 0x4445);
        dos.writeInt(0x12345678);
        dos.writeLong(0x0FEDCBA987654321L);
        dos.writeUTF("abcdefghijklmnopqrstuvwxyz严12");

        dos.close();
        
        //读
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(path)));
        System.out.println(dis.readBoolean());
        System.out.println(new String(new byte[] { dis.readByte() }));
        System.out.println(dis.readChar());
        System.out.println(dis.readShort());
        System.out.println(dis.readInt());
        System.out.println(dis.readLong());
        System.out.println(dis.readUTF());

        dis.close();
	}
}
