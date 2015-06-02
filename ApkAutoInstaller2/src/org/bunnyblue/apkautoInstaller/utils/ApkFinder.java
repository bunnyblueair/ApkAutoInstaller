/**
 * 
 */
package org.bunnyblue.apkautoInstaller.utils;

import java.io.File;
import java.util.LinkedList;

/**
 * @author BunnyBlue
 *
 */
public class ApkFinder {
	public static void findApks(File dirFile, LinkedList<String> apkPaths) { // 假定是文件夹
		File[] files = dirFile.listFiles(); // 获取文件夹下面的所有文件
		for (File f : files) {
			// 判断是否为文件夹
			if (f.isDirectory()) {

				findApks(f, apkPaths); // 如果是文件夹，重新遍历
			} else { // 如果是文件 就打印文件的路径
				if (f.getAbsolutePath().endsWith(".apk")) {
					apkPaths.add(f.getAbsolutePath());
				}
			}
		}
	}
}
