package oop.controller.action.openid;

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

import java.util.List;
import java.io.IOException;

@SuppressWarnings("unused")
public class GetVerificationAction extends AbstractAction {

	public ConsumerManager manager;

	@Override
	public void performImpl() throws Exception {
		title("Đăng nhập sử dụng OpenID");
		manager = (ConsumerManager) getSession().getAttribute("OIDManager");
		verifyResponse();
	}

	public ConsumerManager getManager() {
		return manager;
	}


	public Identifier verifyResponse() {
		try {
			// extract the parameters from the authentication response
			// (which comes in as a HTTP request from the OpenID provider)
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

			// verify the response; ConsumerManager needs to be the same
			// (static) instance used to place the authentication request
			VerificationResult verification = manager.verify(receivingURL
					.toString(), response, discovered);

			// examine the verification result and extract the verified
			// identifier
			Identifier verified = verification.getVerifiedId();
			if (verified != null) {
				/*AuthSuccess authSuccess = (AuthSuccess) verification
						.getAuthResponse();

				if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
					FetchResponse fetchResp = (FetchResponse) authSuccess
							.getExtension(AxMessage.OPENID_NS_AX);

					List emails = fetchResp.getAttributeValues("email");
					String email = (String) emails.get(0);
				}*/
				setRedirect(Config.get().getHomeDir());
				return verified; // success
			}
		} catch (OpenIDException e) {
			throw new ActionException(e.getMessage());
		}

		return null;
	}

	

}
