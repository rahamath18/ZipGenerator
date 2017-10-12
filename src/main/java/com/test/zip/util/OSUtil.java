package com.test.zip.util;

public class OSUtil {

	public static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean IS_UNIX = OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0;

	public static boolean IS_WINDOWS = OS.indexOf("win") >= 0;

	public static boolean IS_MAC = OS.indexOf("mac") >= 0;

	public static boolean IS_SOLARIS = OS.indexOf("sunos") >= 0;

	public static String getBasePathForOS(String basePath) {

		if (IS_WINDOWS) {
			return basePath;
		} else {
			return basePath.substring(basePath.indexOf(":") + 1, basePath.length());
		}
	}

	public static void main(String[] args) {

		System.out.println(OS);

		if (IS_WINDOWS) {
			System.out.println("This is Windows");
		} else if (IS_MAC) {
			System.out.println("This is Mac");
		} else if (IS_UNIX) {
			System.out.println("This is Unix or Linux");
		} else if (IS_SOLARIS) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
		
		String baString = getBasePathForOS("D:/raws");
		System.out.println(baString);
		
	}

}
