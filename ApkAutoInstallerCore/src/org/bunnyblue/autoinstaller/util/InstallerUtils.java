/**
 * ApkAutoInstaller Project
 The MIT License (MIT) Copyright (c) 2015 Bunny Blue<br>
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package org.bunnyblue.autoinstaller.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

public class InstallerUtils {

	public static List<AccessibilityNodeInfo> contains(AccessibilityNodeInfo parentNodeInfo, String buttonName) {
		List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText = parentNodeInfo
				.findAccessibilityNodeInfosByText(buttonName);
		List<AccessibilityNodeInfo> arrayList = new ArrayList<AccessibilityNodeInfo>();
		for (AccessibilityNodeInfo accessibilityNodeInfo : findAccessibilityNodeInfosByText) {
			CharSequence text = accessibilityNodeInfo.getText();

			if (!TextUtils.isEmpty(text) && text.toString().equals(buttonName)) {
				arrayList.add(accessibilityNodeInfo);
			}
		}
		return arrayList;
	}

	public static boolean performOnclick(AccessibilityNodeInfo accessibilityNodeInfo, String buttonName) {
		if (accessibilityNodeInfo == null || !accessibilityNodeInfo.isClickable()) {
			return false;
		}
		boolean performAction = accessibilityNodeInfo.performAction(16);
		InstallerUtils.log(buttonName, performAction);
		return performAction;
	}

	public static void log(String button_name, boolean button_click_result) {
		Log.i("AutoInstall", "button_name" + button_name);
		Log.i("AutoInstall", "button_click_result=" + button_click_result);

	}

	public static String getInstallPkgPath(Intent intent) {
		if (intent.getData() == null) {
			return "";
		}
		return intent.getData().getPath();
	}

	public static boolean isAutoInstallActivity(ComponentName componentName) {
		if ((componentName.getPackageName() + "/" + componentName.getClassName())
				.equals(AutoInstallerContext.getTargetActivity())) {
			return true;
		}
		return false;
	}

	public static boolean isAutoInstallable(String str, ComponentName componentName) {
		return !TextUtils.isEmpty(str) || InstallerUtils.isAutoInstallActivity(componentName);
	}

	public static boolean isEnableAutoInstall() {
		Context mContext = AutoInstallerContext.getContext();
		SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(':');
		if (!SettingsUtil.isAccessibiltiyEnable(mContext)) {
			return false;
		}
		String string = Secure.getString(mContext.getContentResolver(), "enabled_accessibility_services");
		if (string == null) {
			return false;
		}
		simpleStringSplitter.setString(string);
		while (simpleStringSplitter.hasNext()) {
			if (simpleStringSplitter.next().equalsIgnoreCase(
					AutoInstallerContext.getTargetService())) {
				return true;
			}
		}
		return false;
	}

}