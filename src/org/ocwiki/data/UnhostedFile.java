package org.ocwiki.data;

import java.util.HashSet;
import java.util.Set;

public class UnhostedFile extends CategorizableArticle {

	private Text content;
	private Set<Link> links = new HashSet<Link>();
	
	public UnhostedFile() {
	}

	@Override
	public Article copy() {
		UnhostedFile file = new UnhostedFile();
		copyTo(file);
		file.content = content;
		file.links = new HashSet<Link>(links);
		return file;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
		this.content = content;
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}

}
