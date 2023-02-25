package com.nnrs.socket.entity;

public class Administrator implements java.io.Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7655674874085714032L;
	/**
	 * 管理员编号
	 */
	private String adminNumber;
	/**
	 * 管理员密码
	 */
	private String adminPwd;
	/**
	 * 管理员姓名
	 */
	private String adminName;
	public Administrator() {}
	public Administrator(String adminNumber, String adminPwd, String adminName) {
		super();
		this.adminNumber = adminNumber;
		this.adminPwd = adminPwd;
		this.adminName = adminName;
	}
	public String getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(String adminNumber) {
		this.adminNumber = adminNumber;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	@Override
	public String toString() {
		return "Administrator [adminNumber=" + adminNumber + ", adminPwd=" + adminPwd + ", adminName=" + adminName
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminName == null) ? 0 : adminName.hashCode());
		result = prime * result + ((adminNumber == null) ? 0 : adminNumber.hashCode());
		result = prime * result + ((adminPwd == null) ? 0 : adminPwd.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		if (adminName == null) {
			if (other.adminName != null)
				return false;
		} else if (!adminName.equals(other.adminName))
			return false;
		if (adminNumber == null) {
			if (other.adminNumber != null)
				return false;
		} else if (!adminNumber.equals(other.adminNumber))
			return false;
		if (adminPwd == null) {
			if (other.adminPwd != null)
				return false;
		} else if (!adminPwd.equals(other.adminPwd))
			return false;
		return true;
	}
	
}
