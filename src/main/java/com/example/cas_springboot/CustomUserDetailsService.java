package com.example.cas_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
 */
public class CustomUserDetailsService 	
	//实现AuthenticationUserDetailsService，实现loadUserDetails方法
	implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
	@Autowired
    HttpServletRequest request;
	@Override
	public UserInfo loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		Map<String,Object> maps = token.getAssertion().getPrincipal().getAttributes();
		/*这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息*/
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername((String)maps.get("userAccount"));
		userInfo.setName((String)maps.get("realName"));
		userInfo.setAccountID((String)maps.get("accountID"));
		userInfo.setCardID((String)maps.get("cardID"));
		userInfo.setDepNO((String)maps.get("depID"));
		userInfo.setEmail((String)maps.get("email"));
		userInfo.setUserNO((String)maps.get("userNO"));
		userInfo.setUserType((String)maps.get("userType"));
		userInfo.setDepName((String)maps.get("depName"));
		request.getSession().setAttribute("userInfo", userInfo);
		return userInfo;
	}

}
