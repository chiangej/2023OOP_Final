package application;

import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.stage.Stage;
import model.*;


/**
 * @author User
 *
 */
public class SwitchSceneController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	public static String StoreName; //店家
	public static String configuration;//訂餐物品
	public static String address; // 地址
	public static String userDeliveryState;
	@FXML
	private TextField txtRestaurant;
	@FXML
	private TextField txtTypeRest;
	@FXML
	private TextField txtDish;
	@FXML
	private TextField txtAddress;
	/**
	 * 在使用者頁面顯示店家是否營業
	 */
	@FXML
	private Label Error;
	/**
	 * 顯示店家資訊
	 */
	
	@FXML
	private Label menu;
	/**
	 * 顯示店家菜單
	 */
	@FXML
	private Label menu1;
	/**
	 * 顯示店家資訊
	 */
	@FXML
	private Label menu2;
	/**
	 *顯示點餐金額 
	 */
	@FXML
	private Label menu3;
	/**
	 * 顯示外送員的資訊、歷史訂單、有無訂單、訂單是否送達、訂單完成
	 */
	@FXML
	private Label deliverymenu;
	/**
	 * 顯示使用者個人資料
	 */
	@FXML
	private Label userinfo;
	/**
	 * 顯示使用者外送的狀態
	 */
	@FXML
	private Label userdeliverystate;
	/**
	 * 顯示使用者的歷史訂單
	 */
	@FXML
	private Label userCheckHistory;
	/**
	 * 依餐廳類別顯示餐廳名稱
	 */
	@FXML
	private Label lblresttype;
	/**
	 * 在type介面上顯示餐廳狀態
	 */
	@FXML
	private Label lbltypestate;
	/**
	 * 顯示外送員上班狀態
	 */
	@FXML
	private Label lblboyworkstate;
	
    /**
     * 顯示有無地址輸入
     */
    @FXML
    private Label addressstate;
	   
	@FXML
	private Button japanese;
	@FXML
	private Button luchbox;
	@FXML
	private Button dessert;
	@FXML
	private Button drink;
	@FXML
	private Button noodle;
	@FXML
	private Button chinese;
	@FXML
	private Button snack;
	


	/**
	 * @param event
	 * trigger button 到使用者登入頁面
	 * @throws IOException
	 */
	public void switchToUser(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("UserSignIn");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param event
	 * trigger button 到店家登入頁面
	 * @throws IOException
	 */
	public void switchToOwner(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("OwnerLogin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("店家頁面");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param event
	 * trigger button 到外送員登入頁面
	 * @throws IOException
	 */
	public void switchToDelivery(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("DeliveryBoyLogin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("外送員頁面");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param event
	 * trigger button 到主頁面
	 * @throws IOException
	 */
	public void switchToMain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("登入");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param event
	 * trigger button 到使用者申請帳號頁面
	 * @throws IOException
	 */
	public void switchToUserSignUp(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("UserSignUp.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("UserSignUp");
		stage.setScene(scene);
		stage.show();
	}


	/**
	 * @param event
	 * trigger button 從名稱搜尋到點餐頁面
	 * @throws IOException
	 */
	public void switchToOrderFinal(ActionEvent event) throws IOException {
		String storeName = txtRestaurant.getText();
		Owner owner = MySQLJDBC.findOwnerInName(storeName);
		StoreName = txtRestaurant.getText();
		if (owner.getPhone() != "-1" ) {
			boolean setting = MySQLJDBC.checkSetting(storeName);
			if(setting && MySQLJDBC.UseStoreToCheckDeliveryBoyWorkState(storeName).equals("True")&&MySQLJDBC.checkArrivalState(MySQLJDBC.UseStoreToFindDeliveryboy(storeName)).equals("沒事")) {
				root = FXMLLoader.load(getClass().getResource("OrderFinal.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setTitle("點餐頁面");
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}else {
				Error.setTextFill(Color.RED);
				Error.setText("餐廳未營業或外送員休息、送餐中");
			}
		} else {
			Error.setText("查無此餐廳!");
		}

	}

	/**
	 * @param event
	 * trigger button 從類別搜尋到點餐頁面
	 * @throws IOException
	 */
	public void switchToOrderFinalFromtype(ActionEvent event) throws IOException {
		String storeName = txtTypeRest.getText();
		Owner owner = MySQLJDBC.findOwnerInName(storeName);
		StoreName = txtTypeRest.getText();
		if (owner.getPhone() != "-1" ) {
			boolean setting = MySQLJDBC.checkSetting(storeName);
			if(setting&&MySQLJDBC.UseStoreToCheckDeliveryBoyWorkState(storeName).equals("True")) {
			root = FXMLLoader.load(getClass().getResource("OrderFinal.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("點餐頁面");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}else {
			lbltypestate.setFont(new Font(15));
			lbltypestate.setTextFill(Color.RED);
			lbltypestate.setText("餐廳未營業或外送員休息中");
			}
		} else {
			lbltypestate.setText("查無此餐廳!");
		}

	}

	/**
	 * @param event
	 * trigger button 從確認頁面回到點餐頁面
	 * @throws IOException
	 */
	public void switchToOrderFinal1(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("OrderFinal.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("點餐頁面");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param event
	 * trigger button 到使用者頁面
	 * @throws IOException
	 */
	public void switchToOrderSearch(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Search.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("使用者頁面");
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * @param event
	 * trigger button 到確認頁面
	 * @throws IOException
	 */
	public void switchToCheck(ActionEvent event) throws IOException {
		configuration = OrderFinalController.orderitem;
		root = FXMLLoader.load(getClass().getResource("Check.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("確認頁面");
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * @param event
	 * trigger button 到訂單配送頁面
	 * @throws IOException
	 */
	public void switchToRegister(ActionEvent event)  {
		address = txtAddress.getText();
		if (address.equals("")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("警告!!!!!!");
			alert.setHeaderText("敢不敢輸入你的地址");
			alert.setContentText("趕緊的!");
			if (alert.showAndWait().get() == ButtonType.OK) {
				
			}
		
		}
		else if (address.equals("")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("警告!!!!");
			alert.setHeaderText("敢不敢輸入你的地址");
			alert.setContentText("趕緊的!");
			if (alert.showAndWait().get() == ButtonType.OK) {}
		
		}
		
		try{
			;
			root = FXMLLoader.load(getClass().getResource("Register.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("訂單配送頁面");
			stage.setScene(scene);
			stage.show();	
		}
		catch (Exception e){
			addressstate.setText("請輸入地址!");
		}

	}

	//找菜單
	@FXML
	public void japanese() {
		
		lblresttype.setText("雞の專家_劍潭\n台灣大咖哩(雙城)");
	}
	@FXML
	public void luchbox() {
		lblresttype.setText("龍記燒臘-南港店\n鴨寶精緻便當\n阿琴自助餐\n腿庫*豬腳便當\n珍蜜便當2020更新\n樸食\n東湖頂尖排骨大王\n大安站那邊\n壹森泰食堂\nMiss Energy 低GI廚房 台北");
	}
	@FXML
	public void dessert() {
		lblresttype.setText("盛味豐炭烤燒餅\nUFO車輪餅\n湯圓妹現烤甜甜圈 八德店\nUncle L2 手作舒芙蕾甜點店\n邱太太");
	}
	@FXML
	public void snack() {
		lblresttype.setText("雞の專家_劍潭\n緯大雞排（內湖店）Gver.\n萊客潤餅捲(內湖)\n老蔡水煎包-復興店\n真味肉圓 (台北新明店)\nGABA 元気の源！嘎吧！");
		
	}
	@FXML
	public void noodle() {
		lblresttype.setText("春來麵線\n古早味涼麵&雞魯飯");
	}
	@FXML
	public void chinese() {
		lblresttype.setText("雞の專家_劍潭\n煲柏思維\n緯大雞排（內湖店）Gver.\n邱太太\n老蔡水煎包-復興店\n廣大順\n真味肉圓 (台北新明店)\n珍蜜便當2020更新");
	}
	@FXML
	public void drink() {
		lblresttype.setText("古早味涼麵&雞魯飯\n台灣第一味(台北松山店)\njuice-detour");
	}
	
	/**
	 * 在外送員介面顯示外送員資訊
	 */
	@FXML
	public void showBoyInfo() {
		System.out.println(LoginController.deliveryboyname);
		String information1 = MySQLJDBC.findDeliveryBoyInformation(LoginController.deliveryboyname);
		deliverymenu.setFont(new Font(14.5));
		deliverymenu.setAlignment(Pos.TOP_LEFT);
		deliverymenu.setText(information1);
	}

	/**
	 * 在外送員介面顯式歷史訂單
	 */
	@FXML
	public void showBoyHistory() {
		String information = MySQLJDBC.findHistoryDeliveryBoy(LoginController.deliveryboyname);
		System.out.println(information);
		deliverymenu.setFont(new Font(9));
		deliverymenu.setAlignment(Pos.TOP_LEFT);
		deliverymenu.setText(information);
	}

	/**
	 * 在外送員介面查看有無訂單
	 */
	@FXML
	public void BoyCheckOrder() {
		String info = "";
		deliverymenu.setFont(new Font(16));
		if(MySQLJDBC.checkArrivalState(LoginController.deliveryboyname).equals("否")) {
			info = MySQLJDBC.checkHistoryFinish(LoginController.deliveryboyname);
			
			deliverymenu.setAlignment(Pos.TOP_LEFT);
			deliverymenu.setText(info);
		}
		else{
			deliverymenu.setText("尚未有訂單");
		}
	}	
	/**
	 * 在使用者介面查看歷史訂單
	 */
	@FXML
	public void userCheckOrderHistory() {
		//userCheckHistory
		String information = MySQLJDBC.findHistoryUser(LoginController.username);
		userCheckHistory.setFont(new Font(9));
		userCheckHistory.setText(information);
		
	}	
	/**
	 * 在使用者介面顯示訂單資訊
	 */
	@FXML
	public void userCheckOrderState() {
		if(MySQLJDBC.checkArrivalState(MySQLJDBC.UseStoreToFindDeliveryboy(StoreName)).equals("否")) {
			userdeliverystate.setText("您的餐點配送中");
		}else {
			userdeliverystate.setText("無正在配送中的餐點");
		}
	}	
	/**
	 * 顯示使用者資訊
	 */
	@FXML
	public void showUserInfo() {
		userinfo.setText(MySQLJDBC.findUserInformation(LoginController.username));
	}
	/**
	 * 在外送員介面顯示外送員上班狀態
	 */
	@FXML
	public void boytakeNOorder() {
		MySQLJDBC.changeWorkStateToFalse(LoginController.deliveryboyname);
		lblboyworkstate.setText("休息中");
	}
	/**
	 * 在外送員介面顯示外送員上班狀態
	 */
	@FXML
	public void boytakeOrder() {
		MySQLJDBC.changeWorkStateToTrue(LoginController.deliveryboyname);
		lblboyworkstate.setText("上班中");
	}
	
	/**
	 * 在外送員介面設定訂單送達
	 */
	@FXML
	public void deliveryArrival() {
		deliverymenu.setFont(new Font(16));
		deliverymenu.setText("已送達");
		MySQLJDBC.changearrivalToTrue(LoginController.deliveryboyname);
		MySQLJDBC.changeHistoryFinishToTrue(LoginController.deliveryboyname);
	}
	/**
	 * 在外送員介面設定訂單完成
	 */
	@FXML
	public void orderFinish() {
		deliverymenu.setFont(new Font(16));
		deliverymenu.setText("完成訂單");
		MySQLJDBC.changearrivalToNothing(LoginController.deliveryboyname);
	}
	
}
