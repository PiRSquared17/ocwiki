package oop.util;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.controller.action.ActionUtil;
import oop.data.OpenIDAccount;
import oop.data.User;
import oop.db.dao.OpenIDAccountDAO;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
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

public final class OpenIDUtils {
	
    final private static String yahooEndpoint = "http://me.yahoo.com"; 
    final private static String yahooEndpoint_s = "https://me.yahoo.com"; 
    final private static String googleEndpoint = "http://www.google.com"; 
    final private static String googleEndpoint_s = "https://www.google.com"; 
	
	@SuppressWarnings("unchecked")
	// --- placing the authentication request ---
	public static String authRequest(String userSuppliedString, boolean connect, AbstractAction action, HttpSession session)
			throws IOException {
		User whoIsLogin = SessionUtils.getUser(session);
		if (whoIsLogin==null){
			ConsumerManager manager = null;
			try {
				manager = new ConsumerManager();
				// Sau nay mun dang nhap xong tra ve j thi sua o day
				String returnToUrl = ActionUtil.getActionURL("user.login.openid.verification");
				List discoveries = manager.discover(userSuppliedString);
				DiscoveryInformation discovered = manager.associate(discoveries);
				session.setAttribute("openid-disc", discovered);
				AuthRequest authReq = manager.authenticate(discovered, returnToUrl);
	
	            FetchRequest fetch = FetchRequest.createFetchRequest(); 
	            if (userSuppliedString.startsWith(googleEndpoint)||userSuppliedString.startsWith(googleEndpoint_s)) { 
	                    fetch.addAttribute("email", "http://axschema.org/contact/email", true); 
	                    fetch.addAttribute("firstname", "http://axschema.org/namePerson/first", true); 
	                    fetch.addAttribute("lastname", "http://axschema.org/namePerson/last", true); 
	            } 
	            else if (userSuppliedString.startsWith(yahooEndpoint)||userSuppliedString.startsWith(yahooEndpoint_s)) { 
	                    fetch.addAttribute("email", "http://axschema.org/contact/email", true); 
	                    fetch.addAttribute("fullname", "http://axschema.org/namePerson", true); 
	            } 
	            else { //works for myOpenID 
	                    fetch.addAttribute("fullname", "http://schema.openid.net/namePerson", true); 
	                    fetch.addAttribute("email", "http://schema.openid.net/contact/email", true);
	                    
	                    fetch.addAttribute("email_2", "http://axschema.org/contact/email", true); 
	                    fetch.addAttribute("firstname_2", "http://axschema.org/namePerson/first", true); 
	                    fetch.addAttribute("lastname_2", "http://axschema.org/namePerson/last", true); 
	                    fetch.addAttribute("fullname_2", "http://axschema.org/namePerson", true);
	            } 
	            authReq.addExtension(fetch);
	        	session.setAttribute("OIDManager", manager);
	        	
	        	if (action==null){
	        		return authReq.getDestinationUrl(true);
	        	}else{
					//if (!discovered.isVersion2()) {
						// Option 1: GET HTTP-redirect to the OpenID Provider endpoint
						// The only method supported in OpenID 1.x
						// redirect-URL usually limited ~2048 bytes
		            action.setRedirect(authReq.getDestinationUrl(true));
						/*return null;
					} else {
						// Option 2: HTML FORM Redirection (Allows payloads >2048 bytes)
		
						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher("formredirection.jsp");
						httpReq.setAttribute("parameterMap", authReq.getParameterMap());
						httpReq.setAttribute("destinationUrl", authReq
								.getDestinationUrl(false));
						dispatcher.forward(httpReq, httpResp);
					}*/
	        	}
	
				
			} catch (OpenIDException e) {
				if (session.getAttribute("OIDManager")!=null){
					session.removeAttribute("OIDManager");
				}
				if (session.getAttribute("openid-disc")!=null){
					session.removeAttribute("openid-disc");
				}
				if (connect==true){
					action.setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
					session.removeAttribute("connect");
	
				}else{
					throw new ActionException(e.getMessage());
				}
	
			}
			return null;
		}else{
			if (action==null){
        		return Config.get().getHomeDir();
        	}else{
	            action.setRedirect(Config.get().getHomeDir());
	            return null;
        	}
		}
	}
	
