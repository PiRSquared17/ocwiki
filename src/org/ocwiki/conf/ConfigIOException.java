package org.ocwiki.conf;

import java.io.File;

public class ConfigIOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private File file;
	
	public ConfigIOException(File file, Throwable cause) {
		super(cause);
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	
}
