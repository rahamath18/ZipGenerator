package com.test.zip;

import java.io.File;

import com.test.zip.util.OSUtil;

public class TestZipAFolder {

	public static void main(String[] args) {
		
		try {

			String sourceDir = "D:/ziputil/";
			
			sourceDir = OSUtil.getBasePathForOS(sourceDir);
			
			if(!new File(sourceDir).exists()) {
				System.err.println("Source folder (" + sourceDir +") does not exists!");
				System.out.println("\nCreate source folder ( " + sourceDir +").");
				System.exit(0);
			}
			if(new File(sourceDir + File.separator).isDirectory() && new File(sourceDir ).list().length <=0) {
				System.err.println("Source folder (" + sourceDir + ") is empty!");
				System.out.println("\nCreate one or multiple files in ( " + sourceDir + ").");
				System.exit(0);
			}			
			
			String destZipFileName = "all_content.zip";
			
			ZipFileGenerator.zipAFolder(sourceDir, destZipFileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
