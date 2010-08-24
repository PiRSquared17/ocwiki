package oop.controller.action;

import oop.data.Group;
import oop.data.Namespace;
import oop.db.dao.NamespaceDAO;
import oop.db.dao.UserDAO;

public class SetupAction extends AbstractAction {

	@Override
	protected void performImpl() throws Exception {
		NamespaceDAO.create(Namespace.MAIN, "Chính");
		NamespaceDAO.create(Namespace.OCWIKI, "OCWIKI");
		NamespaceDAO.create(Namespace.QUESTION, "Câu hỏi");
		NamespaceDAO.create(Namespace.TEST, "Đề thi");
		NamespaceDAO.create(Namespace.TEST_STRUCTURE, "Cấu trúc đề");
		NamespaceDAO.create(Namespace.TOPIC, "Chủ đề");
		NamespaceDAO.create(Namespace.FILE, "Tập tin");
		
		UserDAO.create("admin", "admin", Group.ADMIN, "1234", "admin@ocwiki.org");
		UserDAO.create("teacher", "teacher", Group.TEACHER, "1234", "teacher@ocwiki.org");
	}

}
