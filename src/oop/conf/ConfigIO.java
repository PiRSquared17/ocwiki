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
			config.doneLoading();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	public static void loadDirectory(Config config, String dirPath)
			throws IOException {
		File dir = new File(dirPath);
		File[] confFiles = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		});
		boolean read = false;
		try {
			for (File file : confFiles) {
				FileInputStream input = null;
				try {
					input = new FileInputStream(file);
					XMLUtils.getXStream().fromXML(input, config);
					read = true;
				} finally {
					if (input != null) {
						input.close();
					}
				}
			}
		} finally {
			if (read) {
				config.doneLoading();
			}
		}
	}

}
