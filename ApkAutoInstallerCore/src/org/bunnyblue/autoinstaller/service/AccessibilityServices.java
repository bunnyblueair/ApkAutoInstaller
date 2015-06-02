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
package org.bunnyblue.autoinstaller.service;

import java.util.ArrayList;
import java.util.List;

import org.bunnyblue.autoinstaller.service.installer.DefaultInstallerService;
import org.bunnyblue.autoinstaller.service.installer.IAccessibilityService;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityServices extends AccessibilityService {
	private static List<IInstallMonitor> mInstallMonitors;
	private static List<IAccessibilityService> mIAccessibilityServices;

	public interface IInstallMonitor {
		void onServiceAlive(boolean isAlive);
	}

	static {

		mInstallMonitors = new ArrayList<IInstallMonitor>();
		mIAccessibilityServices = new ArrayList<IAccessibilityService>();
	}

	private void onServiceAlive(boolean isAlive) {
		for (IInstallMonitor monitor : mInstallMonitors) {
			monitor.onServiceAlive(isAlive);
		}
	}

	public static void addMonitor(IInstallMonitor monitor) {
		if (!mInstallMonitors.contains(monitor)) {
			mInstallMonitors.add(monitor);
		}
	}

	public static void removeMonitor(IInstallMonitor monitor) {
		mInstallMonitors.remove(monitor);
	}

	public AccessibilityServices() {
		mIAccessibilityServices.add(new DefaultInstallerService());
	}

	@Override
	public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
		for (IAccessibilityService mAccessibilityService : mIAccessibilityServices) {
			mAccessibilityService.onAccessibilityEvent(accessibilityEvent, this);
		}

	}

	@Override
	public boolean onUnbind(Intent intent) {

		onServiceAlive(false);
		return super.onUnbind(intent);
	}

	@Override
	protected void onServiceConnected() {
		super.onServiceConnected();
		onServiceAlive(true);
	}

	@Override
	public void onInterrupt() {
		for (IAccessibilityService mAccessibilityService : mIAccessibilityServices) {
			mAccessibilityService.onInterrupt();
		}
	}
}