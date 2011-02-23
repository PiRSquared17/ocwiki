package org.ocwiki.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TextArticle extends BaseArticle {

	public TextArticle() {
	}

	public TextArticle(String name, Namespace namespace, Text content) {
		setName(name);
		setNamespace(namespace);
		setContent(content);
	}
	
	@Override
	public TextArticle copy() {
		return copyTo(new TextArticle());
	}

}
