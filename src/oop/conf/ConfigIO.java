package oop.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;

import oop.controller.ConfigIOException;
import oop.util.XMLUtils;

public class ConfigIO {

	public static final String DEFAULT_FILE_NAME = "config.xml";
	
	public static void load(File file, Config config) {
		FileInputStream input = null;
		try {
			try {
				input = new FileInputStream(file);
				XMLUtils.getXStream().fromXML(input, config);
				config.doneLoading();
			} finally {
				if (input != null) {
					input.close();
				}
			}
		} catch (Exception e) {
			throw new ConfigIOException(file, e);
		}
	}

	public static void loadDirectory(Config config, String dirPath) {
		File dir = new File(dirPath);
		File defaultFile = new File(dirPath + "/" + DEFAULT_FILE_NAME);
		if (defaultFile.exists()) {
			load(defaultFile, config);
		}
		File[] confFiles = dir.listFiles(CONFIG_FILE_FILTER);
		for (File file : confFiles) {
			if (!file.equals(defaultFile)) {
				load(file, config);
			}
		}
		config.doneLoading();
	}

	private static FilenameFilter CONFIG_FILE_FILTER = new FilenameFilter() {

		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(".xml");
		}
	};

}
