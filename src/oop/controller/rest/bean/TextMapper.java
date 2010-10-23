package oop.controller.rest.bean;

import oop.data.Text;
import oop.persistence.HibernateUtil;

public class TextMapper implements Mapper<TextBean, Text> {

	@Override
	public TextBean toBean(Text value) {
		TextBean bean = new TextBean();
		bean.setId(value.getId());
		bean.setText(value.getText());
		return bean;
	}

	@Override
	public Text toEntity(TextBean value) {
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
