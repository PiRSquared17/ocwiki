package oop.wml.hcwg;

import java.util.LinkedList;

import org.apache.commons.lang.StringEscapeUtils;

public class WMLStyle {

	public static final String PARAGRAPH = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >";
	public static final String END_PARAGRAPH = "</w:p>";

	public static final String TEXTRUN = "<w:r>";
	public static final String END_TEXTRUN = "</w:r>";

	public static final String TEXT = "<w:t>";
	public static final String END_TEXT = "</w:t>";

	public static final String STYLES = "<w:rPr>";
	public static final String END_STYLES = "</w:rPr>";

	public static final String JUSTIFY = "<w:pPr>";
	public static final String END_JUSTIFY = "</w:pPr>";

	public static final String BOLD = "<w:b />";

	public static final String ITALIC = "<w:i />";

	public static final String UNDERLINE = "<w:u w:val=\"single\"/>";

	public static final String CENTER = "<w:jc w:val=\"center\"/>";

	public static final String LEFT = "<w:jc w:val=\"left\"/>";

	public static final String RIGHT = "<w:jc w:val=\"right\"/>";

	public static final String BOTH = "<w:jc w:val=\"both\"/>";

	public static final String TIMES_FONT = "Times New Roman";

	public static final int DEFAULT_PADDING = 720;

	public static final int DEFAULT_SIZE = 12;

	public static final String TAB = "<w:tab/>";

	public static String size(int size) {
		String temp = "<w:sz w:val=\"";
		temp += (size * 2);
		temp += "\"/>";
		return temp;
	}

	public static String font(String font) {
		String temp = "<w:rFonts w:ascii=\"";
		temp += font;
		temp += "\" w:hAnsi=\"";
		temp += font;
		temp += "\" w:cs=\"";
		temp += font;
		temp += "\"/>";
		return temp;
	}

	public static String padding(int paddingSize, int firstLinePadding) {
		String temp = "<w:ind w:left=\"";
		temp += paddingSize;
		temp += "\" w:firstLine=\"";
		temp += firstLinePadding;
		temp += "\"/>";
		return temp;
	}

	public static String lineSpacing(int before, int after) {
		String temp = "<w:spacing w:before=\"";
		temp += before;
		temp += "\" w:after=\"";
		temp += after;
		temp += "\" w:line=\"240\" w:lineRule=\"auto\"/>";
		return temp;
	}

	public static String[] htmlParagraphSplitter(String strIn) {
		String tagExp = "<p\\s*.*?>|<br\\s*/?>";
		String[] paragraphs = strIn.split(tagExp);
		LinkedList<String> result = new LinkedList<String>();
		for (int i = 0; i < paragraphs.length; i++) {
			String temp = removeHTMLFormat(paragraphs[i]);
			if (temp.isEmpty() == false) {// ((temp !=
				// null)&&("".compareTo(temp) !=
				// 0)&&(temp.length() != 0)){
				if (temp.trim().isEmpty() == false) {
					result.addLast(temp);
				}
			}
		}
		if (result.size() == 0) {
			result.addLast("(Chưa có nội dung)");
		}
		return result.toArray(new String[result.size()]);
	}

	public static String removeHTMLFormat(String strIn) {
		String tagExp = "<.+?>";
		strIn = StringEscapeUtils.unescapeHtml(strIn);
		return strIn.replaceAll(tagExp, "");
	}

	public static String title(String title) {
		String temp = "";
		temp += WMLStyle.PARAGRAPH;
		temp += WMLStyle.JUSTIFY;
		temp += WMLStyle.lineSpacing(180, 180);
		temp += WMLStyle.CENTER;
		temp += WMLStyle.END_JUSTIFY;
		temp += WMLStyle.TEXTRUN;
		temp += WMLStyle.STYLES;
		temp += WMLStyle.BOLD;
		temp += WMLStyle.size(20);
		temp += WMLStyle.font(WMLStyle.TIMES_FONT);
		temp += WMLStyle.END_STYLES;
		temp += WMLStyle.TEXT;
		temp += removeHTMLFormat(title);
		temp += WMLStyle.END_TEXT;
		temp += WMLStyle.END_TEXTRUN;
		temp += WMLStyle.END_PARAGRAPH;
		return temp;
	}

	public static LinkedList<String> description(String description) {
		String[] desParagraphs = htmlParagraphSplitter(description);
		LinkedList<String> resultParagraphs = new LinkedList<String>();

		for (int i = 0; i < desParagraphs.length; i++) {
			int before;
			int after;
			if (i == 0) {
				before = 240;
				after = 120;
			} else if (i == desParagraphs.length - 1) {
				before = 120;
				after = 180;
			} else {
				before = 120;
				after = 120;
			}
			String temp = "";
			temp += WMLStyle.PARAGRAPH;
			temp += WMLStyle.JUSTIFY;
			temp += WMLStyle.lineSpacing(before, after);
			temp += WMLStyle.BOTH;
			temp += WMLStyle.END_JUSTIFY;
			temp += WMLStyle.TEXTRUN;
			temp += WMLStyle.STYLES;
			temp += WMLStyle.size(WMLStyle.DEFAULT_SIZE);
			temp += WMLStyle.font(WMLStyle.TIMES_FONT);
			temp += WMLStyle.END_STYLES;
			temp += WMLStyle.TEXT;
			temp += desParagraphs[i];
			temp += WMLStyle.END_TEXT;
			temp += WMLStyle.END_TEXTRUN;
			temp += WMLStyle.END_PARAGRAPH;
			resultParagraphs.addLast(temp);
		}
		return resultParagraphs;
	}

