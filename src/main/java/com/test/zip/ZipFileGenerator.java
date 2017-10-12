package com.test.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.test.zip.util.OSUtil;

public class ZipFileGenerator {

	public static void zipSingleFile(String sourceDir, String sourceFileName, String destZipFileName)
			throws IOException, FileNotFoundException {

		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fos = new FileOutputStream(sourceDir + File.separator + destZipFileName);
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry(sourceFileName);
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream(sourceDir + File.separator + sourceFileName);

			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			in.close();
			zos.closeEntry();

			zos.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void zipAFolder(String sourceDir, String outputFile) {
		List<String> fileList = new ArrayList<String>();
		generateFileList(new File(sourceDir), sourceDir, fileList);
		zipIt(outputFile, sourceDir, fileList);
	}

	private static void zipIt(String zipFile, String sourceDir, List<String> fileList) {

		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fos = new FileOutputStream(sourceDir + File.separator + zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			System.out.println("Output to Zip : " + sourceDir + File.separator + zipFile);

			for (String file : fileList) {

				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = new FileInputStream(sourceDir + File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
			}

			zos.closeEntry();
			zos.close();

			System.out.println("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void generateFileList(File node, String sourceDir, List<String> fileList) {

		if (node.isFile()) {
			if(OSUtil.IS_MAC && node.toString().indexOf("DS_Store") >= 0) {
				//ignore
			} else {
				fileList.add(generateZipEntry(node.getAbsoluteFile().toString(), sourceDir));
			}
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename), sourceDir, fileList);
			}
		}
	}

	private static String generateZipEntry(String file, String sourceDir) {
		return file.substring(sourceDir.length(), file.length());
	}

}
