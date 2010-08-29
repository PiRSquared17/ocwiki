package oop.data;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class User implements Serializable, Entity, HasVersion {

	private static final long serialVersionUID = -8984541011161716639L;

	@XmlElement
	private long id;
	@XmlElement
	private int version;
	private String fullname;
	private String password;
	private String email;
	private String group;
	private boolean blocked;
	private Date blockExpiredDate;
	private String warningMessage;
	private Date warningExpiredDate;
	private String avatar;
	@XmlElement
	private Date registerDate;
	@XmlElement
	private String name;
	private Preferences preferences = new Preferences();

	public User(String name, String fullname, String password,
			String email, String group, String avatar, String warning,
			boolean blocked, Date registerDate) {
		super();
		this.name = name;
		this.fullname = fullname;
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

	public long getId() {
		return id;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@XmlTransient
	public String getPassword() {
		return password;
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
		if (blockExpiredDate != null && new Date().before(blockExpiredDate)) {
			setBlocked(false);
			setBlockExpiredDate(null);
		}
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getWarningMessage() {
		if (warningExpiredDate != null && new Date().before(warningExpiredDate)) {
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

	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * For hibernate
	 */
	User() {
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
	
}
