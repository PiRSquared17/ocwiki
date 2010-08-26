package oop.test;

import oop.conf.Config;
import oop.conf.ConfigIO;

public class TestConfig {

	static {
		Config config = new Config();
		ConfigIO.loadDirectory(config, "test/conf");
		Config.setDefaultInstance(config);
	}
	
	public static Config getConfig() {
		return Config.get();
	}
	
}
