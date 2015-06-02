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

public class FlymeInstaller extends DefaultInstaller {
	private final String app_auto_install_confirm;
	private final String app_auto_install_install;

	public FlymeInstaller() {
		this.app_auto_install_install = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_install);
		this.app_auto_install_confirm = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_confirm);
	}

	@Override
	public void onInstall(String installPkgPath, AccessibilityNodeInfo parentNodeInfo,
			AccessibilityNodeInfo sourceNodeInfo, AccessibilityEvent accessibilityEvent) {
		for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils.contains(parentNodeInfo,
				this.app_auto_install_confirm)) {
			InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.app_auto_install_confirm);
			showAnim();
		}
		for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils.contains(parentNodeInfo,
				this.app_auto_install_install)) {
			InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.app_auto_install_install);
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