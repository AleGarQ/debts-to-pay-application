package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class DebtsToPayGUI {
	  @FXML
	  private BorderPane mainPane;
	  @FXML
	  private PasswordField passwSI;
	  @FXML
	  private TextField usernameSI;
	  @FXML
	  private ComboBox<String> showBills;
	  @FXML
	  private ComboBox<String> sortBills;
	  @FXML
	  private ComboBox<String> showPayments;
	  @FXML
	  private ComboBox<String> sortPayments;
	  @FXML
	  private ComboBox<String> showProviders;
	  @FXML
	  private ComboBox<String> sortProviders;

	  @FXML
	  void signInScreen(ActionEvent event) throws IOException {
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
		  
		  fxmlLoader.setController(this);    	
		  Parent signInPane = fxmlLoader.load();
		  
		  mainPane.setCenter(signInPane);
	  }
	  
	  @FXML
	  void signUpScreen(ActionEvent event) throws IOException {
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
		  
		  fxmlLoader.setController(this);    	
		  Parent signUpPane = fxmlLoader.load();
		  
		  mainPane.setCenter(signUpPane);
	  }

	  @FXML
	  void backToWelcomeScreen(ActionEvent event) throws IOException {
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
		  
		  fxmlLoader.setController(this);    	
		  Parent welcomePane = fxmlLoader.load();
		  
		  mainPane.getChildren().clear();
		  mainPane.setCenter(welcomePane);
	  }

	  @FXML
	  void signIn(ActionEvent event) throws IOException {
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		  
		  fxmlLoader.setController(this);    	
		  Parent menuPane = fxmlLoader.load();
		  
		  initializeComboBoxLists();
		  
		  mainPane.getChildren().clear();
		  mainPane.setCenter(menuPane);
	  }
	  
	  @FXML
	  void signUp(ActionEvent event) throws IOException {
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		  
		  fxmlLoader.setController(this);    	
		  Parent menuPane = fxmlLoader.load();
		  
		  initializeComboBoxLists();
		  
		  mainPane.getChildren().clear();
		  mainPane.setCenter(menuPane);
	  }
	  
	  @FXML
	  void startApplication(MouseEvent event) throws IOException {
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
		  
		  fxmlLoader.setController(this);    	
		  Parent welcomePane = fxmlLoader.load();
		  
		  mainPane.getChildren().clear();
		  mainPane.setCenter(welcomePane);
	  }
	  
	  public void initializeComboBoxLists() {
		  ObservableList<String> showBillList = FXCollections.observableArrayList();
		  ObservableList<String> sortBillList = FXCollections.observableArrayList();
		  ObservableList<String> showPayList = FXCollections.observableArrayList();
		  ObservableList<String> sortPayList = FXCollections.observableArrayList();
		  ObservableList<String> showProvList = FXCollections.observableArrayList();
		  ObservableList<String> sortProvList = FXCollections.observableArrayList();
		  
		  showBillList.removeAll(showBillList);
		  String all = "All";
		  String paid = "Paid";
		  String toPay = "To Pay";
		  String days30 = "Expires in 30 days";
		  String days60 = "Expires in 60 days";
		  showBillList.addAll(all, paid, toPay, days30, days60);
		  showBills.getItems().addAll(showBillList);
		  
		  sortBillList.removeAll(sortBillList);
		  String bussi = "Bussines";
		  String paidV = "Paid Value";
		  String vToPay = "Value To Pay";
		  String conc = "Concept";
		  sortBillList.addAll(bussi, paidV, vToPay, conc);
		  sortBills.getItems().addAll(sortBillList);
		  
		  showPayList.removeAll(showPayList);
		  String days30P = "Past 30 days";
		  String days60P = "Past 60 days";
		  showPayList.addAll(all, days30P, days60P);
		  showPayments.getItems().addAll(showPayList);
		  
		  sortPayList.removeAll(sortPayList);
		  String value = "Value";
		  sortPayList.addAll(bussi, value, conc);
		  sortPayments.getItems().addAll(sortPayList);
		  
		  showProvList.removeAll(showProvList);
		  String act = "Actives";
		  String inact = "Inactives";
		  String wBill = "Without Bill";
		  showProvList.addAll(all, act, inact, wBill);
		  showProviders.getItems().addAll(showProvList);
		  
		  sortProvList.removeAll(sortProvList);
		  String bussiN = "Bussines Name";
		  String interN = "Intermediary Name";
		  sortProvList.addAll(bussiN, interN);
		  sortProviders.getItems().addAll(sortProvList);
	  }
}
