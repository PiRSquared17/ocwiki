package oop.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TextArticle extends BaseArticle {

	public TextArticle() {
	}

	@Override
	public TextArticle copy() {
		return copyTo(new TextArticle());
	}

}
