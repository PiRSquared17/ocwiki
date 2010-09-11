package oop.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import oop.util.XMLUtils;

public class ConfigIO {

	public static void load(File file, Config config) throws IOException {
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);
			XMLUtils.getXStream().fromXML(input, config);
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	public static void loadDirectory(Config config, String dirPath) {
		File dir = new File(dirPath);
		File[] confFiles = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		});
		try {
			for (File file : confFiles) {
				load(file, config);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
