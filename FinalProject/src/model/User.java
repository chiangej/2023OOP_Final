package model;

/**
 * 此class存放跟User相關的資訊
 */
public class User {
	private String name;
	private String email; 
	private String phoneNumber;
	private boolean vip;
	private String password="-1";
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
