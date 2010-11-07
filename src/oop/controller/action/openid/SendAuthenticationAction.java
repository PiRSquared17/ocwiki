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

import com.oreilly.servlet.ParameterNotFoundException;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.controller.action.ActionUtil;
import oop.data.User;
import oop.util.OpenIDUtils;
import oop.util.SessionUtils;

import java.util.List;
import java.io.IOException;

@SuppressWarnings("unused")
public class SendAuthenticationAction extends AbstractAction {

	@Override
	public void performImpl() {
		title("Đăng nhập sử dụng OpenID");
		User whoIsLogin = getUser();
		if (whoIsLogin==null){
			boolean connect = false;
			String userSuppliedString=null;
			if (getParams().get("connect")!=null){
				connect=getParams().getBoolean("connect");
				getSession().setAttribute("connect", connect);
			}
			if (getParams().get("openIDUrl")!=null){
				userSuppliedString = getParams().get("openIDUrl");
			}
			getSession().setAttribute("providerUrl", userSuppliedString);
			
			if (userSuppliedString!=null){
				try {
					OpenIDUtils.authRequest(userSuppliedString, connect, this, getSession());
				} catch (IOException e) {
					if (connect==true){
						setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
						getSession().removeAttribute("connect");
					}else{
						addError("UserUrl", e.getMessage());
					}
				}
			}else{
				if (connect==true){
					setRedirect(ActionUtil.getActionURL("user.profile.complete","actionError=true"));
					getSession().removeAttribute("connect");
				}else{
					addError("UserUrl", "Bạn chưa nhập đường dẫn.");
				}
			}
		}else{
			setRedirect(Config.get().getHomeDir());
		}
	}
}
