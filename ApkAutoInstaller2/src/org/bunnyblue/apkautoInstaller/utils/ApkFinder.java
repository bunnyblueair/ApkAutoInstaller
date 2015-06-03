/**
 * 
 */
package org.bunnyblue.apkautoInstaller.utils;

import java.io.File;
import java.util.LinkedList;

import org.bunnyblue.autoinstaller.util.AutoInstallerContext;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;

/**
 * @author BunnyBlue
 *
 */
public class ApkFinder {
	public static void findApks(File dirFile, LinkedList<ApkItem> apkPaths, PackageManager pm) { // 假定是文件夹
		File[] files = dirFile.listFiles(); // 获取文件夹下面的所有文件
		for (File f : files) {
			// 判断是否为文件夹
			if (f.isDirectory()) {

				findApks(f, apkPaths, pm); // 如果是文件夹，重新遍历
			} else { // 如果是文件 就打印文件的路径
				if (f.getAbsolutePath().endsWith(".apk")) {
					Intent mIntent = new Intent("ACTION_ApkPickerReceiver_PICKED");
					mIntent.putExtra("path", f.getAbsolutePath());
					AutoInstallerContext.getContext().sendBroadcast(mIntent);
					ApkItem apkItem = new ApkItem(f.getAbsolutePath(), pm);

					pureItem(apkPaths, apkItem, pm);

					// apkPaths.add(apkItem);
				}
			}
		}
	}

	private synchronized static void pureItem(LinkedList<ApkItem> apkPaths, ApkItem apkItem, PackageManager pm) {
		if (TextUtils.isEmpty(apkItem.getPackageName())) {
			System.out.println("ApkFinder.pureItem(null)");
			return;
		}
		try {
			PackageInfo packageInfo = pm.getPackageInfo(apkItem.getPackageName(), PackageManager.GET_ACTIVITIES);

			if (packageInfo != null && packageInfo.versionCode > apkItem.getVersionCode()) {
				System.out.println("ApkFinder.pureItem(new  versin installed)");
				return;
			}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		LinkedList<ApkItem> dep = new LinkedList<ApkItem>();
		for (int i = 0; i < apkPaths.size(); i++) {
			ApkItem mItem = apkPaths.get(i);
			// System.err.println(mItem.getPackageName() + "|" +
			// apkItem.getPackageName());
			if (mItem.getPackageName().equals(apkItem.getPackageName())) {

				if (mItem.getVersionCode() <= apkItem.getVersionCode()) {
					dep.add(mItem);
					// apkPaths.add(apkItem);
					// return;
				} else {
					apkItem = null;
				}
			}
		}
		apkPaths.removeAll(dep);
		if (apkItem != null)
			apkPaths.add(apkItem);

	}
}
