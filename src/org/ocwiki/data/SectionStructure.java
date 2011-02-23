package org.ocwiki.data;

import java.util.ArrayList;
import java.util.List;

import org.ocwiki.util.Copiable;

public class SectionStructure implements Entity, Copiable<SectionStructure> {

	private long id;
	private Text content;
	private List<Constraint> constraints = new ArrayList<Constraint>();
	
	SectionStructure() {
	}

	public SectionStructure(Text content) {
		super();
		this.content = content;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<Constraint> constraints) {
		this.constraints = constraints;
	}

	@Override
	public SectionStructure copy() {
		SectionStructure structure = new SectionStructure();
		structure.setContent(getContent());
		structure.setConstraints(new ArrayList<Constraint>(getConstraints()));
		return structure;
	}

}
