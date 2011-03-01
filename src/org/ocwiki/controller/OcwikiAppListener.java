package org.ocwiki.controller;

import java.util.EventObject;

public interface OcwikiAppListener {

	public void appInitialized(EventObject evt);
	
	public void appDestroying(EventObject evt);
	
}
