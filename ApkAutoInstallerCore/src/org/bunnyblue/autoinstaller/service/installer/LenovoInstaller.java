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

import java.util.List;

import org.bunnyblue.autoinstaller.R;
import org.bunnyblue.autoinstaller.util.AutoInstallerContext;
import org.bunnyblue.autoinstaller.util.InstallerUtils;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class LenovoInstaller extends DefaultInstaller {
	private final String app_auto_install_lenovo_pass_le_security;

	private final String app_auto_install_confirm;
	private final String app_auto_install_lenovo_install_success_button;
	private final String app_auto_install_lenovo_no_perm;
	private final String app_auto_install_lenovo_perm;
	private final String app_auto_install_lenovo_install_success;
	private final String app_auto_install_install;

	public LenovoInstaller() {
		this.app_auto_install_install = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_install);
		this.app_auto_install_confirm = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_confirm);
		this.app_auto_install_lenovo_install_success_button = AutoInstallerContext.getContext().getString(
				R.string.app_auto_install_lenovo_install_success_button);
		this.app_auto_install_lenovo_no_perm = AutoInstallerContext.getContext().getString(
				R.string.app_auto_install_lenovo_no_perm);
		this.app_auto_install_lenovo_perm = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_lenovo_perm);
		this.app_auto_install_lenovo_install_success = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_lenovo_install_success);
		this.app_auto_install_lenovo_pass_le_security = AutoInstallerContext.getContext().getResources()
				.getString(R.string.app_auto_install_lenovo_pass_le_security);

	}

	@Override
	public void onInstall(String str, AccessibilityNodeInfo parentNodeInfo,
			AccessibilityNodeInfo sourceNodeInfo, AccessibilityEvent accessibilityEvent) {
		List<AccessibilityNodeInfo> app_auto_install_lenovo_perm = InstallerUtils.contains(parentNodeInfo,
				this.app_auto_install_lenovo_perm);
		List<AccessibilityNodeInfo> app_auto_install_lenovo_no_perm = InstallerUtils
				.contains(parentNodeInfo, this.app_auto_install_lenovo_no_perm);
		List<AccessibilityNodeInfo> app_auto_install_lenovo_pass_le_security = InstallerUtils.contains(parentNodeInfo,
				this.app_auto_install_lenovo_pass_le_security);
		if (app_auto_install_lenovo_perm.size() > 0 || app_auto_install_lenovo_no_perm.size() > 0
				|| app_auto_install_lenovo_pass_le_security.size() > 0) {
			for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils
					.contains(parentNodeInfo, this.app_auto_install_install)) {
				InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.app_auto_install_install);
				showAnim();
			}
			for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils.contains(parentNodeInfo,
					this.app_auto_install_confirm)) {
				InstallerUtils.performOnclick(mAccessibilityNodeInfo, this.app_auto_install_confirm);
				showAnim();
			}
			return;
		}
		List<AccessibilityNodeInfo> app_auto_install_lenovo_install_success = InstallerUtils.contains(parentNodeInfo,
				this.app_auto_install_lenovo_install_success);

		if (app_auto_install_lenovo_install_success.size() > 0) {
			for (AccessibilityNodeInfo mAccessibilityNodeInfo : InstallerUtils.contains(parentNodeInfo,
					this.app_auto_install_lenovo_install_success_button)) {
				InstallerUtils.performOnclick(mAccessibilityNodeInfo,
						this.app_auto_install_lenovo_install_success_button);
			}
		}
	}

	@Override
	public void onInterrupt() {

		super.onInterrupt();
	}

	@Override
	public String getPackageinstallerName() {
		return "com.lenovo.safecenter";
	}
}