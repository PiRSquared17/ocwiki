package org.ocwiki.data.log;

import org.ocwiki.data.Resource;
import org.ocwiki.data.Solution;

public class SolutionLog extends ResourceLog {

	private Resource<Solution> solution;

	public SolutionLog() {
	}
	
	public Resource<Solution> getSolution() {
		return solution;
	}
	
}
