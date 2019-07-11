package com.example.cas_springboot;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息
 * @、这里我写了几个较为常用的字段，id，name，username，password，可以根据实际的情况自己增加
 */
public class UserInfo implements UserDetails {
	private static final long serialVersionUID = -1041327031937199938L;

	/**
	 * 用户AccountID
	 */
	private String accountID;

	/**
	 * 用户姓名
	 */
	private String name;

	/**
	 * 登录名称
	 */
	private String username;

	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 单位编号
	 */
	private String depNO = "";
	/**
	 * 身份证号码
	 */
	private String cardID = "";
	/**
	 * 电子邮件
	 */
	private String email = "";
	/**
	 * 用户类型
	 */
	private String userType = "";
	/**
	 * 用户编号
	 */
	private String userNO = "";
	
	private boolean isAccountNonExpired = true;

	private boolean isAccountNonLocked = true;

	private boolean isCredentialsNonExpired = true;

	private boolean isEnabled = true;
	
	/**
     * 用户类型
     */
    private String depName; 

	private Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserNO() {
		return userNO;
	}

	public void setUserNO(String userNO) {
		this.userNO = userNO;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getDepNO() {
		return depNO;
	}

	public void setDepNO(String depNO) {
		this.depNO = depNO;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) authorities;
	}

	public void setAuthorities(Set<AuthorityInfo> authorities) {
		this.authorities = authorities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
    
    public String getDepName() {
        return depName;
    }

    
    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "UserInfo [accountID=" + accountID + ", name=" + name + ", username=" + username + ", password="
                + password + ", depNO=" + depNO + ", cardID=" + cardID + ", email=" + email + ", userType=" + userType
                + ", userNO=" + userNO + ", isAccountNonExpired=" + isAccountNonExpired + ", isAccountNonLocked="
                + isAccountNonLocked + ", isCredentialsNonExpired=" + isCredentialsNonExpired + ", isEnabled="
                + isEnabled + ", depName=" + depName + ", authorities=" + authorities + "]";
    }
	
	
}
