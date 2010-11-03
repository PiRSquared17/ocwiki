package oop.controller.action.user;

import static org.apache.commons.lang.StringUtils.isEmpty;

import org.apache.commons.lang.StringEscapeUtils;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.data.NameOrdering;
import oop.data.OpenIDAccount;
import oop.data.User;
import oop.db.dao.OpenIDAccountDAO;
import oop.db.dao.UserDAO;
import oop.util.SessionUtils;
import oop.util.UserUtils;
import oop.util.Utils;

public class CompleteProfileAction extends AbstractAction {
	
	private OpenIDAccount newOpenIDAcc;
	private String providerUrl="";
	boolean usedEmail=false;
	
	public String getProviderUrl() {
		return providerUrl;
	}

	public boolean isUsedEmail() {
		return usedEmail;
	}

	public OpenIDAccount getNewOpenIDAcc() {
		return newOpenIDAcc;
	}

	@Override
	public void performImpl() throws Exception {
		boolean actionError=false;
		if (getParams().get("actionError")!=null){
			actionError = getParams().getBoolean("actionError");
		}
		if (actionError==true){
			addError("verifyError","Việc xác thực tài khoản OpenID không thành công, bạn hãy thực hiện lại");
		}else{
			if ((getSession().getAttribute("newOIDAcc")==null)||(getSession().getAttribute("newUser")==null)){
				setRedirect(Config.get().getHomeDir());
			}else{
				newOpenIDAcc=new OpenIDAccount((OpenIDAccount) getSession().getAttribute("newOIDAcc"));
				newOpenIDAcc.setUser(new User((User)getSession().getAttribute("newUser")));
				if ("change".equals(getParams().get("change"))){
					String fullName=getParams().get("fullname-edit-input");
					String firstName=getParams().get("firstname-edit-input");
					String lastName=getParams().get("lastname-edit-input");
					String email=getParams().get("email-edit-input");
					if (!checkEmail(email)) {
						addError("emailError", "Email không hợp lệ");
					}
					if (!checkFullname(lastName, firstName)){
						addError("fullnameError","Bạn nên điền họ hoặc tên, không được để trống cả 2 giá trị này");
					}
					if (!hasErrors()){
						if ("firstLast".compareTo(fullName)==0) newOpenIDAcc.getUser().setNameOrdering(NameOrdering.FIRST_LAST);
						else if ("lastMiddleFirst".compareTo(fullName)==0) newOpenIDAcc.getUser().setNameOrdering(NameOrdering.LAST_MIDDLE_FIRST);
						else newOpenIDAcc.getUser().setNameOrdering(NameOrdering.LAST_FIRST);
						newOpenIDAcc.getUser().setFirstName(firstName);
						newOpenIDAcc.getUser().setLastName(lastName);
						newOpenIDAcc.getUser().setEmail(email);
						if ((!isEmpty(email)) &&(UserDAO.fetchByEmail(newOpenIDAcc.getUser().getEmail())!=null)){
							usedEmail=true;
							providerUrl = (OpenIDAccountDAO.fetchByUser(UserDAO.fetchByEmail(email).getId())).get(0).getProviderUrl();
							getSession().setAttribute("newOpenIDAcc", newOpenIDAcc);
							getSession().setAttribute("newUser", newOpenIDAcc.getUser());
							//getSession().setAttribute("providerUrl", providerUrl);
						}else{		
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

	