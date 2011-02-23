package org.ocwiki.controller.action.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;

import org.ocwiki.conf.Config;
import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Test;
import org.ocwiki.data.TestVersion;
import org.ocwiki.db.dao.TestDAO;
import org.ocwiki.util.TestUtils;
import org.ocwiki.wml.hcwg.docx.DocXDocument;

import org.docx4j.openpackaging.exceptions.Docx4JException;

public class SaveDocxAction extends AbstractAction {
	public SaveDocxAction() {
		// default constructor
	}

	@Override
	public void performImpl() throws IOException, ServletException{
		long testId = getParams().getLong("test");
		Test test = TestDAO.fetchById(testId).getArticle();
		
		TestVersion version = TestUtils.naturalOrder(test);
		
		String savePath = getController().getServletContext().getRealPath("/");
		DocXDocument newDocX = new DocXDocument(test, version, savePath);
		try {
			newDocX.create();
		} catch (NullPointerException e) {
			throw e;
		} catch (JAXBException e) {
			throw new ActionException("Quá trình tạo gặp sự cố!");
		} catch (Docx4JException e) {
			throw new ActionException("Không thể tạo hoặc lưu file này!");
		}
				
		//need to save docx list?
		setRedirect(Config.get().getHomeDir() + "/" + newDocX.getRelativePath());
	}
}
