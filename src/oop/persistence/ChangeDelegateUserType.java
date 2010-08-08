package oop.persistence;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oop.change.ChangeDelegate;
import oop.util.XMLUtils;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

@SuppressWarnings("unchecked")
public class ChangeDelegateUserType implements UserType {

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Class returnedClass() {
		return ChangeDelegate.class;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		String xml = rs.getString(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		return XMLUtils.getXStream().fromXML(xml);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(0, Hibernate.TEXT.sqlType());
		} else {
			st.setString(index, XMLUtils.getXStream().toXML(value));
		}
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Hibernate.TEXT.sqlType() };
	}

}
