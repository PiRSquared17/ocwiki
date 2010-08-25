package oop.controller.action.test;

import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Test;
import oop.data.TestVersion;
import oop.db.dao.TestDAO;
import oop.util.TestUtils;
import oop.wml.hcwg.docx.DocXDocument;

import org.docx4j.openpackaging.exceptions.Docx4JException;

public class SaveDocxAction extends AbstractAction {
	public SaveDocxAction() {
		// default constructor
	}

	@Override
	public void performImpl() throws Exception{
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
		} catch (SQLException e) {
			throw e;
		} catch (Docx4JException e) {
			throw new ActionException("Không thể tạo hoặc lưu file này!");
		}
				
		//need to save docx list?
		setRedirect(Config.get().getHomeDir() + "/" + newDocX.getRelativePath());
	}
}
