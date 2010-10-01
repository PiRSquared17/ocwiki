package oop.data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class File extends CategorizableArticle {

	private String filename;

	public File() {
	}
	
	@XmlTransient
	public String getFileName() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	protected <T> T copyTo(T obj) {
		File media = (File) obj;
		media.setFilename(getFileName());
		return super.copyTo(obj);
	};

	@Override
	public Article copy() {
		return copyTo(new File());
	}

}
