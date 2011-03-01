package org.ocwiki.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import org.ocwiki.util.XMLUtils;

public class ConfigIO {

	public static final String DEFAULT_FILE_NAME = "default.xml";
	
	public static void load(File file, Config config) {
		try {
			if (file.isDirectory()) {
				loadDirectory(file, config);
			} else {
				loadFile(file, config);
			}
			config.doneLoading();
		} catch (Exception e) {
			throw new ConfigIOException(file, e);
		}
	}

	public static void load(String path, Config config) {
		load(new File(path), config);
	}
	
	private static void loadFile(File file, Config config) throws IOException {
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
	
	private static void loadDirectory(File dir, Config config) throws IOException {
		File defaultFile = new File(dir.getAbsolutePath() + "/" + DEFAULT_FILE_NAME);
		if (defaultFile.exists()) {
			loadFile(defaultFile, config);
		}
		File[] confFiles = dir.listFiles(CONFIG_FILE_FILTER);
		for (File file : confFiles) {
			if (!file.equals(defaultFile)) {
				loadFile(file, config);
			}
		}
	}

	private static FilenameFilter CONFIG_FILE_FILTER = new FilenameFilter() {

		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(".xml");
		}
	};

}
