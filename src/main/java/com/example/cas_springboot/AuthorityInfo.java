package com.example.cas_springboot;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityInfo implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	/**
	 * 权限CODE
	 */
	private String authority;

	public AuthorityInfo(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
