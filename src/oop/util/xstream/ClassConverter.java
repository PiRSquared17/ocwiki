package oop.util.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@SuppressWarnings("unchecked")
public class ClassConverter implements Converter {

	@Override
	public void marshal(Object obj, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		Class clazz = (Class) obj;
		writer.setValue(clazz.getName());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		try {
			return Class.forName(reader.getValue());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean canConvert(Class clazz) {
		return clazz == Class.class;
	}

}
