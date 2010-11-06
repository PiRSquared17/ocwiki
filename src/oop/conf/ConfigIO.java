package oop.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;

import oop.controller.ConfigIOException;
import oop.util.XMLUtils;

public class ConfigIO {

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
		File[] confFiles = dir.listFiles(CONFIG_FILE_FILTER);
		for (File file : confFiles) {
			FileInputStream input = null;
			try {
				try {
					input = new FileInputStream(file);
					XMLUtils.getXStream().fromXML(input, config);
				} finally {
					if (input != null) {
						input.close();
					}
				}
			} catch (Exception e) {
				throw new ConfigIOException(file, e);
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
