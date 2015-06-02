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

import org.bunnyblue.autoinstaller.util.AutoInstallerContext;
import org.bunnyblue.autoinstaller.util.InstallerUtils;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class DefaultInstallerService implements IAccessibilityService {
	private static WakeLock mWakeLock;
	private InstallerGenerator mInstallerGenerator;

	@Override
	public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, AccessibilityService accessibilityService) {
		AccessibilityNodeInfo sourceNodeInfo = accessibilityEvent.getSource();
		if (sourceNodeInfo != null && sourceNodeInfo.getPackageName() != null) {
			if (this.mInstallerGenerator == null) {

				this.mInstallerGenerator = InstallerGenerator.getGenerator(sourceNodeInfo);
			}
			AccessibilityNodeInfo mParentNodeInfo = getParentNodeInfo(sourceNodeInfo);
			@SuppressWarnings("deprecation")
			List<?> recentTasks = ((ActivityManager) accessibilityService.getSystemService("activity")).getRecentTasks(
					1,
					0);
			if (recentTasks.size() != 0) {
				Intent intent = ((RecentTaskInfo) recentTasks.get(0)).baseIntent;
				if (intent != null) {
					ComponentName component = intent.getComponent();
					String installPkgPath = InstallerUtils.getInstallPkgPath(intent);
					if (InstallerUtils.isAutoInstallable(installPkgPath, component)) {
						if (mWakeLock == null) {
							mWakeLock = newWakeLock();
						}
						mWakeLock.acquire(300000);
						this.mInstallerGenerator.getInstaller().onInstall(installPkgPath, mParentNodeInfo,
								sourceNodeInfo,
								accessibilityEvent);
					}
				}
			}
		}
	}

	private WakeLock newWakeLock() {
		return ((PowerManager) AutoInstallerContext.getContext().getSystemService("power")).newWakeLock(268435482,
				"AUTO_INSTALL_WAKE_LOCK");
	}

	@Override
	public void onInterrupt() {
		if (this.mInstallerGenerator != null) {
			this.mInstallerGenerator.getInstaller().onInterrupt();
		}
	}

	private AccessibilityNodeInfo getParentNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
		AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent();
		if (parent == null || parent == accessibilityNodeInfo) {
			return accessibilityNodeInfo;
		}
		return getParentNodeInfo(parent);
	}
}