/**
 * 
 */
package org.bunnyblue.autoinstaller.util;

/**
 * @author BunnyBlue
 *
 */
public interface IApkInstaller {
	public void startInstall(String name, String path);

	public void endInstall(String name, String path);

}
