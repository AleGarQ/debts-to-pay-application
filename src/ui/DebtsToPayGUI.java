package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Account;
import model.Controller;

public class DebtsToPayGUI {
	@FXML
	private Label nameOnScreen;
	@FXML
	private BorderPane mainPane;
	private Controller control;
	
//	Sign in screen
	@FXML
	private PasswordField passwSI;
	@FXML
	private TextField usernameSI;
	
//	Sign up screen
	@FXML
	private PasswordField passwSU;
	@FXML
	private TextField usernameSU;
	@FXML
	private TextField emailSU;
	@FXML
	private TextField lastnameSU;
	@FXML
	private TextField nameSU;
	
//	Register Provider----------------------------------
	@FXML
	private TextField bussinesN;
	@FXML
	private TextField intermediaryN;
	@FXML
	private TextField intermediaryNumb;
	@FXML
	private TextField nit;
	@FXML
	private TextField bankAcc;
	
//	Update Intermediary-------------------------------------
	@FXML
	private TextField intermediaryNameUpd;
	@FXML
	private TextField intermediaryPhoneUpd;
	@FXML
	private TextField providerNameUpd;

//	Register New Bill-------------------------------------
	@FXML
    private TextField valueBill;
    @FXML
    private TextField providerNameBill;
    @FXML
    private TextField limitPaymentDate;
    @FXML
    private TextField conceptBill;
    @FXML
    private TextField current;
    @FXML
    private TextField interestPercentage;
    @FXML
    private TextField paymentMethod;
    @FXML
    private TextField fees;

//	Register Payment--------------------------------------
    @FXML
    private TextField conceptPay;
    @FXML
    private TextField valuePay;
    @FXML
    private TextField providerNamePay;

//  Show Bills-----------------------------------------------
	@FXML
	private ComboBox<String> showBills;
	@FXML
	private ComboBox<String> sortBills;
	@FXML
    private CheckBox screenBoxBill;
    @FXML
    private CheckBox textFileBoxBill;
	
//	Show Payments---------------------------------------------
	@FXML
	private ComboBox<String> showPayments;
	@FXML
	private ComboBox<String> sortPayments;
	@FXML
    private CheckBox screenBoxPay;
    @FXML
    private CheckBox textFileBoxPay;

//  Show Providers----------------------------------------------
	@FXML
	private ComboBox<String> showProviders;
	@FXML
	private ComboBox<String> sortProviders;
	@FXML
    private CheckBox screenBoxProv;
    @FXML
    private CheckBox textFileBoxProv;
	
	public DebtsToPayGUI() {
		control = new Controller();
	}

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
		if(!usernameSI.getText().equals("") && !passwSI.getText().equals("")) {
			if(control.searchUsername(usernameSI.getText())) {
				Account user = control.findAccount(usernameSI.getText(), passwSI.getText());
				if(user != null) {
					loadMenuScreen();
					nameOnScreen.setText(user.getName() + " " + user.getLastname());
				}else {
					Alert wrongPassword = new Alert(AlertType.WARNING);
					wrongPassword.setTitle("WRONG PASSWORD");
					wrongPassword.setHeaderText("The password entered does not match the user");
					wrongPassword.setContentText("Please correct it");
					wrongPassword.showAndWait();
				}
			}else {
				Alert usernameNotFound = new Alert(AlertType.WARNING);
				usernameNotFound.setTitle("WRONG OR NOT REGISTERED USERNAME");
				usernameNotFound.setHeaderText("The username entered has not been found");
				usernameNotFound.setContentText("Please correct it or register");
				usernameNotFound.showAndWait();
			}
		}else {
			Alert fields = new Alert(AlertType.WARNING);
			fields.setTitle("EMPTY FIELDS");
			fields.setHeaderText("Some fields are empty");
			fields.setContentText("Please fill them and try again");
			fields.showAndWait();
		}
	}

	@FXML
	void signUp(ActionEvent event) throws IOException {
		if(!usernameSU.getText().equals("") 
				&& !passwSU.getText().equals("") 
				&& !emailSU.getText().equals("")
				&& !nameSU.getText().equals("") 
				&& !lastnameSU.getText().equals("")) {
			if(!control.searchUsername(usernameSU.getText())) {
					control.addAccount(nameSU.getText(), lastnameSU.getText(), emailSU.getText(), usernameSU.getText(), passwSU.getText());
					loadMenuScreen();
					nameOnScreen.setText(nameSU.getText() + " " + lastnameSU.getText());
			}else {
				Alert usernameNotFound = new Alert(AlertType.WARNING);
				usernameNotFound.setTitle("USERNAME ALREADY TAKEN");
				usernameNotFound.setHeaderText("The username entered is in use");
				usernameNotFound.setContentText("Please sign in with it or choose another");
				usernameNotFound.showAndWait();
			}
		}else {
			Alert fields = new Alert(AlertType.WARNING);
			fields.setTitle("EMPTY FIELDS");
			fields.setHeaderText("Some fields are empty");
			fields.setContentText("Please fill them and try again");
			fields.showAndWait();
		}
	}
	
	public void loadMenuScreen() throws IOException {
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
	
	@FXML
	void registerNewBill(ActionEvent event) {
		
	}
	
	@FXML
	void registerPayment(ActionEvent event) {
		
	}
	
	@FXML
	void registerProvider(ActionEvent event) {
		
	}
	
	@FXML
	void showBills(ActionEvent event) {
		
	}
	
	@FXML
	void showPayments(ActionEvent event) {
		
	}

	@FXML
	void showProviders(ActionEvent event) {
		
	}
	
	@FXML
	void updateIntermediary(ActionEvent event) {
		
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
