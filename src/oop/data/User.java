package oop.data;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import oop.util.Utils;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

@XmlRootElement
public class User implements Serializable, Entity, HasVersion {

	private static final long serialVersionUID = -8984541011161716639L;

	private long id;
	private int version;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String password;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String email;
	private String group;
	private boolean blocked;
	private Date blockExpiredDate;
	private String warningMessage;
	private Date warningExpiredDate;
	private String avatar;
	private Date registerDate;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String name;
	private NameOrdering nameOrdering = NameOrdering.LAST_FIRST;
	private Preferences preferences = new Preferences();
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
	private String timezone = "+7:00";

	public User() {
	}

	public User(String name, String password, String firstName, String lastName,
			String email, String group, String avatar, String warning,
			boolean blocked, Date registerDate) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.group = group;
		this.warningMessage = warning;
		this.blocked = blocked;
		this.avatar = avatar;
		this.registerDate = registerDate;
	}

	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFullname() {
		switch (nameOrdering) {
		case FIRST_LAST:
			return Utils.join(" ", firstName, lastName);
		case LAST_MIDDLE_FIRST:
			return Utils.join(" ", lastName, middleName, firstName);
		}
		return Utils.join(" ", lastName, firstName);
	}

	public void setPassword(String password) {
		this.password = password;
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
		if (blockExpiredDate != null && new Date().after(blockExpiredDate)) {
			setBlocked(false);
			setBlockExpiredDate(null);
		}
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getWarningMessage() {
		if (warningExpiredDate != null && new Date().after(warningExpiredDate)) {
			setWarningMessage(null);
			setWarningExpiredDate(null);
		}
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@XmlElement
	public Date getRegisterDate() {
		return registerDate;
	}
	
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	@XmlTransient
	public Preferences getPreferences() {
		return preferences;
	}

	public void setWarningExpiredDate(Date warningExpiredDate) {
		this.warningExpiredDate = warningExpiredDate;
	}

	@XmlElement
	public Date getWarningExpiredDate() {
		return warningExpiredDate;
	}

	public void setBlockExpiredDate(Date blockExpiredDate) {
		this.blockExpiredDate = blockExpiredDate;
	}

	@XmlElement
	public Date getBlockExpiredDate() {
		return blockExpiredDate;
	}

	/* (non-Javadoc)
	 * @see oop.data.Versionable#getVersion()
	 */
	public int getVersion() {
		return version;
	}
	
	@XmlElement
	public void setVersion(int version) {
		this.version = version;
	}

	public boolean matchPassword(String password) {
		return this.password.equals(password);
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

	public void setNameOrdering(NameOrdering nameOrdering) {
		this.nameOrdering = nameOrdering;
	}

	public NameOrdering getNameOrdering() {
		return nameOrdering;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMiddleName() {
		return middleName;
	}
	
}
