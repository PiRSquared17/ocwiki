package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.User;

public class UserMapper implements Mapper<UserBean, User> {

	private static UserMapper DEFAULT_INSTANCE = new UserMapper();

	public static UserMapper get() {
		return DEFAULT_INSTANCE;
	}

	@Override
	public UserBean toBean(User value) {
		UserBean bean = new UserBean();
		bean.setId(value.getId());
		bean.setVersion(value.getVersion());
		bean.setEmail(value.getEmail());
		bean.setGroup(value.getGroup());
		bean.setBlocked(value.isBlocked());
		bean.setWarningMessage(value.getWarningMessage());
		bean.setAvatar(value.getAvatar());
		bean.setRegisterDate(value.getRegisterDate());
		bean.setName(value.getName());
		bean.setNameOrdering(value.getNameOrdering());
		bean.setPreferences(value.getPreferences());
		bean.setFirstName(value.getFirstName());
		bean.setLastName(value.getLastName());
		bean.setMiddleName(value.getMiddleName());
		bean.setAbout(value.getAbout());
		bean.setBirthday(value.getBirthday());
		bean.setWebsite(value.getWebsite());
		bean.setHometown(value.getHometown());
		bean.setLocation(value.getLocation());
		bean.setBio(value.getBio());
		bean.setGender(value.getGender());
		bean.setTimezone(value.getTimezone());
		bean.setDraft(value.getDraft());
		return bean;
	}

	@Override
	public User toEntity(UserBean value) {
		User entity = new User();
		entity.setId(value.getId());
		entity.setVersion(value.getVersion());
		entity.setEmail(value.getEmail());
		entity.setGroup(value.getGroup());
		entity.setBlocked(value.isBlocked());
		entity.setWarningMessage(value.getWarningMessage());
		entity.setAvatar(value.getAvatar());
		entity.setRegisterDate(value.getRegisterDate());
		entity.setName(value.getName());
		entity.setNameOrdering(value.getNameOrdering());
		entity.setPreferences(value.getPreferences());
		entity.setFirstName(value.getFirstName());
		entity.setLastName(value.getLastName());
		entity.setMiddleName(value.getMiddleName());
		entity.setAbout(value.getAbout());
		entity.setBirthday(value.getBirthday());
		entity.setWebsite(value.getWebsite());
		entity.setHometown(value.getHometown());
		entity.setLocation(value.getLocation());
		entity.setBio(value.getBio());
		entity.setGender(value.getGender());
		entity.setTimezone(value.getTimezone());
		entity.setDraft(value.getDraft());
		return entity;
	}
	
}
