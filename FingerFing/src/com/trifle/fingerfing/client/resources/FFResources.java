package com.trifle.fingerfing.client.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface FFResources extends ClientBundle {

	public static final FFResources INST = GWT.create(FFResources.class);
	
	@Source("WorkingSets1.json")
	public TextResource getWorkingSets();

	@Source("AlternativeKeyLayoutRU.json")
	public TextResource getAlternativeKeyLayoutRU();

}
