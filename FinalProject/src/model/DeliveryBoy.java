package model;

/**
 * 此class存放跟DeliveryBoy相關的資訊(name,email,phoneNumber,password)
 */
public class DeliveryBoy {
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	
	public DeliveryBoy(String email, String password) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