	public static Identifier verifyResponse(boolean connect, AbstractAction action, HttpSession session) throws BlockedUserException {

		User whoIsLogin = SessionUtils.getUser(session);
		if (whoIsLogin==null){
			try {
				ParameterList response = new ParameterList(action.getRequest()
						.getParameterMap());
				//Nhận lại manager va discovered trong sesion
				ConsumerManager manager;	
				DiscoveryInformation discovered;
				if ((session.getAttribute("OIDManager")!=null)&&(session.getAttribute("openid-disc")!=null)){
					manager = (ConsumerManager) session.getAttribute("OIDManager");
					discovered = (DiscoveryInformation) session.getAttribute("openid-disc");
				}else{
					session.removeAttribute("openid-disc");
					session.removeAttribute("OIDManager");
					if (connect==true){
						action.setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
						session.removeAttribute("connect");
						return null;
					}else{
						throw new ActionException("Đăng nhập bằng openID thất bại: Lỗi phiên làm việc. Bạn hãy thực hiện lại.");
					}
	
				}
	
				// extract the receiving URL from the HTTP request
				StringBuffer receivingURL = action.getRequest().getRequestURL();
				String queryString = action.getRequest().getQueryString();
				if (queryString != null && queryString.length() > 0)
					receivingURL.append("?").append(action.getRequest().getQueryString());
	
				VerificationResult verification = manager.verify(receivingURL
						.toString(), response, discovered);
	
				//Nhận lại userUrl
				Identifier verified = verification.getVerifiedId();
				if (verified != null) {
					String userUrl = verified.toString();
					String providerUrl = (String) session.getAttribute("providerUrl");
	
					OpenIDAccount found = OpenIDAccountDAO.fetchByUrl(userUrl);
					if (found!=null){					
						if (connect==true){
							User newUser;
							OpenIDAccount newOpenIDAcc;
						
							if ((session.getAttribute("newOIDAcc")==null)||(session.getAttribute("newUser")==null)){
								action.setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
							}else{							
								newUser = new User((User)session.getAttribute("newUser"));
								newOpenIDAcc = new OpenIDAccount((OpenIDAccount) session.getAttribute("newOIDAcc"));
								
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
									SessionUtils.login(session, newOpenIDAcc.getUser());
									action.setRedirect(ActionUtil.getActionURL("user.profileedit","mergeUser=true"));
								}catch (Exception e) {
									action.setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
								}
								
							}						
							session.removeAttribute("connect");
							
						}else{
							SessionUtils.login(session, found.getUser()); 
							action.setRedirect(Config.get().getHomeDir());
						}
					}else{	
						User newUser = new User();
						newUser.setName(null);
		                AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();
		
			            if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX))
			            {
			                FetchResponse fetchResp = (FetchResponse) authSuccess
			                        .getExtension(AxMessage.OPENID_NS_AX);
			
			                List emails = fetchResp.getAttributeValues("email_2");
			                if (emails.size()>0){
			                	if (!isEmpty((String) emails.get(0)))
			                		newUser.setEmail((String) emails.get(0));
			                }
			                
			                List fullNames = fetchResp.getAttributeValues("fullname_2");
			                if (fullNames.size()>0){
			                	if (!isEmpty((String) fullNames.get(0)))
			                		newUser.setFirstName((String) fullNames.get(0));
			                }
			                
			                List firstNames = fetchResp.getAttributeValues("firstname_2");
				            if (firstNames.size()>0) {
				            	if (!isEmpty((String) firstNames.get(0)))
				            		newUser.setFirstName((String) firstNames.get(0));
				            }
				            List lastNames = fetchResp.getAttributeValues("lastname_2");
				            if (lastNames.size()>0){
				            	if (!isEmpty((String) lastNames.get(0)))
				            		newUser.setLastName((String) lastNames.get(0));
				            }    
				            
			                emails = fetchResp.getAttributeValues("email");
			                if (emails.size()>0){
			                	if (!isEmpty((String) emails.get(0)))
			                		newUser.setEmail((String) emails.get(0));
			                }
			                
			                fullNames = fetchResp.getAttributeValues("fullname");
			                if (fullNames.size()>0){
			                	if (!isEmpty((String) fullNames.get(0)))
			                		newUser.setFirstName((String) fullNames.get(0));
			                }
			                
			                firstNames = fetchResp.getAttributeValues("firstname");
				            if (firstNames.size()>0) {
				            	if (!isEmpty((String) firstNames.get(0)))
				            		newUser.setFirstName((String) firstNames.get(0));
				            }
				            lastNames = fetchResp.getAttributeValues("lastname");
				            if (lastNames.size()>0){
				            	if (!isEmpty((String) lastNames.get(0)))
				            		newUser.setLastName((String) lastNames.get(0));
				            }               
			            }
			       		        
			            if (isEmpty(newUser.getFullname())) newUser.setFirstName("người dùng"+newUser.getId());
			            
			            session.setAttribute("newOIDAcc", new OpenIDAccount(userUrl,providerUrl,newUser));
			            session.setAttribute("newUser", newUser);
			           
			            //OpenIDAccount newOpenIDAccount = new OpenIDAccount(userUrl, newUser);
			            //OpenIDAccountDAO.persist(newOpenIDAccount);
			            
			            //SessionUtils.login(getSession(), newUser); 
			            
			            action.setRedirect(ActionUtil.getActionURL("user.profile.complete"));
					}
	
					return verified; // success
				}
			} catch (OpenIDException e) {
				session.removeAttribute("openid-disc");
				session.removeAttribute("OIDManager");
				if (connect==true){
					action.setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
					session.removeAttribute("connect");
				}else{
					throw new ActionException(e.getMessage());
				}
			}
			session.removeAttribute("openid-disc");
			session.removeAttribute("OIDManager");
			return null;
		}else{
            action.setRedirect(Config.get().getHomeDir());
            return null;
		}
	}
	
	public static User getUs(HttpSession session){
		return SessionUtils.getUser(session);
	}
}




