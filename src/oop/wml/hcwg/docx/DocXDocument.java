package oop.wml.hcwg.docx;

import java.io.File;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.xml.bind.JAXBException;

import oop.conf.Config;
import oop.data.Answer;
import oop.data.Article;
import oop.data.Question;
import oop.data.Section;
import oop.data.Test;
import oop.data.TestVersion;
import oop.taglib.UtilFunctions;
import oop.wml.hcwg.WMLStyle;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class DocXDocument {

	private Test test;
	private TestVersion version;

	private String savePath;
	private String fileDir;
	private String fileName;

	public DocXDocument() {
		test = null;
		version = null;

		savePath = null;
		fileDir = null;
		fileName = null;
	}

	public DocXDocument(Test test, TestVersion version, String savePath) {
		this.test = test;
		this.version = version;

		this.savePath = savePath;
		fileDir = "SavedDocx";
		fileName = generateFileName();
	}

	public Article getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public TestVersion getVersion() {
		return version;
	}

	public void setVersion(TestVersion version) {
		this.version = version;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFileDir() {
		return fileDir;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void makeDirectory(String directory) {
		File dir = new File(directory);
		if (!dir.exists()) {
			if (!dir.mkdir()) {
				dir.mkdirs();
			}
		}
	}

	public String generateFileName() {
		String verCode;
		if (version.getCode() == null)
			verCode = "";
		else
			verCode = "-" + version.getCode();
		return Config.get().getSiteName() + test.getId() + verCode + ".docx";
	}

	public String getFullPath() {
		return savePath + fileDir + "\\" + fileName;
	}

	public String getRelativePath() {
		return fileDir + "/" + fileName;
	}

	public void create() throws JAXBException, SQLException, Docx4JException,
			NullPointerException {
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
				.createPackage();

		int sectionNumber = 0;
		int questionNumber = 0;

		// Para tieu de
		wordMLPackage.getMainDocumentPart().addParagraph(
				WMLStyle.title(test.getName()));
		LinkedList<String> paragraphs = WMLStyle.description(test
				.getContent().getText());
		for (String paragraph : paragraphs) {
			wordMLPackage.getMainDocumentPart().addParagraph(paragraph);
		}
		paragraphs = WMLStyle.description("Thời gian: " + test.getTime());
		for (String paragraph : paragraphs) {
			wordMLPackage.getMainDocumentPart().addParagraph(paragraph);
		}

		if (version.getCode() != null) {
			paragraphs = WMLStyle.description("Mã đề: " + version.getCode());
			for (String paragraph : paragraphs) {
				wordMLPackage.getMainDocumentPart().addParagraph(paragraph);
			}
		}

		for (Section section : test.getSections()) {
			sectionNumber++;
			wordMLPackage.getMainDocumentPart().addParagraph(
					WMLStyle.PARAGRAPH + WMLStyle.END_PARAGRAPH);
			paragraphs = WMLStyle.section(section.getContent().getText(), 
					UtilFunctions.toAlpha(sectionNumber));
			for (String paragraph : paragraphs) {
				wordMLPackage.getMainDocumentPart().addParagraph(paragraph);
			}

			for (Question question : version.getQuestions().get(section)) {
				paragraphs = WMLStyle.question(question.getContent().getText(),
						questionNumber);
				for (String paragraph : paragraphs) {
					wordMLPackage.getMainDocumentPart().addParagraph(paragraph);
				}

				int answerNumber = 0;
				for (Answer answer : version.getAnswers().get(question)) {
					answerNumber++;
					paragraphs = WMLStyle.answer(answer.getContent().getText(), 
							UtilFunctions.toAlpha(answerNumber));
					for (String paragraph : paragraphs) {
						wordMLPackage.getMainDocumentPart().addParagraph(
								paragraph);
					}
				}
			}
		}
		wordMLPackage.getMainDocumentPart().addParagraph(WMLStyle.end());

		makeDirectory(savePath + "\\" + fileDir);
		wordMLPackage.save(new java.io.File(getFullPath()));
	}
}
