package oop.test;

import java.io.IOException;

import oop.conf.Config;
import oop.conf.ConfigIO;

public class TestConfig {

	static {
		try {
			Config config = new Config();
			ConfigIO.loadDirectory(config, "test/conf");
			Config.setDefaultInstance(config);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static Config getConfig() {
		return Config.get();
	}
	
}
