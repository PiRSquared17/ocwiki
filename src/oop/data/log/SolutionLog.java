package oop.data.log;

import oop.data.Resource;
import oop.data.Solution;

public class SolutionLog extends ResourceLog {

	private Resource<Solution> solution;

	public SolutionLog() {
	}
	
	public Resource<Solution> getSolution() {
		return solution;
	}
	
}
