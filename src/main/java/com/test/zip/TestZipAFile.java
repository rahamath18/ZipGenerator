package com.test.zip;

import java.io.File;

import com.test.zip.util.OSUtil;

public class TestZipAFile {

	public static void main(String[] args) {
		
		try {

			String sourceDir = "D:/ziputil";
			sourceDir = OSUtil.getBasePathForOS(sourceDir);
			
			if(!new File(sourceDir).exists()) {
				System.err.println("Source folder (" + sourceDir +") does not exists!");
				System.out.println("\nCreate source folder ( " + sourceDir +").");
				System.exit(0);
			}
			String sourceFileName = "content.txt";
			
			if(!new File(sourceDir + File.separator + sourceFileName).exists()) {
				System.err.println("Source content file (" + sourceDir + File.separator + sourceFileName +") does not exists!");
				System.out.println("\nCreate source content file ( " + sourceDir + File.separator + sourceFileName +").");
				System.exit(0);
			}
			
			String destZipFileName = "content.zip";
			
			ZipFileGenerator.zipSingleFile(sourceDir, sourceFileName, destZipFileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
