package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.Text;
import org.ocwiki.persistence.HibernateUtil;

public class TextMapper implements Mapper<TextBean, Text> {

	@Override
	public TextBean toBean(Text value) {
		if (value == null) {
			return null;
		}
		TextBean bean = new TextBean();
		bean.setId(value.getId());
		bean.setText(value.getText());
		return bean;
	}

	@Override
	public Text toEntity(TextBean value) {
		if (value == null) {
			return null;
		}
		if (value.getId() == 0) {
			return new Text(value.getText());
		}
		return (Text) HibernateUtil.getSession()
				.load(Text.class, value.getId());
	}
	
	private static TextMapper DEFAULT_INSTANCE = new TextMapper();

	public static TextMapper get() {
		return DEFAULT_INSTANCE;
	}

}
