package oop.controller.action.user;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.Date;

import oop.util.TimeZone;

import oop.controller.action.AbstractAction;
import oop.data.Gender;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.persistence.HibernateUtil;
import oop.util.UserUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.exception.ConstraintViolationException;

import com.oreilly.servlet.ParameterNotFoundException;

public class ProfileEditAction extends AbstractAction {

	private User displayedUser;
	private boolean success;
	private TimeZone timezone;
	
	public TimeZone getTimezone() {
		return timezone;
	}

	public boolean isSuccess() {
		return success;
	}

	public User getDisplayedUser() {
		return displayedUser;
	}
	
	

	@Override
	public void performImpl() throws Exception {
		displayedUser = getUser();
		timezone = new TimeZone(displayedUser.getTimezone());
		title("Thay đổi thông tin cá nhân của " + displayedUser.getName());
		if ("Change".equals(getParams().get("change"))) {
			
			String name=getParams().get("name-edit-input");
			String pass=getParams().get("pass-edit-old");
			String newPass=getParams().get("pass-edit-input");
			String rePass=getParams().get("pass-edit-confirm");
			String fullName=getParams().get("fullname-edit-input");
			String firstName=getParams().get("firstname-edit-input");
			String lastName=getParams().get("lastname-edit-input");
			String gender=getParams().get("gender-edit-input");
			Date date = new Date(	Integer.parseInt(getParams().get("birthday-edit-input-year")),
									Integer.parseInt(getParams().get("birthday-edit-input-month")),
									Integer.parseInt(getParams().get("birthday-edit-input-day")));
			Date birthday=date;
			String about=getParams().get("about-edit-input");
			String hometown=getParams().get("hometown-edit-input");
			String location=getParams().get("location-edit-input");
			String bio=getParams().get("bio-edit-input");
			String email=getParams().get("email-edit-input");
			String website=getParams().get("website-edit-input");
			String timezone=getParams().get("timezone-edit-input");

			if (!checkEmail(email)) addError("Email", "Không hợp lệ");
			if (!checkPass(pass,newPass,rePass)) addError("Mật khẩu", "Mật khẩu cũ không đúng hoặc xác nhận mật khẩu mới không khớp");
			
			if (!hasErrors()){
				if (!isEmpty(name)) displayedUser.setName(name);
				if (!isEmpty(newPass)) displayedUser.setPassword(newPass);
				displayedUser.setFirstName(firstName);
				displayedUser.setLastName(lastName);
				//tenho - hoten
				displayedUser.setFullname(fullName);
				
				if (gender=="male") displayedUser.setGender(Gender.MALE);
				else if (gender=="female") displayedUser.setGender(Gender.FEMALE);
				else displayedUser.setGender(Gender.UNKNOWN);
				
				displayedUser.setBirthday(birthday);
				displayedUser.setAbout(about);
				displayedUser.setHometown(hometown);
				displayedUser.setLocation(location);
				displayedUser.setBio(bio);
				displayedUser.setEmail(email);
				displayedUser.setWebsite(website);
				displayedUser.setTimezone(timezone);
				
				try{
					UserDAO.persist(displayedUser);
				}catch (Exception ex) {
					displayedUser=getUser();
				}
			}
			
		}

	}

	private boolean checkEmail(String email){
		email = StringEscapeUtils.escapeSql(email);
		if (isEmpty(email)){
			return false;
		}
		if (!UserUtils.isValidEmail(email)) {
			return false;
		}
		return true;
	}

	private boolean checkPass(String pass,String newPass,String rePass){
		if (isEmpty(pass) || isEmpty(newPass) || isEmpty(rePass)) {
			return false;
		} else if (!getUser().matchPassword(pass))
			return false;
		else if (!pass.equals(rePass))
			return false;
		return true;
	}

}
