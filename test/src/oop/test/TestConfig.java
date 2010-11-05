package oop.test;

import oop.conf.Config;
import oop.controller.OcwikiApp;

public class TestConfig {

	static {
		OcwikiApp.initialize("test/conf");
	}
	
	public static Config getConfig() {
		return Config.get();
	}
	
}
