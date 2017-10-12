package io;

import java.io.File;
import java.io.FileFilter;

import org.junit.Test;

/**
 *File代表文件和目录, 存在或不存在的
 */
public class FileTest {

	/**
	 * 创建目录,文件 和 删除   
	 *  mkdir() 一级目录
	 *  mkdirs() 多级目录
	 *  createNewFile() : 文件
	 */
	@Test
	public void test01() throws Exception {
		//通过相对路径
		File f = new File("dir");
		f.mkdir();
		f.delete();
		
		/*
		//通过绝对路径,创建多级目录
		File f1 = new File("D:" + File.separator + "ao" + File.separator + "new");
		f1.mkdirs();
		
		//创建文件
		File f2 = new File("D:" + File.separator + "ao" + File.separator + "new.txt");
		f2.createNewFile();
		*/
	}
	
	/**
	 * 查询文件和目录
	 * list() : 包括隐藏的 ,返回的是名字
	 * listFiles() : 返回的是绝对路径
	 */
	@Test
	public void test02() {
		File f = new File("D:" + File.separator + "ao");
		
		//list()
		String[] list = f.list();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
		
		//listFiles()
		File[] files = f.listFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i]);
		}
		
	}
	
	/**
	 * 判断是否是目录
	 */
	@Test
	public void test03() {
		File f = new File("D:" + File.separator + "ao" + File.separator + "new.txt");
		System.out.println(f.isDirectory() ? "yes" : "no");
	}
	
	/**
	 * 文件过滤器
	 */
	@Test
	public void test04() {
		File f = new File("D:" + File.separator + "ao");
		
		FileFilter ff = new FileFilter() {
			public boolean accept(File pathname) {
				String s = pathname.getName().toLowerCase();
				
				if(s.endsWith(".txt")) {
					return true;
				}
				return false;
			}
		};
		
		File[] flist = f.listFiles(ff);
		
		if (flist == null) {
			System.out.println("the filelist is null");
			return;
		}

		for (File fs : flist) {
			System.out.println(fs);
		}
	}
}
