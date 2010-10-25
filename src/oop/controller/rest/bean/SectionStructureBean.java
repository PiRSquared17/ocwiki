package oop.controller.rest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import oop.data.Text;

@XmlRootElement
public class SectionStructureBean {

	private long id;
	private Text content;
	private List<ConstraintBean> constraints = new ArrayList<ConstraintBean>();

	public SectionStructureBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
		this.content = content;
	}

	public List<ConstraintBean> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<ConstraintBean> constraints) {
		this.constraints = constraints;
	}

}
