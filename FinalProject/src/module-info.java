module FinalProject {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	requires javafx.web;
	
	opens application to javafx.graphics, javafx.fxml;
}
