package oop.controller.rest.bean;

import oop.data.User;
import oop.persistence.HibernateUtil;

public class UserReferenceMapper implements Mapper<UserReferenceBean, User> {

	@Override
	public UserReferenceBean toBean(User value) {
		UserReferenceBean bean = new UserReferenceBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setFullname(value.getFullname());
		return bean;
	}

	@Override
	public User toEntity(UserReferenceBean value) {
		if (value == null) {
			return null;
		}
		return HibernateUtil.load(User.class, value.getId());
	}

	private static UserReferenceMapper DEFAULT_INSTANCE = new UserReferenceMapper();

	public static UserReferenceMapper get() {
		return DEFAULT_INSTANCE;
	}

}
