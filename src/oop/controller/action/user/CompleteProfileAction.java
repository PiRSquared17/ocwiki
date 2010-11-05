package oop.controller.action.user;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.data.FacebookAccount;
import oop.data.NameOrdering;
import oop.data.OpenIDAccount;
import oop.data.User;
import oop.db.dao.FacebookAccountDAO;
import oop.db.dao.OpenIDAccountDAO;
import oop.db.dao.UserDAO;
import oop.util.SessionUtils;
import oop.util.UserUtils;
import oop.util.Utils;

public class CompleteProfileAction extends AbstractAction {
	
	private OpenIDAccount newOpenIDAcc;
	private List<OpenIDAccount> openIDAccounts;
	private List<FacebookAccount> fbAccounts;
	private User simpleAccount;
	private boolean usedEmail=false;
	
	private boolean mergeSA=false;
	private boolean mergeOID=false;
	private boolean mergeFB=false;
	
	public List<OpenIDAccount> getOpenIDAccounts() {
		return openIDAccounts;
	}

	public List<FacebookAccount> getFbAccounts() {
		return fbAccounts;
	}

	public User getSimpleAccount() {
		return simpleAccount;
	}

	public boolean isUsedEmail() {
		return usedEmail;
	}

	public OpenIDAccount getNewOpenIDAcc() {
		return newOpenIDAcc;
	}

	public boolean isMergeSA() {
		return mergeSA;
	}

	public boolean isMergeOID() {
		return mergeOID;
	}

	public boolean isMergeFB() {
		return mergeFB;
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
						
						User check=UserDAO.fetchByEmail(newOpenIDAcc.getUser().getEmail());
						if ((!isEmpty(email)) &&(check!=null)){
							usedEmail=true;
							if (check.hasPassword()){
								mergeSA=true;
								simpleAccount=check;
							}
							openIDAccounts = OpenIDAccountDAO.fetchByUser(check.getId());
							if (openIDAccounts.size()>0){
								mergeOID=true;
							}
							fbAccounts = FacebookAccountDAO.fetchByUser(check.getId());
							if (fbAccounts.size()>0){
								mergeFB=true;
							}							
							getSession().setAttribute("newOpenIDAcc", newOpenIDAcc);
							getSession().setAttribute("newUser", newOpenIDAcc.getUser());
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

	