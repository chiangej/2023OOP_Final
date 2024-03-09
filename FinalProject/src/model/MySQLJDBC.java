package model;
import java.sql.*;

/**
 * 這是個存放與MySQL資料連接相關的class，此class下每個method都是static
 */
public class MySQLJDBC {
	
	/**
	 * 這是尋找user所使用的method
	 * @param mail 輸入使用者mail
	 * @param pass 輸入使用者password
	 * @return return使用者資料，如果沒找到email與password會被設成"-1"
	 */
	public static User findUser(String mail , String pass) {
		Connection conn = null;
		Statement stmt = null;
		Boolean found = false;
		User user = new User("-1","-1");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE email ="+"'"+ mail +"'"+"AND password="+"'"+pass+"'"+ ";");
			if (!rs.next()){
				System.out.println("This person doesn't exist. No data");
				found = false;  
            }else {
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            	user.setPassword(rs.getString("password"));
            	user.setPhoneNumber(rs.getString("phoneNumber"));
            	boolean isvip;
            	if(rs.getString("vip").equals("yes")) {
            		isvip=true;
            	}else {
            		isvip=false;
            	}
            	user.setVip(isvip);
            	found = true;
            }
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);		
		}finally {
			return user;
		}
	}
	
	/**
	 * 這是插入user所使用的method，輸入基本資料
	 * @param name
	 * @param email
	 * @param phoneNumber
	 * @param vip
	 * @param password
	 */
	public static void insertUser(String name,String email,String phoneNumber,String vip,String password) {
		Connection conn=null;
        Statement stmt = null;
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	System.out.println("Success loading Mysql Driver!");
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
        	System.out.println("Success connect Mysql server!");
        	System.out.println("insert data");
        	stmt = conn.createStatement();
        	
        	String sql ="INSERT INTO user "+
        				"VALUES('"+name+"','"+email+"','"+phoneNumber+"','"+vip+"','"+password+"')";
        	stmt.executeUpdate(sql);
        					
        }catch(SQLException se) {
        	se.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	try {
        		if(stmt!=null)
        			conn.close();
        	}catch(SQLException se) {
        		
        	}
        	try {
        		if(conn!=null)
        			conn.close();
            }catch(SQLException se) {
            	se.printStackTrace();
            } 
        }
	}
	
	/**
	 * 使用owner的store name去獲得該間店的基本資料
	 * @param name 輸入店家名稱
	 * @return 獲得店家資料以owner class的形式
	 */
	public static Owner findOwnerInName(String name) {
		Connection conn = null;
		Statement stmt = null;
		Boolean found = false;
		Owner owner =new Owner("-1");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM store WHERE name ='"+name+"';");
			if (!rs.next()){
				System.out.println("This person doesn't exist. No data");
				found = false;  
            }else {
            	owner.setName(rs.getString("name"));
            	owner.setMenu(rs.getString("menu"));
            	owner.setBusiness_time(rs.getString("business_time"));
            	owner.setOrder_description(rs.getString("order_description"));
            	owner.setPosition(rs.getString("position"));
            	owner.setStore_description(rs.getString("store_description"));
            	owner.setPhone(rs.getString("phone"));
            	owner.setType(rs.getString("type"));
            	found = true;
            }
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return owner;
		}
	}
	
	/**
	 * 使用owner的phone去獲得該間店的基本資料
	 * @param phone 輸入店家phone
	 * @return 獲得店家資料以owner class的形式
	 */
	public static Owner findOwner(String phone) {
		Connection conn = null;
		Statement stmt = null;
		Boolean found = false;
		Owner owner =new Owner("-1");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM store WHERE phone ='"+phone+"';");
			if (!rs.next()){
				System.out.println("This person doesn't exist. No data");
				found = false;  
            }else {
            	owner.setName(rs.getString("name"));
            	owner.setMenu(rs.getString("menu"));
            	owner.setBusiness_time(rs.getString("business_time"));
            	owner.setOrder_description(rs.getString("order_description"));
            	owner.setPosition(rs.getString("position"));
            	owner.setStore_description(rs.getString("store_description"));
            	owner.setPhone(rs.getString("phone"));
            	owner.setType(rs.getString("type"));
            	found = true;
            }
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return owner;
		}
	}
	//停止收單
	/**
	 * 改變setting(店家營業狀態)變成False(不營業)
	 * @param name 輸入該店家名稱
	 */
	public static void changeSettingToFalse(String name){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.setting set setting = 'False' where name ='"+name+"';");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}
	//開始收單
	/**
	 * 改變setting(店家營業狀態)變成True(營業)
	 * @param name 輸入該店家名稱
	 */
	public static void changeSettingToTrue(String name){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.setting set setting = 'True' where name ='"+name+"';");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}
	//滿額
	/**
	 * 改變滿額折扣之滿額的部分
	 * @param price 輸入滿額的價錢
	 * @param name 輸入該店家名稱
	 */
	public static void changeFull(int price,String name){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.setting set full = "+price+" where name = "+"'"+name+"'"+";");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}
	//折扣
	/**
	 * 改變滿額折扣之折扣的部分
	 * @param price 輸入折扣的價錢
	 * @param name 輸入該店家名稱
	 */
	public static void changeDiscount(int price,String name){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.setting set discount = "+price+" where name = "+"'"+name+"'"+";");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}
	
	/**
	 * 確認setting(店家營業狀態)之狀態
	 * @param name 輸入該店家名稱
	 * @return 獲得該店家setting(店家營業狀態)
	 */
	public static boolean checkSetting(String name){
		Connection conn = null;
		Statement stmt = null;
		String state=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM setting WHERE name ='"+name+"';");
			while(rs.next()) {
			state=rs.getString("setting");
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			if(state.equals("True")) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	/**
	 * 確認滿額折扣之滿額之狀態
	 * @param name 輸入該店家名稱
	 * @return 獲得滿額的價錢
	 */
	public static int checkFull(String name){
		Connection conn = null;
		Statement stmt = null;
		int full=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM setting WHERE name ='"+name+"';");
			while(rs.next()) {
			full=rs.getInt("full");
			System.out.println("Operation done successfully");
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return full;
		}
	}
	
	/**
	 * 確認滿額折扣之折扣之狀態
	 * @param name 輸入該店家名稱
	 * @return 獲得折扣的價錢
	 */
	public static int checkDiscount(String name){
		Connection conn = null;
		Statement stmt = null;
		int discount=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM setting WHERE name ='"+name+"';");
			while(rs.next()) {
			discount=rs.getInt("discount");
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return discount;
		}
	}
	
	/**
	 * 用mail與password獲取Deliveryboy的基本資料，用於Deliveryboy登入
	 * @param mail 輸入該外送員之mail
	 * @param pass 輸入該外送員之password
	 * @return
	 */
	public static DeliveryBoy findDeliveryboy(String mail , String pass) {
		Connection conn = null;
		Statement stmt = null;
		Boolean found = false;
		DeliveryBoy boy = new DeliveryBoy("-1","-1");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM deliveryboy WHERE email ="+"'"+ mail +"'"+"AND password="+"'"+pass+"'"+ ";");
			if (!rs.next()){
				System.out.println("This person doesn't exist. No data");
				found = false;  
            }else {
            	boy.setName(rs.getString("name"));
            	boy.setEmail(rs.getString("email"));
            	boy.setPassword(rs.getString("password"));
            	boy.setPhoneNumber(rs.getString("phone"));
            	found = true;
            }
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);		
		}finally {
			return boy;
		}
	}
	
	/**
	 * 使用user尋找其歷史訂單
	 * @param user 輸入user名稱
	 * @return 獲取歷史訂單
	 */
	public  static String findHistoryUser(String user) {
		Connection conn = null;
		Statement stmt = null;
		String history="";
		int number=1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			boolean results = stmt.execute("SELECT * FROM history WHERE 訂餐人 ="+"'"+ user +"'" +";");
			int rsCount = 0;
			do {
	            if (results) {
	                ResultSet rs = stmt.getResultSet();
	                rsCount++;
	                while (rs.next()) {
	                    history=history+"訂餐編號: "+number + "\n" +"訂餐店家: " +rs.getString("訂餐店家")+ "\n" +"訂餐人: " +rs.getString("訂餐人")+ "\n" +"訂餐物品: "+ rs.getString("訂餐物品")+ "\n" +"總金額: "+ rs.getString("總金額")+ "\n"+"送餐人員: "+ rs.getString("送餐人員")+"\n"+"外送地址: "+ rs.getString("外送地址")+"\n"+"\n";
	                    number++;
	                }
	            }
	            results = stmt.getMoreResults();
	        } while (results);
			
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);		
		}finally {
			return history;
		}
	}
	
	/**
	 * 使用store尋找其歷史訂單
	 * @param store 輸入store名稱
	 * @return 獲取歷史訂單
	 */
	public  static String findHistoryOwner(String store) {
		Connection conn = null;
		Statement stmt = null;
		String history="";
		int number=1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			boolean results = stmt.execute("SELECT * FROM history WHERE 訂餐店家 ="+"'"+ store +"'" +";");
			int rsCount = 0;
			do {
	            if (results) {
	                ResultSet rs = stmt.getResultSet();
	                rsCount++;
	                while (rs.next()) {
	                    history=history+"訂餐編號: "+ number+ "\n" +"訂餐店家: " +rs.getString("訂餐店家")+ "\n" +"訂餐人: " +rs.getString("訂餐人")+ "\n" +"訂餐物品: "+ rs.getString("訂餐物品")+ "\n" +"總金額: "+ rs.getString("總金額")+ "\n"+"送餐人員: "+ rs.getString("送餐人員")+"\n"+"外送地址: "+ rs.getString("外送地址")+"\n"+"\n";
	                    number++;
	                }
	            }
	            results = stmt.getMoreResults();
	        } while (results);
			
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);		
		}finally {
			return history;
		}
	}
	
	/**
	 * 使用DeliveryBoy尋找其歷史訂單
	 * @param store 輸入DeliveryBoy名稱
	 * @return 獲取歷史訂單
	 */
	public  static String findHistoryDeliveryBoy(String store) {
		Connection conn = null;
		Statement stmt = null;
		String history="";
		int number=1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			boolean results = stmt.execute("SELECT * FROM history WHERE 送餐人員 ="+"'"+ store +"'" +";");
			int rsCount = 0;
			do {
	            if (results) {
	                ResultSet rs = stmt.getResultSet();
	                rsCount++;
	                while (rs.next()) {
	                    history=history+"訂餐編號: "+number + "\n" +"訂餐店家: " +rs.getString("訂餐店家")+ "\n" +"訂餐人: " +rs.getString("訂餐人")+ "\n" +"訂餐物品: "+ rs.getString("訂餐物品")+ "\n" +"總金額: "+ rs.getString("總金額")+ "\n"+"送餐人員: "+ rs.getString("送餐人員")+"\n"+"外送地址: "+ rs.getString("外送地址")+"\n"+"\n";
	                    number++;
	                }
	            }
	            results = stmt.getMoreResults();
	        } while (results);
			
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);		
		}finally {
			return history;
		}
	}

	/**
	 * 輸入deliveryboy的名稱用以獲取其基本資訊以String方式回傳
	 * @param deliveryboy 輸入deliveryboy的名稱
	 * @return 獲取其基本資訊
	 */
	public static String  findDeliveryBoyInformation(String deliveryboy) {
		Connection conn = null;
		Statement stmt = null;
		String information="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM deliveryboy WHERE name ='"+deliveryboy+"';");
			while(rs.next()) {
			information="name: "+rs.getString("name")+"\n"+"email: "+rs.getString("email")+"\n"+"phone: "+rs.getString("phone")+"\n"+"work state: "+rs.getString("workstate");
			System.out.println("Operation done successfully");
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return information;
		}
	}
	
	/**
	 * 輸入user的名稱用以獲取其基本資訊以String方式回傳
	 * @param user 輸入user的名稱
	 * @return 獲取其基本資訊
	 */
	public static String  findUserInformation(String user) {
		Connection conn = null;
		Statement stmt = null;
		String information="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE name ='"+user+"';");
			while(rs.next()) {
			information="name: "+rs.getString("name")+"\n"+"email: "+rs.getString("email")+"\n"+"phone: "+rs.getString("phoneNumber")+"\n"+"vip qualified: "+rs.getString("vip");
			System.out.println("Operation done successfully");
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return information;
		}
	}
	//不要上班
	/**
	 * 使deliveryboy的工作狀態轉回False(不進行接單)
	 * @param name  輸入deliveryboy的名稱
	 */
	public static void changeWorkStateToFalse(String name){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.deliveryboy set workstate = 'False' where name ='"+name+"';");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}
	//要上班
	/**
	 * 使deliveryboy的工作狀態轉回True(進行接單)
	 * @param name 輸入deliveryboy的名稱
	 */
	public static void changeWorkStateToTrue(String name){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.deliveryboy set workstate = 'True' where name ='"+name+"';");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}

	/**
	 * 獲取deliveryboy工作狀態的資訊
	 * @param storename 輸入deliveryboy所負責的店家
	 * @return 獲取其工作狀態
	 */
	public static String UseStoreToCheckDeliveryBoyWorkState(String storename) {
		Connection conn = null;
		Statement stmt = null;
		String workstate="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM deliveryboy WHERE 負責店家 ='"+storename+"';");
			while(rs.next()) {
			workstate = rs.getString("workstate");
			System.out.println("Operation done successfully");
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return workstate;
		}
	  }
	
	/**
	 * 使訂單存入歷史訂單中，輸入訂單基本資訊
	 * @param store
	 * @param user
	 * @param item
	 * @param cost
	 * @param deliveryboy
	 * @param address
	 * @param finish
	 */
	public static void insertHistory(String store,String user,String item,String cost,String deliveryboy,String address,String finish) {
		Connection conn=null;
        Statement stmt = null;
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	System.out.println("Success loading Mysql Driver!");
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
        	System.out.println("Success connect Mysql server!");
        	System.out.println("insert data");
        	stmt = conn.createStatement();
        	
        	String sql ="INSERT INTO history "+
        				"VALUES('"+store+"','"+user+"','"+item+"','"+cost+"','"+deliveryboy+"','"+address+"','"+finish+"')";
        	stmt.executeUpdate(sql);
        					
        }catch(SQLException se) {
        	se.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	try {
        		if(stmt!=null)
        			conn.close();
        	}catch(SQLException se) {
        		
        	}
        	try {
        		if(conn!=null)
        			conn.close();
            }catch(SQLException se) {
            	se.printStackTrace();
            } 
        }
	}

	/**
	 * 使用store去找到對應Deliveryboy
	 * @param storename 輸入store的名字
	 * @return 獲得Deliveryboy的名字
	 */
	public static String UseStoreToFindDeliveryboy(String storename){
		Connection conn = null;
		Statement stmt = null;
		String deliveryboy="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM deliveryboy WHERE 負責店家 ='"+storename+"';");
			if (!rs.next()){
				System.out.println("This person doesn't exist. No data");
            }else {
            	System.out.println("找到了");
            	deliveryboy=rs.getString("name");
            	
            }
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return deliveryboy;
		}
	}

	/**
	 * 改變訂單抵達狀態變成完成(True)
	 * @param name 輸入Deliveryboy的名字
	 */
	public static void changearrivalToTrue(String name) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.deliveryboy set 送達訂單 = '是' where name ='"+name+"';");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}
	
	/**
	 * 改變訂單抵達狀態變成未完成(False)
	 * @param name 輸入Deliveryboy的名字
	 */
	public static void changearrivalToFalse(String name) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.deliveryboy set 送達訂單 = '否' where name ='"+name+"';");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}

	/**
	 * 改變訂單抵達狀態變成尚未接單(nothing)
	 * @param name
	 */
	public static void changearrivalToNothing(String name) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.deliveryboy set 送達訂單 = '沒事' where name ='"+name+"';");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}

	/**
	 * 確定訂單抵達狀態
	 * @param deliveryboy 輸入deliveryboy名稱
	 * @return 獲得訂單抵達狀態
	 */
	public static String checkArrivalState(String deliveryboy) {
		Connection conn = null;
		Statement stmt = null;
		String ArrivalState="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM deliveryboy WHERE name ='"+deliveryboy+"';");
			while(rs.next()) {
				ArrivalState = rs.getString("送達訂單");
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return ArrivalState;
		}
	}
	
	/**
	 * 改變存放於History訂單抵達狀態變成完成(True)
	 * @param deliveryboyname
	 */
	public static void changeHistoryFinishToTrue(String deliveryboyname){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			int rs = stmt.executeUpdate("update personal_information.history set 是否送達訂單 = '否' where 送餐人員 ='"+deliveryboyname+"';");
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
		}
	}
	
	/**
	 * 確認存放於History訂單抵達狀態
	 * @param deliveryboyname 輸入deliveryboyname
	 * @return 獲取訂單抵達狀態
	 */
	public static String checkHistoryFinish(String deliveryboyname) {
		Connection conn = null;
		Statement stmt = null;
		String history = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM history WHERE 送餐人員 ="+"'"+deliveryboyname +"'"+"AND 是否送達訂單="+"'否'"+ ";");
			while(rs.next()) {
				history= "\n" +"訂餐店家: " +rs.getString("訂餐店家")+ "\n" +"訂餐人: " +rs.getString("訂餐人")+ "\n" +"訂餐物品: "+ rs.getString("訂餐物品")+ "\n" +"總金額: "+ rs.getString("總金額")+ "\n"+"送餐人員: "+ rs.getString("送餐人員")+"\n"+"外送地址: "+ rs.getString("外送地址")+"\n"+"\n";
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return history;
		}
	}
	
	public static String latitude(String name) {
		Connection conn = null;
		Statement stmt = null;
		String position="";
		String lat = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM store WHERE name ='"+name+"';");
			if (!rs.next()){
				System.out.println("This person doesn't exist. No data"); 
            }else {

            	position=rs.getString("position");
            	System.out.println(position);
            	int index = position.indexOf("lat",0);
            	lat=position.substring(position.indexOf("lat",0)+11, position.indexOf("lat",0)+17);
            	lat=lat.replaceAll(",","" );
            	System.out.println(lat);
            }
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return lat;
		}
	}
	
	public static String longtitude(String name) {
		Connection conn = null;
		Statement stmt = null;
		String position="";
		String lng = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM store WHERE name ='"+name+"';");
			if (!rs.next()){
				System.out.println("This person doesn't exist. No data"); 
            }else {

            	position=rs.getString("position");
            	System.out.println(position);
            	int index = position.indexOf("lon",0);
            	lng=position.substring(position.indexOf("lon",0)+11, position.indexOf("lon",0)+19);
            	lng=lng.replaceAll(",","" );
            	lng=lng.replaceAll(" ","" );
            	lng=lng.replaceAll("}","");
            	System.out.println(lng);
            }
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return lng;
		}
	}
	
	public static String[] checkmenu(String storename) {
		Connection conn = null;
		Statement stmt = null;
		String menu = "";
		String[] splitmenu=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_information","root","0000");
			System.out.println("Opened database successfully");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM store WHERE name ="+"'"+storename +"'"+ ";");
			while(rs.next()) {
				menu=rs.getString("menu");
				menu=menu.replaceAll("\"", "")
						 .replaceAll(" ", "")
						 .replaceAll(":", "")
						 .replaceAll(",", "")
						 .replaceAll("\\[", "")
						 .replaceAll("\\]", "")
						 .replaceAll("name", "")
						 .replaceAll("price", " ")
						 .replaceAll("}", "");
				menu=menu.substring(1,menu.length()); 
				splitmenu=menu.split("\\{");
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Operation done successfully");
		}catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);	
		}finally {
			return splitmenu;
		}
	}
}

