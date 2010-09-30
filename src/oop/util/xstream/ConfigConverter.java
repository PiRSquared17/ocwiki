package oop.util.xstream;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import oop.conf.Config;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.javabean.BeanProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

@SuppressWarnings("unchecked")
public class ConfigConverter implements Converter {

	/*
	 * TODO: - support indexed properties
	 */
	private Mapper mapper;
	private BeanProvider beanProvider;

	public ConfigConverter(Mapper mapper) {
		this(mapper, new BeanProvider());
	}

	public ConfigConverter(Mapper mapper, BeanProvider beanProvider) {
		this.mapper = mapper;
		this.beanProvider = beanProvider;
	}

	/**
	 * Only checks for the availability of a public default constructor. If you
	 * need stricter checks, subclass JavaBeanConverter
	 */
	public boolean canConvert(Class type) {
		return type == Config.class;
	}

	public void marshal(final Object source,
			final HierarchicalStreamWriter writer,
			final MarshallingContext context) {
		throw new UnsupportedOperationException("No need to write out config.");
	}

	public Object unmarshal(final HierarchicalStreamReader reader,
			final UnmarshallingContext context) {
		final Object result = instantiateNewInstance(context);
		Map<String, Collection> implicitCollections = new HashMap<String, Collection>();

		while (reader.hasMoreChildren()) {
			reader.moveDown();

			String nodeName = reader.getNodeName();
			String propertyName = mapper
					.realMember(result.getClass(), nodeName);

			boolean propertyExistsInClass = beanProvider
					.propertyDefinedInClass(propertyName, result.getClass());

			if (propertyExistsInClass) {
				Class type = determineType(reader, result, propertyName);
				Object value = context.convertAnother(result, type);
				beanProvider.writeProperty(result, propertyName, value);
			} else {
				Class itemType = mapper.getItemTypeForItemFieldName(result
						.getClass(), propertyName);
				if (itemType == null) {
					itemType = mapper.realClass(nodeName);
				}
				
				if (itemType != null) {
					Object itemValue = context.convertAnother(result, itemType);
					String collectionName = mapper
							.getFieldNameForItemTypeAndName(context
									.getRequiredType(),
									itemValue != null ? itemValue.getClass()
											: Mapper.Null.class, nodeName);
					Class collectionType = beanProvider.getPropertyType(result,
							collectionName);
					if (collectionType == Set.class) {
						if (!implicitCollections.containsKey(collectionName)) {
							implicitCollections.put(collectionName,
									new HashSet());
						}
						implicitCollections.get(collectionName).add(itemValue);
					} else {
						throw new UnsupportedOperationException(
								"Support implicit collection for java.util.Set only");
					}
				} else if (mapper.shouldSerializeMember(result.getClass(),
						propertyName)) {
					throw new ConversionException("Property '" + propertyName
							+ "' not defined in class "
							+ result.getClass().getName());
				}
			}

			for (Entry<String, Collection> entry : implicitCollections
					.entrySet()) {
				beanProvider.writeProperty(result, entry.getKey(), entry
						.getValue());
			}

			reader.moveUp();
		}

		return result;
	}

	private Object instantiateNewInstance(UnmarshallingContext context) {
		Object result = context.currentObject();
		if (result == null) {
			result = beanProvider.newInstance(context.getRequiredType());
		}
		return result;
	}

	private Class determineType(HierarchicalStreamReader reader, Object result,
			String fieldName) {
		final String classAttributeName = mapper
				.aliasForSystemAttribute("class");
		String classAttribute = classAttributeName == null ? null : reader
				.getAttribute(classAttributeName);
		if (classAttribute != null) {
			return mapper.realClass(classAttribute);
		} else {
			return mapper.defaultImplementationOf(beanProvider.getPropertyType(
					result, fieldName));
		}
	}

}
