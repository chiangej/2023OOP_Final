package model;

/**
 *此class存放跟Owner相關的資訊
 */
public class Owner {
	private String name;
	private String position;
	private String phone;
	private String store_description;
	private String order_description;
	private String type;
	private String menu;
	private String business_time;
	public Owner(String phone){
		this.phone=phone;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStore_description() {
		return store_description;
	}
	public void setStore_description(String store_description) {
		this.store_description = store_description;
	}
	public String getOrder_description() {
		return order_description;
	}
	public void setOrder_description(String order_description) {
		this.order_description = order_description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getBusiness_time() {
		return business_time;
	}
	public void setBusiness_time(String business_time) {
		this.business_time = business_time;
	}
	
}
