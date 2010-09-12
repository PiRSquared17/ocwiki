package oop.persistence;

import oop.conf.Config;

import org.hibernate.cfg.DefaultNamingStrategy;

public class PrefixNamingStrategy extends DefaultNamingStrategy {

	private static final long serialVersionUID = 6326369394886626637L;
	private Config config;

	public PrefixNamingStrategy() {
		this(null);
	}
	
	public PrefixNamingStrategy(Config config) {
		super();
		this.config = config;
	}

	@Override
	public String tableName(String tableName) {
		if (config == null) {
			return "ocw" + tableName;
		}
		return config.getTablePrefix()
				+ Character.toUpperCase(tableName.charAt(0))
				+ tableName.substring(1);
	}
	
}
