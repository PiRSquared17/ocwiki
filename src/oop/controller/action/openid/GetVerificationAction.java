package oop.controller.action.openid;

import org.apache.commons.lang.StringUtils;
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

import javax.servlet.ServletException;

@SuppressWarnings("unused")
public class GetVerificationAction extends AbstractAction {

	private ConsumerManager manager;

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Đăng nhập sử dụng OpenID");
		manager = (ConsumerManager) getSession().getAttribute("OIDManager");
		Identifier verified = verifyResponse();
		if (verified!=null){

		}else{
			throw new ActionException("Đăng nhập bằng openID thất bại: Có lỗi xảy ra với việc xác thực danh tính.");
		}

		
	}

	public ConsumerManager getManager() {
		return manager;
	}


	@SuppressWarnings("rawtypes")
	public Identifier verifyResponse() {
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
				User newUser = new User();
				String userUrl = verified.toString();
				newUser.setName(userUrl);
				
				OpenIDAccount found = OpenIDAccountDAO.fetchByUrl(userUrl);
				if (found!=null){
					SessionUtils.login(getSession(), found.getUser()); 
					setRedirect(Config.get().getHomeDir());
				}else{				
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
		       		            
		            OpenIDAccount newOpenIDAccount = new OpenIDAccount(userUrl, newUser);
		            OpenIDAccountDAO.persist(newOpenIDAccount);
		            
		            SessionUtils.login(getSession(), newUser); 
		            setRedirect(ActionUtil.getActionURL("user.profileedit","user="+userUrl));
				}

				return verified; // success
			}
		} catch (OpenIDException e) {
			throw new ActionException(e.getMessage());
		}
		return null;
	}

	

}
