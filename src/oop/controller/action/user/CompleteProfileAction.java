package oop.controller.action.user;

import static org.apache.commons.lang.StringUtils.isEmpty;

import org.apache.commons.lang.StringEscapeUtils;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.data.NameOrdering;
import oop.data.OpenIDAccount;
import oop.db.dao.OpenIDAccountDAO;
import oop.db.dao.UserDAO;
import oop.util.SessionUtils;
import oop.util.UserUtils;

public class CompleteProfileAction extends AbstractAction {
	
	private OpenIDAccount newOpenIDAcc;
	boolean usedEmail=false;

	public boolean isUsedEmail() {
		return usedEmail;
	}

	public OpenIDAccount getNewOpenIDAcc() {
		return newOpenIDAcc;
	}

	@Override
	public void performImpl() throws Exception {
		newOpenIDAcc=((OpenIDAccount) getSession().getAttribute("newOIDAcc"));
		if ("change".equals(getParams().get("change"))){
			String fullName=getParams().get("fullname-edit-input");
			String firstName=getParams().get("firstname-edit-input");
			String lastName=getParams().get("lastname-edit-input");
			String email=getParams().get("email-edit-input");
			if (!checkEmail(email)) {
				addError("emailError", "Email không hợp lệ");
			}
			if (!checkFullname(lastName, firstName)){
				addError("fullnameError","Phải nên họ hoặc tên, không được để trống cả 2 giá trị này");
			}
			if (!hasErrors()){
				if (newOpenIDAcc.getUser().getId()!=UserDAO.fetchByEmail(email).getId()){
					usedEmail=true;
				}else{
					if ("firstLast".compareTo(fullName)==0) newOpenIDAcc.getUser().setNameOrdering(NameOrdering.FIRST_LAST);
					else if ("lastMiddleFirst".compareTo(fullName)==0) newOpenIDAcc.getUser().setNameOrdering(NameOrdering.LAST_MIDDLE_FIRST);
					else newOpenIDAcc.getUser().setNameOrdering(NameOrdering.LAST_FIRST);
					newOpenIDAcc.getUser().setFirstName(firstName);
					newOpenIDAcc.getUser().setLastName(lastName);
					newOpenIDAcc.getUser().setEmail(email);
	
					try{
						OpenIDAccountDAO.persist(newOpenIDAcc);				
						SessionUtils.login(getSession(), newOpenIDAcc.getUser());
						setRedirect(Config.get().getHomeDir());
					}catch (Exception ex) {
						addError("updateError", ex.getMessage());
					}
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

	private boolean checkFullname(String lastName, String firstName){
		if (isEmpty(firstName) && isEmpty(lastName)){
			return false;
		}
		return true;
	}
}

	