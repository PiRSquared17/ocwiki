package oop.controller.action.user;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Gender;
import oop.data.NameOrdering;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.util.SessionUtils;
import oop.util.TimeZone;
import oop.util.UserUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.oreilly.servlet.ParameterNotFoundException;

public class ProfileEditAction extends AbstractAction {

	private User displayedUser;
	private boolean success = false;
	private TimeZone userTimezone;

	public TimeZone getTimezone() {
		return userTimezone;
	}

	public boolean isSuccess() {
		return success;
	}

	public User getDisplayedUser() {
		return displayedUser;
	}


	@Override
	public void performImpl() throws Exception {
		displayedUser = UserDAO.fetchById(getUser().getId());
		userTimezone = new TimeZone(displayedUser.getTimezone());
		
		title("Thay đổi thông tin cá nhân của " + displayedUser.getFullname());
		if ("change".equals(getParams().get("change"))) {
			
			String name=getParams().get("name-edit-input");
			String pass=getParams().get("pass-edit-old");
			String newPass=getParams().get("pass-edit-input");
			String rePass=getParams().get("pass-edit-confirm");
			String fullName=getParams().get("fullname-edit-input");
			String firstName=getParams().get("firstname-edit-input");
			String lastName=getParams().get("lastname-edit-input");
			
			Gender gender=Gender.UNKNOWN;
			try {
				gender = Gender.valueOf(StringUtils.upperCase(getParams().get("gender-edit-input")));
			} catch (IllegalArgumentException ex) {
				addError("gender", "Giới tính không hợp lệ");
			}
			
			Date birthday = new Date();
			try{
				Calendar date = new GregorianCalendar(getParams().getInt("birthday-edit-year"),
													getParams().getInt("birthday-edit-month")-1,
													getParams().getInt("birthday-edit-day"));
				birthday = date.getTime();
			} catch (ParameterNotFoundException ex) {
				addError("birthday", "Bạn cần điền ngày sinh.");
			} catch (NumberFormatException ex) {
				addError("birthday", "Ngày sinh không hợp lệ.");
			} catch (Exception e) {
				throw new ActionException(e.getMessage());
			}
			

			String about=getParams().get("about-edit-input");
			String hometown=getParams().get("hometown-edit-input");
			String location=getParams().get("location-edit-input");
			String bio=getParams().get("bio-edit-input");
			String email=getParams().get("email-edit-input");
			String website=getParams().get("website-edit-input");
			String timezone=getParams().get("timezone-edit-input");

			if (!isEmpty(name)){
				if (!checkUsername(name)) {
					addError("nameError", "Username đã được dùng bởi người khác");
				}
			}
			if (!checkEmail(email)) {
				addError("emailError", "Email không hợp lệ hoặc đã được dùng bởi người khác");
			}
			if (!checkPass(pass,newPass,rePass)) {
				addError("passError", "Mật khẩu cũ không đúng hoặc xác nhận mật khẩu mới không khớp");
			}
			
			if (!hasErrors()){
				if (!isEmpty(name)) displayedUser.setName(name);
				if (!isEmpty(newPass)) displayedUser.setPassword(newPass);
				displayedUser.setFirstName(firstName);
				displayedUser.setLastName(lastName);
				//tenho - hoten
				if (fullName=="firstLast") displayedUser.setNameOrdering(NameOrdering.FIRST_LAST);
				else if (fullName=="lastMiddleFirst") displayedUser.setNameOrdering(NameOrdering.LAST_MIDDLE_FIRST);
				else displayedUser.setNameOrdering(NameOrdering.LAST_FIRST);
								
				displayedUser.setGender(gender);
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
					userTimezone = new TimeZone(displayedUser.getTimezone());					
					SessionUtils.login(getSession(), displayedUser);
					success=true;
				}catch (Exception ex) {
					addError("updateError", ex.getMessage());
				}
			}			
		}

	}

	private boolean checkUsername(String name){
		if (displayedUser.getId()!=UserDAO.fetchByUsername(name).getId()){
			return false;
		}
		return true;
	}
	
	private boolean checkEmail(String email){
		email = StringEscapeUtils.escapeSql(email);
		if (isEmpty(email)){
			return false;
		}
		if (!UserUtils.isValidEmail(email)) {
			return false;
		}
		if (displayedUser.getId()!=UserDAO.fetchByEmail(email).getId()){
			return false;
		}
		return true;
	}

	private boolean checkPass(String pass,String newPass,String rePass){
		if (isEmpty(pass) && isEmpty(newPass) && isEmpty(rePass)) {
			return true;
		} else if (isEmpty(pass) || isEmpty(newPass) || isEmpty(rePass)) {
			return false;
		} else if (!getUser().matchPassword(pass))
			return false;
		else if (!newPass.equals(rePass))
			return false;
		return true;
	}

}
