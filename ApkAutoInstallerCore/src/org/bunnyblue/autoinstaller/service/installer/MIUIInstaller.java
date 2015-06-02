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
package org.bunnyblue.autoinstaller.service.installer;

import org.bunnyblue.autoinstaller.R;
import org.bunnyblue.autoinstaller.util.AutoInstallerContext;
import org.bunnyblue.autoinstaller.util.InstallerUtils;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class MIUIInstaller extends DefaultInstaller {
	private final String app_auto_install_done_miui;
	private final String app_auto_install_done;
	private final String install_confirm;
	private final String app_auto_install_finish_miui;
	private final String install;

	public MIUIInstaller() {
		this.install = AutoInstallerContext.getContext().getResources().getString(R.string.app_auto_install_install);
		this.app_auto_install_done_miui = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_done_miui);
		this.app_auto_install_done = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_done);
		this.install_confirm = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_confirm);
		this.app_auto_install_finish_miui = AutoInstallerContext.getContext().getString(
				R.string.app_auto_install_finish_miui);
	}

	@Override
	public void onInstall(String str, AccessibilityNodeInfo accessibilityNodeInfo,
			AccessibilityNodeInfo accessibilityNodeInfo2, AccessibilityEvent accessibilityEvent) {
		for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils.contains(accessibilityNodeInfo,
				this.install_confirm)) {
			InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.install_confirm);
			showAnim();
		}
		for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils
				.contains(accessibilityNodeInfo, this.install)) {
			InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.install);
			showAnim();
		}
		for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils.contains(accessibilityNodeInfo,
				this.app_auto_install_done_miui)) {
			InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.app_auto_install_done_miui);
		}
		for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils.contains(accessibilityNodeInfo,
				this.app_auto_install_done)) {
			InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.app_auto_install_done);
		}
		for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils.contains(accessibilityNodeInfo,
				this.app_auto_install_finish_miui)) {
			InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.app_auto_install_finish_miui);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bunnyblue.autoinstaller.util.DefaultInstaller#onInterrupt()
	 */
	@Override
	public void onInterrupt() {
		// TODO Auto-generated method stub
		super.onInterrupt();
	}

	@Override
	public String getPackageinstallerName() {
		return "com.android.packageinstaller";
	}
}