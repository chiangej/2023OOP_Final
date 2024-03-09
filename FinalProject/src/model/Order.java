package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此class存放order點餐時相關的method(菜單顯示、計算點餐....)
 */
public class Order {
	private static String regex = "[\u4e00-\u9fa5]";
	public static String fee;

	/**
	 * 輸入店家名稱以獲得其菜單
	 * 
	 * @param storename 輸入店家名稱
	 * @return 獲得其菜單
	 */
	public static String setMenu(String storename) {
		Owner owner = MySQLJDBC.findOwnerInName(storename);
		String menu = owner.getMenu();
		String m = menu.replaceAll("}", "\n").replaceAll("\\p{Punct}", "").replaceAll(" ", "").replaceAll("name", "")
				.replaceAll("price", " ");
		int full = MySQLJDBC.checkFull(storename);
		int discount = MySQLJDBC.checkDiscount(storename);
		return m + "\n" + "店家折扣: " + "滿" + full + "送" + discount;
	}

	/**
	 * 輸入店家名稱與點餐內容，回傳餐點內容與總價錢
	 * 
	 * @param storename 輸入店家名稱
	 * @param order     輸入點餐內容
	 * @return 回傳餐點內容與總價錢
	 */
	public static String count(String storename, int cost, double distance, boolean vip) {
		Owner owner = MySQLJDBC.findOwnerInName(storename);

		if(cost > 199 &&vip) {
		cost = (int) (cost + (distance/750*5));
		}
		

		return   "總共金額 : " + cost+"元";
	}

	public  static int quickcount(String order) {
		 String[] split = order.split(" ");
		 int i=0;
		 i= (Integer.valueOf(split[1]).intValue())*(Integer.valueOf(split[2]).intValue());
		 
		 return i;
	}
	
	public static String setStoreInfromation(String storename) {
		Owner owner = MySQLJDBC.findOwnerInName(storename);
		String name= "店家名稱: "+owner.getName()+"\n";
		String phone= "電話: "+owner.getPhone()+"\n";
		String time="營業時間 :\n"+owner.getBusiness_time().replaceAll(",","\n").replaceAll("\\p{Punct}", "").replaceAll(" end", "end time :").replaceAll(" start", "start time :").replaceAll("fri","Friday:\n").replaceAll(" mon","Monday:\n").replaceAll(" sat","Saturday:\n").replaceAll(" sun","Sunday:\n").replaceAll(" thu","Thursday:\n").replaceAll(" tue","Tuesday:\n").replaceAll(" wed","Wednesday:\n")+"\n";
		String position=owner.getPosition();
		String newPosition[] = position.split("\"");
		String position1 ="店家名稱 :"+newPosition[3]+"\n";
		String type="餐點類別 :"+owner.getType().replaceAll(","," ").replaceAll("\\p{Punct}", "")+"\n";
		String store_description="店家描述 :"+owner.getStore_description()+"\n";
		String order_description="點餐描述 :"+owner.getOrder_description();
		String information=name+phone+time+position1+type+store_description+order_description;
		return information;
		
	}
	
}


