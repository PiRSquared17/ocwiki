package oop.controller.rest.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import oop.data.ContentLicense;

@XmlRootElement
public class FileBean extends CategorizableArticleBean {

	private String filename;
	private String author  = "";;
	private ContentLicense license = ContentLicense.UNKNOWN;;
	private String originalSource = "";
	private Date dateOfWork = new Date();
	private String additionalInfo = "";

	public FileBean() {
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ContentLicense getLicense() {
		return license;
	}

	public void setLicense(ContentLicense license) {
		this.license = license;
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
	
}
