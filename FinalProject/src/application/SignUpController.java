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
import javafx.stage.Stage;
import model.*;;

/**
 * @author User
 *讓使用者申請帳號
 */
public class SignUpController {
	@FXML
	private Label label1;
	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtPassword;

	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtVip;

	private Stage stage;
	private Scene scene;
	private Parent root;

	// putswitchToUser
	public void SignUp(ActionEvent event) throws IOException {
		String name = txtUserName.getText();
		String password = txtPassword.getText();
		String vip = txtVip.getText();
		String email = txtEmail.getText();
		String phone = txtPhoneNumber.getText();
		User user = MySQLJDBC.findUser(email, password);
		if (user.getEmail().equals("-1") && user.getPassword().equals("-1")) {
			MySQLJDBC.insertUser(name, email, phone, vip, password);
			root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("UserSignIn");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			label1.setText("此帳號已有人使用");
		}

	}
}