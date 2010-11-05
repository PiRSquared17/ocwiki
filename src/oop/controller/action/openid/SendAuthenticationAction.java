package oop.controller.action.openid;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.controller.action.ActionUtil;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.ax.FetchRequest;

@SuppressWarnings("rawtypes")
public class SendAuthenticationAction extends AbstractAction {

	private ConsumerManager manager;
    final private String yahooEndpoint = "https://me.yahoo.com"; 
    final private String googleEndpoint = "https://www.google.com"; 

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Đăng nhập sử dụng OpenID");
		try {
			manager = new ConsumerManager();
			String userSuppliedString = getParams().get("openIDUrl");
			if (userSuppliedString!=null){
				authRequest(userSuppliedString);
			}
		} catch (ConsumerException e) {
			throw new ActionException("Không khởi tạo được consumer", e);
		}
	}

	public ConsumerManager getManager() {
		return manager;
	}


	// --- placing the authentication request ---
	public String authRequest(String userSuppliedString)
			throws IOException {
		try {
			// Sau nay mun dang nhap xong tra ve j thi sua o day
			String returnToUrl = ActionUtil.getActionURL("user.login.openid.verification");
			List discoveries = manager.discover(userSuppliedString);
			DiscoveryInformation discovered = manager.associate(discoveries);
			getRequest().getSession().setAttribute("openid-disc", discovered);
			AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

            FetchRequest fetch = FetchRequest.createFetchRequest(); 
            if (userSuppliedString.startsWith(googleEndpoint)) { 
                    fetch.addAttribute("email", "http://axschema.org/contact/email", true); 
                    fetch.addAttribute("firstname", "http://axschema.org/namePerson/first", true); 
                    fetch.addAttribute("lastname", "http://axschema.org/namePerson/last", true); 
            } 
            else if (userSuppliedString.startsWith(yahooEndpoint)) { 
                    fetch.addAttribute("email", "http://axschema.org/contact/email", true); 
                    fetch.addAttribute("fullname", "http://axschema.org/namePerson", true); 
            } 
            else { //works for myOpenID 
                    fetch.addAttribute("fullname", "http://schema.openid.net/namePerson", true); 
                    fetch.addAttribute("email", "http://schema.openid.net/contact/email", true); 
            } 
            authReq.addExtension(fetch);
            
			// Attribute Exchange example: fetching the 'email' attribute
/*			FetchRequest fetch = FetchRequest.createFetchRequest();
			fetch.addAttribute("email",			// attribute alias
					"http://schema.openid.net/contact/email", // type URI
					true); // required

			authReq.addExtension(fetch);*/

			//if (!discovered.isVersion2()) {
				// Option 1: GET HTTP-redirect to the OpenID Provider endpoint
				// The only method supported in OpenID 1.x
				// redirect-URL usually limited ~2048 bytes
				setRedirect(authReq.getDestinationUrl(true));
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
			getSession().setAttribute("OIDManager", manager);
			
		} catch (OpenIDException e) {
			throw new ActionException(e.getMessage());
		}

		return null;
	}	

}
