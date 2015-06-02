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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.os.Build;

/**
 * @author BunnyBlue
 *
 */
public class SystemUtil {
	private static final String BUILD_PROP_FILE = "/system/build.prop";
	private static final String PROP_NAME_MIUI_VERSION_CODE = "ro.miui.ui.version.code";

	public static boolean isBlur() {
		try {
			return Build.BRAND.toLowerCase().contains("blur");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isZTE() {
		try {
			return Build.BRAND.toLowerCase().contains("zte");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isGalaxyS2() {
		try {
			String toLowerCase = Build.MODEL.toLowerCase();
			boolean result = toLowerCase.contains("gt-i9100") || toLowerCase.contains("gt-i9108")
					|| toLowerCase.contains("gt-i9103");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isMX() {
		try {
			String toLowerCase = Build.MODEL.toLowerCase();
			boolean result = toLowerCase.contains("m353") || toLowerCase.contains("mx4");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isNexusS() {
		try {
			return Build.MODEL.toLowerCase().contains("nexus s");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isKindleFire() {
		try {
			return Build.MODEL.toLowerCase().contains("kindle fire");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isMIUI() {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(BUILD_PROP_FILE)));
			String readLine;
			do {
				readLine = bufferedReader.readLine();
				if (readLine == null) {
					return false;
				}
			} while (!readLine.startsWith(PROP_NAME_MIUI_VERSION_CODE));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static boolean isSmartisan() {
		try {
			return Build.BRAND.toLowerCase().contains("smartisan");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isFlymeOs() {
		try {
			return Build.FINGERPRINT.toLowerCase().contains("flyme");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isHuaWeiG520() {
		try {
			return Build.MODEL.toUpperCase().contains("HUAWEI G520");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
