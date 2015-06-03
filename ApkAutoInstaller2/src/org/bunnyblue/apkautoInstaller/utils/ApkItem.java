/**
 * 
 */
package org.bunnyblue.apkautoInstaller.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * @author BunnyBlue
 *
 */
public class ApkItem {
	private String packageName;

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @return the versionCode
	 */
	public int getVersionCode() {
		return versionCode;
	}

	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @return the icon
	 */
	public Drawable getIcon() {
		return icon;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	private int versionCode;
	private String versionName;
	private Drawable icon;
	private String appName;
	private String size;
	private String path;

	/**
	 * 
	 */
	public ApkItem(String archiveFilePath, PackageManager pm) {

		PackageInfo info = pm.getPackageArchiveInfo(archiveFilePath,
				PackageManager.GET_ACTIVITIES);
		path = archiveFilePath;
		if (info != null) {
			ApplicationInfo appInfo = info.applicationInfo;
			appInfo.sourceDir = archiveFilePath;
			appInfo.publicSourceDir = archiveFilePath;
			try {
				icon = appInfo.loadIcon(pm);
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
			appName = appInfo.loadLabel(pm).toString();
			packageName = appInfo.packageName; // 得到安装包名称
			versionCode = info.versionCode;
			versionName = info.versionName;
		}

	}
}
