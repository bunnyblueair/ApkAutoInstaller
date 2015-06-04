/**
 * 
 */
package org.bunnyblue.apkautoInstaller;

import org.bunnyblue.autoinstaller.util.AutoInstallerContext;

import android.app.Application;

/**
 * @author BunnyBlue
 *
 */
public class CoreApp extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		AutoInstallerContext.initApplication(this,
				"org.bunnyblue.apkautoInstaller/org.bunnyblue.autoinstaller.service.AccessibilityServices",
				"org.bunnyblue.apkautoInstaller/org.bunnyblue.apkautoInstaller.BootActivity");
	}

}
