package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;

public class LoginController {
	/**
	 * 顯示店家跟使用者帳號密碼有錯誤
	 */
	@FXML
	private Label lblStatus;
	@FXML
	private Label lblsetdiscountstate;
	/**
	 * 顯示外送員帳密有誤
	 */
	@FXML
	private Label lbldeliveryStatus;
	
	/**
	 * 顯示店家菜單、資訊、歷史訂單
	 */
	@FXML
	private Label lblinfo;
	
	/**
	 * 顯示店家有沒有營業
	 */
	@FXML
	private Label lblOpenStatus;

	@FXML
	private TextField txtUserMail;

	@FXML
	private TextField txtPassword;
	
	/**
	 * 店家輸入滿多少錢可以折扣
	 */
	@FXML
	private TextField full;
	
	/**
	 * 店家輸入折扣多少
	 */
	@FXML
	private TextField discount;

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public String mail;
	public static String password;
	public static String storename; // 店家名稱
	public static String deliveryboyname;//使用者名稱
	public static String username;  //使用者名稱
	public static boolean vip;

	/**
	 * @param event
	 * trigger button 到使用者頁面
	 * @throws IOException
	 */
	public void UserLogin(ActionEvent event) throws IOException {
		mail = txtUserMail.getText();
		password = txtPassword.getText();
		User user = MySQLJDBC.findUser(mail, password);
		username = user.getName();
		vip=user.isVip();
		if (user.getEmail().equals("-1") && user.getPassword().equals("-1")) {
			lblStatus.setText("帳號或密碼有誤");
		} else {
			root = FXMLLoader.load(getClass().getResource("Search.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("使用者頁面");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}

	/**
	 * @param event
	 * trigger button 到店家使用頁面
	 * @throws IOException
	 */
	public void OwnerLogin(ActionEvent event) throws IOException {
		Owner owner=MySQLJDBC.findOwner(txtPassword.getText());
		password = owner.getPhone();
		storename=owner.getName();
		if (password != "-1") {
			root = FXMLLoader.load(getClass().getResource("OwnerInterface.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("店家頁面");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			lblStatus.setText("帳號或密碼有誤");
		}

	}
	/**
	 * @param event
	 * trigger button 到外送員使用頁面
	 * @throws IOException
	 */
	public void DeliveryBoyLogin(ActionEvent event) throws IOException {
		String mail = txtUserMail.getText();
		String password = txtPassword.getText();
		DeliveryBoy d1 = MySQLJDBC.findDeliveryboy(mail, password);
		 deliveryboyname = d1.getName();
		if (d1.getEmail().equals("-1") && d1.getPassword().equals("-1")) {
			lbldeliveryStatus.setText("帳號或密碼有誤");
		} else {
			root = FXMLLoader.load(getClass().getResource("DeliveryBoyInterface.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("外送員頁面");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	}
	/**
	 * 
	 * @param event
	 * trigger button 到設定折扣頁面
	 * @throws IOException
	 * 
	 */
	public void switchToSetDiscount(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("setDiscount.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("折扣設定");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	/**
	 * @param event
	 * trigger button 到店家使用頁面
	 * @throws IOException
	 */
	public void switchToOwnerInterface(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("OwnerInterface.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("店家頁面");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	
	@FXML
	public void ownershowinfo() {
		lblinfo.setFont(new Font(24));
		Owner owner = MySQLJDBC.findOwner(password);
		String information1 = Order.setStoreInfromation(owner.getName());
		lblinfo.setText(information1);

	}

	@FXML
	public void ownershowmenu() {
		lblinfo.setFont(new Font(24));
		Owner owner = MySQLJDBC.findOwner(password);
		String menu = Order.setMenu(owner.getName());
		lblinfo.setText(menu);
	}
	
	/**
	 * 設定店家不要接單
	 */
	@FXML
	public void takeNOorder() {
		MySQLJDBC.changeSettingToFalse(storename);
		lblOpenStatus.setText("休息中");
	}
	/**
	 * 設定店家要接單
	 */
	@FXML
	public void takeOrder() {
		MySQLJDBC.changeSettingToTrue(storename);
		lblOpenStatus.setText("營業中");
	}
	
	/**
	 * 店家設定折扣
	 */
	@FXML
	public void setDiscount() {
		lblsetdiscountstate.setText("已完成設定");
		int F = Integer.parseInt(full.getText());
		int D = Integer.parseInt(discount.getText());
		MySQLJDBC.changeFull(F, storename);
		MySQLJDBC.changeDiscount(D, storename);
	}
	//切換到主頁面
	public void switchToMain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("登入");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void ownerCheckOrderHistory() {
		String information = MySQLJDBC.findHistoryOwner(storename);
		lblinfo.setFont(new Font(14));
		lblinfo.setText(information);
		
	}

	
	

}
