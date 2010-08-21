package oop.data;

public class LevelConstraint extends Constraint {

	private int level;

	LevelConstraint() {
	}

	public LevelConstraint(int level, int count) {
		super(count);
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
