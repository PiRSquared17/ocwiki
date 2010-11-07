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
import oop.util.OpenIDUtils;
import oop.util.SessionUtils;

import java.util.List;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

import com.oreilly.servlet.ParameterNotFoundException;

@SuppressWarnings("unused")
public class GetVerificationAction extends AbstractAction {

	private Boolean connect = false;
	@Override
	public void performImpl() {
		title("Đăng nhập sử dụng OpenID");
		User whoIsLogin = getUser();
		if (whoIsLogin==null){
			Boolean connect = false;
			if (getSession().getAttribute("connect")!=null){
				connect=(Boolean) getSession().getAttribute("connect");
			}
			Identifier verified = OpenIDUtils.verifyResponse(connect.booleanValue(),this,getSession());
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
			setRedirect(Config.get().getHomeDir());
		}
	}

}
