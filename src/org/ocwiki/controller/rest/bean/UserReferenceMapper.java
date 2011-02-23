package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.User;
import org.ocwiki.persistence.HibernateUtil;

public class UserReferenceMapper implements Mapper<UserReferenceBean, User> {

	@Override
	public UserReferenceBean toBean(User value) {
		UserReferenceBean bean = new UserReferenceBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setFullname(value.getFullname());
		bean.setAvatar(value.getAvatar());
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
