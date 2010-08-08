package oop.util.xstream;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import oop.change.Change;
import oop.data.Entity;
import oop.persistence.HibernateUtil;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@SuppressWarnings("unchecked")
public class EntityConverter implements Converter {

	@Override
	public void marshal(Object obj, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		if (obj == null) {
			writer.setValue("");
		} else {
			Entity clazz = (Entity) obj;
			writer.setValue(Long.toString(clazz.getId()));
		}
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		if (StringUtils.isEmpty(reader.getValue())) {
			return null;
		} else {
			Long id = Long.parseLong(reader.getValue());
			Class clazz = context.getRequiredType();
			Session session = HibernateUtil.getSession();
			return session.load(clazz, id);
		}
	}

	@Override
	public boolean canConvert(Class clazz) {
		return Entity.class.isAssignableFrom(clazz) && clazz != Change.class;
	}

}
