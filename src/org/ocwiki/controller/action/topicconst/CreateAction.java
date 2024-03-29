package org.ocwiki.controller.action.topicconst;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.data.Resource;
import org.ocwiki.data.SectionStructure;
import org.ocwiki.data.TestStructure;
import org.ocwiki.data.Text;
import org.ocwiki.data.Topic;
import org.ocwiki.data.TopicConstraint;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.TestStructureDAO;
import org.ocwiki.util.Utils;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractResourceAction<TestStructure> {

	public CreateAction() {
	}

	@Override
	public void performImpl() throws IOException, ServletException {
		long testStructureId = getParams().getLong("teststruct");
		resource = TestStructureDAO.fetchById(testStructureId);
		testStructure = resource.getArticle();

		String submit = getParams().get("submit");
		if ("add".equals(submit)) {
			doCreate(testStructure);
		}
	}

	private void doCreate(TestStructure testStructure) {
		testStructure = testStructure.copy();

		SectionStructure sectionStructure = null;
		if (getParams().hasParameter("section")) {
			try {
				sectionStructure = Utils.replaceByCopy(testStructure
						.getSectionStructures(), getParams().getInt("section"));
			} catch (NumberFormatException ex) {
				addError("sectionErr", "Mã số mục không hợp lệ.");
			}
		} else {
			List<SectionStructure> sectionStructures = testStructure
					.getSectionStructures();
			if (sectionStructures.isEmpty()) {
				sectionStructure = new SectionStructure(new Text(""));
				testStructure.getSectionStructures().add(sectionStructure);
			} else {
				addError("section", "Bạn cần chọn một mục.");
			}
		}

		TopicConstraint constraint = new TopicConstraint();
		try {
			int quantity = getParams().getInt("quantity");
			if (quantity > 0) {
				constraint.setCount(quantity);
			} else {
				addError("quantity", "Số lượng phải nguyên dương.");
			}
		} catch (ParameterNotFoundException ex) {
			addError("quantity", "Bạn cần nhập số lượng.");
		} catch (NumberFormatException ex) {
			addError("quantity", "Số lượng không hợp lệ.");
		}

		try {		
			Set<Resource<Topic>> topics = new HashSet<Resource<Topic>>();
			for (int i = 0; i < getParams().count("topics"); i++) {
				Resource<Topic> topic = ResourceDAO.fetchById(getParams()
						.getIndexedLong("topics", i));
				topics.add(topic);
			}
			constraint.setTopics(topics);
		} catch (ParameterNotFoundException ex) {
			addError("topic", "Bạn cần chọn chủ đề.");
		} catch (NumberFormatException ex) {
			addError("topic", "Mã số chủ đề không hợp lệ.");
		}

		if (hasNoErrors()) {
			sectionStructure.getConstraints().add(constraint);
			saveNewRevision(resource, testStructure);
			setNextAction("teststruct.view&tstr=" + resource.getId());
		}
	}

	private TestStructure testStructure;

	public TestStructure getTestStructure() {
		return testStructure;
	}
	
}
