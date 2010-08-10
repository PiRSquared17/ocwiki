package oop.test.hibernate;

import java.util.Set;

import oop.data.Constraint;
import oop.data.LevelConstraint;
import oop.db.dao.LevelConstraintDAO;
import oop.db.dao.SectionStructureDAO;

import org.junit.Assert;
import org.junit.Test;

public class ConstraintTest extends HibernateTest {

	public ConstraintTest() {
		super("structure.xml");
	}
	
	public void create() {
		LevelConstraintDAO.create(1, 1, 1);
		Set<Constraint> constraints = SectionStructureDAO.fetchById(1)
				.getConstraints();
		Assert.assertEquals(1, constraints.size());
	}
	
	@Test
	public void polymorphism() {
		Set<Constraint> constraints = SectionStructureDAO.fetchById(1)
				.getConstraints();
		Assert.assertEquals(1, constraints.size());
		Assert.assertTrue(constraints.iterator().next() instanceof LevelConstraint);
	}

}
