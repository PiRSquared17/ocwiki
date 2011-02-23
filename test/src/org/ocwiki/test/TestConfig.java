package org.ocwiki.test;

import org.ocwiki.conf.Config;
import org.ocwiki.controller.OcwikiApp;

public class TestConfig {

	static {
		OcwikiApp.initialize("test/conf");
	}
	
	public static Config getConfig() {
		return Config.get();
	}
	
}
