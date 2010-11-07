package oop.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;


@XmlRootElement
public class File extends CategorizableArticle {

	@Field(index=Index.UN_TOKENIZED, store=Store.NO)
	private String filename = "";
	private String author = "";
	private ContentLicense license = ContentLicense.UNKNOWN;
	private String originalSource = "";
	private Date dateOfWork = new Date();
	private String additionalInfo = "";

	public File() {
	}

	@XmlTransient
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getOriginalSource() {
		return originalSource;
	}

	public void setOriginalSource(String originalSource) {
		this.originalSource = originalSource;
	}

	public Date getDateOfWork() {
		return dateOfWork;
	}

	public void setDateOfWork(Date dateOfWork) {
		this.dateOfWork = dateOfWork;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	protected <T> T copyTo(T obj) {
		File media = (File) obj;
		media.setFilename(getFilename());
		return super.copyTo(obj);
	};

	@Override
	public File copy() {
		return copyTo(new File());
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setLicense(ContentLicense license) {
		this.license = license;
	}

	public ContentLicense getLicense() {
		return license;
	}

	public boolean isImage() {
		if (StringUtils.isEmpty(filename)) {
			return false;
		}
		String str = filename.toLowerCase();
		return (str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".gif"));
	}
}