	public static String end() {
		String temp = "";
		temp += WMLStyle.PARAGRAPH;
		temp += WMLStyle.JUSTIFY;
		temp += WMLStyle.lineSpacing(180, 180);
		temp += WMLStyle.CENTER;
		temp += WMLStyle.END_JUSTIFY;
		temp += WMLStyle.TEXTRUN;
		temp += WMLStyle.STYLES;
		temp += WMLStyle.size(WMLStyle.DEFAULT_SIZE);
		temp += WMLStyle.font(WMLStyle.TIMES_FONT);
		temp += WMLStyle.END_STYLES;
		temp += WMLStyle.TEXT;
		temp += "___ HẾT ___";
		temp += WMLStyle.END_TEXT;
		temp += WMLStyle.END_TEXTRUN;
		temp += WMLStyle.END_PARAGRAPH;
		return temp;
	}

	public static LinkedList<String> section(String sectionText,
			String sectionNumber) {
		String[] secParagraphs = htmlParagraphSplitter(sectionText);
		LinkedList<String> resultParagraphs = new LinkedList<String>();

		for (int i = 0; i < secParagraphs.length; i++) {
			int before;
			int after;
			if (i == 0) {
				before = 180;
				after = 120;
			} else if (i == secParagraphs.length - 1) {
				before = 120;
				after = 180;
			} else {
				before = 120;
				after = 120;
			}
			String temp = "";
			temp += WMLStyle.PARAGRAPH;
			temp += WMLStyle.JUSTIFY;
			temp += WMLStyle.lineSpacing(before, after);
			temp += WMLStyle.BOTH;
			temp += WMLStyle.END_JUSTIFY;
			temp += WMLStyle.TEXTRUN;
			temp += WMLStyle.STYLES;
			temp += WMLStyle.BOLD;
			temp += WMLStyle.size(WMLStyle.DEFAULT_SIZE);
			temp += WMLStyle.font(WMLStyle.TIMES_FONT);
			temp += WMLStyle.END_STYLES;
			temp += WMLStyle.TEXT;
			if (i == 0)
				temp += (sectionNumber + ". ");
			temp += secParagraphs[i];
			temp += WMLStyle.END_TEXT;
			temp += WMLStyle.END_TEXTRUN;
			temp += WMLStyle.END_PARAGRAPH;
			resultParagraphs.addLast(temp);
		}
		return resultParagraphs;
	}

	public static LinkedList<String> question(String questionContent,
			int questionNumber) {

		String questionName = "";
		questionName += WMLStyle.TEXTRUN;
		questionName += WMLStyle.STYLES;
		questionName += WMLStyle.BOLD;
		questionName += WMLStyle.size(WMLStyle.DEFAULT_SIZE);
		questionName += WMLStyle.font(WMLStyle.TIMES_FONT);
		questionName += WMLStyle.END_STYLES;
		questionName += WMLStyle.TEXT;
		questionName += ("Câu " + questionNumber);
		questionName += WMLStyle.END_TEXT;
		questionName += WMLStyle.END_TEXTRUN;

		String[] quesParagraphs = htmlParagraphSplitter(questionContent);
		LinkedList<String> resultParagraphs = new LinkedList<String>();
		for (int i = 0; i < quesParagraphs.length; i++) {
			int before;
			int after;
			if (i == 0) {
				before = 180;
				after = 120;
			} else {
				before = 120;
				after = 120;
			}
			String temp = "";
			temp += WMLStyle.PARAGRAPH;
			temp += WMLStyle.JUSTIFY;
			temp += WMLStyle.lineSpacing(before, after);
			temp += WMLStyle.BOTH;
			temp += WMLStyle.END_JUSTIFY;
			if (i == 0)
				temp += questionName;
			temp += WMLStyle.TEXTRUN;
			temp += WMLStyle.STYLES;
			temp += WMLStyle.size(WMLStyle.DEFAULT_SIZE);
			temp += WMLStyle.font(WMLStyle.TIMES_FONT);
			temp += WMLStyle.END_STYLES;
			temp += WMLStyle.TEXT;
			if (i == 0)
				temp += ": ";
			temp += quesParagraphs[i];
			temp += WMLStyle.END_TEXT;
			temp += WMLStyle.END_TEXTRUN;
			temp += WMLStyle.END_PARAGRAPH;
			resultParagraphs.addLast(temp);
		}
		return resultParagraphs;
	}

	public static LinkedList<String> answer(String answerContent, String answerNumber) {

		String[] ansParagraphs = htmlParagraphSplitter(answerContent);
		LinkedList<String> resultParagraphs = new LinkedList<String>();
		for (int i = 0; i < ansParagraphs.length; i++) {

			StringBuilder temp = new StringBuilder();
			temp.append(WMLStyle.PARAGRAPH);
			temp.append(WMLStyle.JUSTIFY);
			temp.append(WMLStyle.lineSpacing(120, 120));
			temp.append(WMLStyle.LEFT);
			temp.append(WMLStyle.END_JUSTIFY);
			temp.append(WMLStyle.TEXTRUN);
			temp.append(WMLStyle.STYLES);
			temp.append(WMLStyle.size(WMLStyle.DEFAULT_SIZE));
			temp.append(WMLStyle.font(WMLStyle.TIMES_FONT));
			temp.append(WMLStyle.END_STYLES);
			temp.append(WMLStyle.TEXT);
			temp.append(WMLStyle.TAB);
			if (i == 0)
				temp.append((answerNumber+". "));
			temp.append(ansParagraphs[i]);
			temp.append(WMLStyle.END_TEXT);
			temp.append(WMLStyle.END_TEXTRUN);
			temp.append(WMLStyle.END_PARAGRAPH);
			resultParagraphs.addLast(temp.toString());

		}
		return resultParagraphs;
	}
}
