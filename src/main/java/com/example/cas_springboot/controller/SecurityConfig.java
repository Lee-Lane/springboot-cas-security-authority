package com.example.cas_springboot.controller;

import com.example.cas_springboot.CustomUserDetailsService;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity // 启用web权限
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Value("${cas-service-url}")
    private String casServiceUrl = "https://cas.hqzh.mtn";//
    
    
//    @Value("${service-url}")
    private String serviceUrl = "http://test.hqzh.mtn:8080";//
	/** 定义认证用户信息获取来源，密码校验规则等 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.authenticationProvider(casAuthenticationProvider());
	}

	/** 定义安全策略 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()// 配置安全策略
				.antMatchers("/hello","/hello").permitAll()//定义/请求不需要验证
				.anyRequest().authenticated()// 其余的所有请求都需要验证
				.and().logout().permitAll()// 定义logout不需要验证
				.and().formLogin();// 使用form表单登录

		http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint()).and()
				.addFilter(casAuthenticationFilter()).addFilterBefore(casLogoutFilter(), LogoutFilter.class)
				.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
	}

	/** 认证的入口 */
	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(casServiceUrl+"/login");
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}

	/** 指定service相关信息 */
	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(serviceUrl+"/login");
		serviceProperties.setAuthenticateAllArtifacts(true);
		return serviceProperties;
	}

	/** CAS认证过滤器 */
	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		casAuthenticationFilter.setFilterProcessesUrl("/login");
		return casAuthenticationFilter;
	}

	/** cas 认证 Provider */
	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
		// casAuthenticationProvider.setUserDetailsService(customUserDetailsService());
		// //这里只是接口类型，实现的接口不一样，都可以的。
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setKey("casAuthenticationProviderKey");
		
		return casAuthenticationProvider;
	}


	/** 用户自定义的AuthenticationUserDetailsService */
	@Bean
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		//传递编辑参数解决获取扩展属性乱码问题
		return new Cas20ServiceTicketValidator(casServiceUrl,"UTF-8");
	}

	/** 单点登出过滤器 */
	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
//		singleSignOutFilter.setCasServerUrlPrefix(casServiceUrl);
		singleSignOutFilter.setIgnoreInitConfiguration(true);
		return singleSignOutFilter;
	}

	/** 请求单点退出过滤器 */
	@Bean
	public LogoutFilter casLogoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter(casServiceUrl+"/logout?service="+serviceUrl,
				new SecurityContextLogoutHandler());
		logoutFilter.setFilterProcessesUrl("/logout");
		return logoutFilter;
	}
}
