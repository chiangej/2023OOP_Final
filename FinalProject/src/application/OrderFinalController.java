package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.MySQLJDBC;
import model.Order;

public class OrderFinalController implements Initializable {

	/**
	 * 被點的菜顯示的地方
	 */
	@FXML
	private  TextArea result;
	
	/**
	 * 顯示店家菜單
	 */
	@FXML
	private ComboBox cbboxOrder;
	
	/**
	 * 顯示數量
	 */
	@FXML
	private ComboBox cbboxNumber;
	//有改過
	/**
	 *被點的餐
	 */
	public static  String orderitem="";
	/**
	 * 消費金額
	 */
	public static int Cost;
	//public static String orderitemshow="";
	
	/**
	 * 店家名稱
	 */
	@FXML
	private Text store;
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * 點餐確認按紐會心曾想要的菜到頁面中
	 * @param event
	 */
	public void order(ActionEvent event) {
		try {
			String item = "錯誤請重新輸入";// initial
			item = cbboxOrder.getSelectionModel().getSelectedItem().toString();
			String number = "錯誤請重新輸入";
			number = cbboxNumber.getSelectionModel().getSelectedItem().toString();
			int c = Order.quickcount(item + " " + number);
			Cost = Cost + c;
			System.out.println(Cost);
			orderitem = orderitem + item + "*" + number + "\n";
			//orderitemshow = orderitemshow + item + "*" + number + " ";
			result.setFont(new Font(12));
			result.setText(orderitem);
		} catch (Exception e) {
			result.setFont(new Font(40));
			result.setText("想用手打的嗎?");
		}
	}

	 /**
	  * 顯示店家菜單
	 * @param event
	 */
	public void info(ActionEvent event)  {
		 String  info=Order.setStoreInfromation(SwitchSceneController.StoreName);
		 result.setText(info);
 	  }
	 
	/**
	 *初始化菜單
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		store.setText(SwitchSceneController.StoreName);
		String[] a = new String[100];
		for(int i=0;i<a.length;i++) {
			a[i] =(int)i+1+"";
		}
		String[] item=MySQLJDBC.checkmenu(SwitchSceneController.StoreName);
		
		cbboxOrder.getItems().addAll(item);
		cbboxNumber.getItems().addAll(a);
	}
	
	
	/**
	 * 回到上搜尋頁面
	 * @param event
	 * @throws IOException
	 */
	public void switchToSearch(ActionEvent event) throws IOException {
		Cost = 0;
		orderitem="";
		root = FXMLLoader.load(getClass().getResource("Search.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("使用者頁面");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * 切換到輸入地址的畫面
	 * @param event
	 * @throws IOException
	 */
	public void switchToCheck(ActionEvent event) throws IOException {
		
		if(Cost != 0) {
			root = FXMLLoader.load(getClass().getResource("Check.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("確認頁面");
			stage.setScene(scene);
			stage.show();
		}
		else {
			result.setFont(new Font(40));
			result.setText("尚未點餐?");
		}
			
	}
	

	/**
	 * 將前面所有以點的商品清空
	 * @param event
	 * @throws IOException
	 */
	public void reset(ActionEvent event) throws IOException {
		Cost = 0;
		orderitem="";
		result.setText("");

	}

}
