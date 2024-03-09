package application;

import java.math.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.MySQLJDBC;
import model.Order;

public class MapController implements Initializable {
	@FXML
	public WebView webView;
	@FXML
	private Label showTime;
	

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public String address;
	public String outputad;
	public String user_lat;
	public String user_lng;
	public String slat;
	public String slng;
	public static String deliverytime;
	public static String ifnotad;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		address = SwitchSceneController.address;
		outputad = getUrlContents("https://maps.googleapis.com/maps/api/geocode/json?address=" + address
				+ "&key=AIzaSyCBBnoPMDyOvYG1RakAXyH1Khzuv6BHx7w");

		user_lat = outputad.substring(outputad.indexOf("lat", 0) + 7, outputad.indexOf("lat", 0) + 15);
		user_lng = outputad.substring(outputad.indexOf("lng", 0) + 7, outputad.indexOf("lng", 0) + 15);

		slat = MySQLJDBC.latitude(SwitchSceneController.StoreName);
		slng = MySQLJDBC.longtitude(SwitchSceneController.StoreName);

		WebEngine engine = webView.getEngine();
		engine.load(
				"https://maps.googleapis.com/maps/api/staticmap?center=25.05,121.55&size=600x300&maptype=roadmap%20&markers=color:red%7C"
						+ user_lat + "," + user_lng + "&markers=color:red%7C" + slat + "," + slng
						+ "&path=color:0x000000|weight:5|" + user_lat + "," + user_lng + "|" + slat + "," + slng
						+ "&key=AIzaSyCBBnoPMDyOvYG1RakAXyH1Khzuv6BHx7w");

	}
	
		
	private static String getUrlContents(String theUrl)  
	  {  
	    StringBuilder content = new StringBuilder();  
	  // Use try and catch to avoid the exceptions  
	    try  
	    {  
	      URL url = new URL(theUrl); // creating a url object  
	      URLConnection urlConnection = url.openConnection(); // creating a urlconnection object  
	  
	      // wrapping the urlconnection in a bufferedreader  
	      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
	      String line;  
	      // reading from the urlconnection using the bufferedreader  
	      while ((line = bufferedReader.readLine()) != null)  
	      {  
	        content.append(line);  
	      }  
	      bufferedReader.close();  
	    }  
	    catch(Exception e)  
	    {  
	      e.printStackTrace();  
	    }  
	    return content.toString();  
	  }  
	
	public void switchToSearch(ActionEvent event) throws IOException {
		MySQLJDBC.changeWorkStateToFalse(LoginController.deliveryboyname);
		MySQLJDBC.insertHistory(SwitchSceneController.StoreName, LoginController.username, OrderFinalController.orderitem, OrderFinalController.Cost+"",
				MySQLJDBC.UseStoreToFindDeliveryboy(SwitchSceneController.StoreName), address, "否");
		MySQLJDBC.changearrivalToFalse(MySQLJDBC.UseStoreToFindDeliveryboy(SwitchSceneController.StoreName));
		root = FXMLLoader.load(getClass().getResource("Search.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("使用者頁面");
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToCheck(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Check.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("確認頁面");
		stage.setScene(scene);
		stage.show();

	}
	private static double rad(double d) {
		return d * Math.PI / 180.0;
		}
	
	@FXML
	public  void showtimeAndmoney() {
			double ulat=Double.parseDouble(user_lat);
			double ulng=Double.parseDouble(user_lng);
			double slatt=Double.parseDouble(slat);
			double slngg=Double.parseDouble(slng);
			double EARTH_RADIUS = 6378137;
			double raduser_lat = rad(ulat);
			double radLat2 = rad(slatt);
			double a = raduser_lat - radLat2;
			double b = rad(ulng) - rad(slngg);
			double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
			+ Math.cos(raduser_lat) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
			distance = distance * EARTH_RADIUS;
			distance = Math.round(distance * 10000) / 10000;
			
			//String count(String storename, int cost, double distance, boolean vip)
			String m3 = Order.count(SwitchSceneController.StoreName, OrderFinalController.Cost,distance,LoginController.vip);
			showTime.setText("預計送達時間： "+(int)distance/750+"分鐘"+"\n外送金額： "+m3);		
	}}
		
	
	
