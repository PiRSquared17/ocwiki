package oop.controller.action.openid;

import static org.apache.commons.lang.StringUtils.isEmpty;

import org.apache.commons.lang.StringUtils;
import org.hibernate.ejb.criteria.predicate.IsEmptyPredicate;
import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.controller.action.ActionUtil;
import oop.data.OpenIDAccount;
import oop.data.User;
import oop.db.dao.OpenIDAccountDAO;
import oop.util.BlockedUserException;
import oop.util.SessionUtils;

import java.util.List;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

import com.oreilly.servlet.ParameterNotFoundException;

@SuppressWarnings("unused")
public class GetVerificationAction extends AbstractAction {

	private ConsumerManager manager;
	private Boolean connect = false;
	@Override
	public void performImpl() throws Exception {
		title("Đăng nhập sử dụng OpenID");
		if (getSession().getAttribute("OIDManager")!=null){
			manager = (ConsumerManager) getSession().getAttribute("OIDManager");
			if (getSession().getAttribute("connect")!=null){
				connect=(Boolean) getSession().getAttribute("connect");
			}
			Identifier verified = verifyResponse();
			if (verified!=null){
	
			}else{
				if (connect.booleanValue()==true){
					setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
					getSession().removeAttribute("connect");
				}else{
					throw new ActionException("Đăng nhập bằng openID thất bại: Có lỗi xảy ra với việc xác thực danh tính.");
				}
			}
		}else{
			if (connect.booleanValue()==true){
				setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
				getSession().removeAttribute("connect");
			}else{
				throw new ActionException("Đăng nhập bằng openID thất bại: Lỗi phiên làm việc. Bạn hãy thực hiện lại.");
			}
		}
	}

	public ConsumerManager getManager() {
		return manager;
	}


	public Identifier verifyResponse() throws BlockedUserException {

		try {
			ParameterList response = new ParameterList(getRequest()
					.getParameterMap());

			// retrieve the previously stored discovery information
			DiscoveryInformation discovered = (DiscoveryInformation) getRequest()
					.getSession().getAttribute("openid-disc");

			// extract the receiving URL from the HTTP request
			StringBuffer receivingURL = getRequest().getRequestURL();
			String queryString = getRequest().getQueryString();
			if (queryString != null && queryString.length() > 0)
				receivingURL.append("?").append(getRequest().getQueryString());

			VerificationResult verification = manager.verify(receivingURL
					.toString(), response, discovered);

			Identifier verified = verification.getVerifiedId();
			if (verified != null) {
				String userUrl = verified.toString();
				String providerUrl = (String) getSession().getAttribute("providerUrl");

				OpenIDAccount found = OpenIDAccountDAO.fetchByUrl(userUrl);
				if (found!=null){
					
					if (connect.booleanValue()==true){
						User newUser;
						OpenIDAccount newOpenIDAcc;
					
						if ((getSession().getAttribute("newOIDAcc")==null)||(getSession().getAttribute("newUser")==null)){
							setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
						}else{							
							newUser = new User((User)getSession().getAttribute("newUser"));
							newOpenIDAcc = new OpenIDAccount((OpenIDAccount) getSession().getAttribute("newOIDAcc"));
							
							if (!isEmpty(newUser.getLastName())){
								found.getUser().setLastName(newUser.getLastName());
							}
							if (!isEmpty(newUser.getFirstName())){
								found.getUser().setFirstName(newUser.getFirstName());
							}
							found.getUser().setNameOrdering(newUser.getNameOrdering());
							
							newOpenIDAcc.setUser(found.getUser());
							try{
								OpenIDAccountDAO.persist(newOpenIDAcc);
								SessionUtils.login(getSession(), newOpenIDAcc.getUser());
								setRedirect(ActionUtil.getActionURL("user.profileedit","mergeUser=true"));
							}catch (Exception e) {
								setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
								//throw new ActionException(e.getMessage());
							}
							
						}						
						getSession().removeAttribute("connect");
						
					}else{
						SessionUtils.login(getSession(), found.getUser()); 
						setRedirect(Config.get().getHomeDir());
					}
				}else{	
					User newUser = new User();
					newUser.setName(null);
	                AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();
	
		            if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX))
		            {
		                FetchResponse fetchResp = (FetchResponse) authSuccess
		                        .getExtension(AxMessage.OPENID_NS_AX);
		
		                List emails = fetchResp.getAttributeValues("email");
		                if (emails.size()>0){
		                	newUser.setEmail((String) emails.get(0));
		                }
		                
		                List fullNames = fetchResp.getAttributeValues("fullname");
		                if (fullNames.size()>0){
		                	newUser.setLastName((String) fullNames.get(0));
		                }
		                
		                List firstNames = fetchResp.getAttributeValues("firstname");
			            if (firstNames.size()>0) {
			            	newUser.setFirstName((String) firstNames.get(0));
			            }
			            List lastNames = fetchResp.getAttributeValues("lastname");
			            if (lastNames.size()>0){
			            	newUser.setLastName((String) lastNames.get(0));
			            }               
		            }
		       		        
		            if (isEmpty(newUser.getFullname())) newUser.setFirstName("người dùng"+newUser.getId());
		            
		            getSession().setAttribute("newOIDAcc", new OpenIDAccount(userUrl,providerUrl,newUser));
		            getSession().setAttribute("newUser", newUser);
		           
		            //OpenIDAccount newOpenIDAccount = new OpenIDAccount(userUrl, newUser);
		            //OpenIDAccountDAO.persist(newOpenIDAccount);
		            
		            //SessionUtils.login(getSession(), newUser); 
		            
		            setRedirect(ActionUtil.getActionURL("user.profile.complete"));
				}

				return verified; // success
			}
		} catch (OpenIDException e) {
			if (connect.booleanValue()==true){
				setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
				getSession().removeAttribute("connect");
			}else{
				throw new ActionException(e.getMessage());
			}
		}
		return null;
	}

	

}
