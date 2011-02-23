package org.ocwiki.controller.rest.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.ocwiki.data.Gender;
import org.ocwiki.data.NameOrdering;
import org.ocwiki.data.Preferences;

@XmlRootElement
public class UserBean {

	private long id;
	private int version;
	private String name;
	private String email;
	private String group;
	private boolean blocked;
	private Date blockExpiredDate;
	private String warningMessage;
	private Date warningExpiredDate;
	private String avatar;
	private Date registerDate;
	private Preferences preferences = new Preferences();
	private NameOrdering nameOrdering = NameOrdering.LAST_FIRST;
	private String fullname;
	private String firstName;
	private String middleName;
	private String lastName;
	private String about;
	private Date birthday;
	private String website;
	private String hometown;
	private String location;
	private String bio;
	private Gender gender = Gender.UNKNOWN;
	private String timezone;
	private String draft;

	public UserBean() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Date getBlockExpiredDate() {
		return blockExpiredDate;
	}

	public void setBlockExpiredDate(Date blockExpiredDate) {
		this.blockExpiredDate = blockExpiredDate;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public Date getWarningExpiredDate() {
		return warningExpiredDate;
	}

	public void setWarningExpiredDate(Date warningExpiredDate) {
		this.warningExpiredDate = warningExpiredDate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

	public NameOrdering getNameOrdering() {
		return nameOrdering;
	}

	public void setNameOrdering(NameOrdering nameOrdering) {
		this.nameOrdering = nameOrdering;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getDraft() {
		return draft;
	}

	public void setDraft(String draft) {
		this.draft = draft;
	}

}
