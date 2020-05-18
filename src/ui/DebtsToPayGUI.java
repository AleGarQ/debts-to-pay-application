package ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import exceptions.BillAlreadyOnListException;
import exceptions.ProviderAlreadyOnListException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Account;
import model.Bill;
import model.Controller;
import model.Provider;

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
	private TextField bankName;
	@FXML
	private TextField accountNumber;
	
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
    @FXML
    private TableView<Bill> paymentsTable;
    @FXML
    private TableColumn<Bill, String> conceptColumn;
    @FXML
    private TableColumn<Bill, String> valuePaidColumn;

//  Show Providers----------------------------------------------
	@FXML
	private ComboBox<String> showProviders;
	@FXML
	private ComboBox<String> sortProviders;
	@FXML
    private CheckBox screenBoxProv;
    @FXML
    private CheckBox textFileBoxProv;
    @FXML
    private TableView<Provider> providersTable;
    @FXML
    private TableColumn<Provider, String> businessColumn;
    @FXML
    private TableColumn<Provider, String> nitColumn;
    @FXML
    private TableColumn<Provider, String> bankColumn;
    @FXML
    private TableColumn<Provider, String> accountColumn;
    @FXML
    private TableColumn<Provider, String> intermediaryColumn;
    @FXML
    private TableColumn<Provider, String> phoneColumn;
    @FXML
    private TableColumn<Provider, String> stateColumn;
    
	
	public DebtsToPayGUI() throws BillAlreadyOnListException {
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
		if(!usernameSU.getText().equals("") && !passwSU.getText().equals("") && !emailSU.getText().equals("") && !nameSU.getText().equals("") && !lastnameSU.getText().equals("")) {
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
		if(!bussinesN.getText().equals("") && !intermediaryN.getText().equals("") && !intermediaryNumb.getText().equals("") && !nit.getText().equals("") && !bankName.getText().equals("") && !accountNumber.getText().equals("")) {
			try {
				control.addProvider(bankName.getText(), accountNumber.getText(), bussinesN.getText(), nit.getText(), intermediaryN.getText(), intermediaryNumb.getText());
				bussinesN.setText("");
				intermediaryN.setText("");
				intermediaryNumb.setText("");
				nit.setText("");
				bankName.setText("");
				accountNumber.setText("");
				Alert usernameNotFound = new Alert(AlertType.INFORMATION);
				usernameNotFound.setTitle("PROVIDER REGISTERED SUCCESSFULY");
				usernameNotFound.setHeaderText("The provider now is in our system");
				usernameNotFound.setContentText("To register a bill to this intermediary go to \"Register new bill\" tab");
				usernameNotFound.showAndWait();
			} catch (ProviderAlreadyOnListException e) {
				Alert usernameNotFound = new Alert(AlertType.WARNING);
				usernameNotFound.setTitle("PROVIDER ALREADY REGISTERED");
				usernameNotFound.setHeaderText(e.getMessage());
				usernameNotFound.setContentText("Please make sure it is right");
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
	void showBills(ActionEvent event) {
		
	}
	
	@FXML
	void showPayments(ActionEvent event) throws IOException {
		if (showPayments.getValue() != null && sortPayments.getValue() != null && (screenBoxPay.isSelected() || textFileBoxPay.isSelected())) {
			if (sortPayments.getValue().equals("Value")) {
				control.insertionSortPaymentsByValue();
				optionsToPrintPayments();
			}else {
//				control.sortPaymentsByConcept();
				optionsToPrintPayments();
			}
		}else {
			Alert fields = new Alert(AlertType.WARNING);
			fields.setTitle("EMPTY FIELDS");
			fields.setHeaderText("Some fields are empty");
			fields.setContentText("Please fill them and try again");
			fields.showAndWait();
		}
	}
	
	public void optionsToPrintPayments() throws IOException {
		String paymentsInfo = "";
		if (showPayments.getValue().equals("All")) {
			if (screenBoxPay.isSelected()) {
				loadPaymentsList();
			}
			
			ArrayList<Bill> aux = (ArrayList<Bill>) control.getBills();
			for (int i = 0; i < aux.size(); i++) {
				paymentsInfo += aux.get(i).getConcept() + " |" + aux.get(i).getValuePaid() + "\n";
			}
		}
		
		if (textFileBoxPay.isSelected()) {
			PrintWriter writer = new PrintWriter("data/Payments Information.txt");
			
			writer.println("          PAYMENTS INFO          ");
			writer.println("---------------------------------");
			writer.println("---------------------------------");
			writer.println("Concept | Value Paid");

			writer.println(paymentsInfo);
			
			writer.close();
		}
	}
	
	public void loadPaymentsList() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentsTable.fxml"));
		fxmlLoader.setController(this);
		Parent paymentsList = fxmlLoader.load();
    	
		Scene scene = new Scene(paymentsList);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Payments List");
		stage.show();
    	initializePaymentsList();
    }
    
    public void initializePaymentsList() {
    	ObservableList<Bill> observableList;
    	observableList = FXCollections.observableArrayList(control.getBills());
    	
		paymentsTable.setItems(observableList);
	    conceptColumn.setCellValueFactory(new PropertyValueFactory<Bill, String>("concept"));
	    valuePaidColumn.setCellValueFactory(new PropertyValueFactory<Bill, String>("valuePaid"));
    }

	@FXML
	void showProviders(ActionEvent event) throws IOException {
		if (showProviders.getValue() != null && sortProviders.getValue() != null && (screenBoxProv.isSelected() || textFileBoxProv.isSelected())) {
			if (sortProviders.getValue().equals("Bussines Name")) {
				control.selectionSortProvidersByBussines();
				optionsToPrintProviders();
			}else {
				control.bubbleSortProvidersByIntermediary();
				optionsToPrintProviders();
			}
		}else {
			Alert fields = new Alert(AlertType.WARNING);
			fields.setTitle("EMPTY FIELDS");
			fields.setHeaderText("Some fields are empty");
			fields.setContentText("Please fill them and try again");
			fields.showAndWait();
		}
	}
	
	public void optionsToPrintProviders() throws IOException {
		String providersInfo = "";
		if (showProviders.getValue().equals("All")) {
			if (screenBoxProv.isSelected()) {
				loadProvidersList();
			}
			
			ArrayList<Provider> aux = (ArrayList<Provider>) control.getProviders();
			for (int i = 0; i < aux.size(); i++) {
				providersInfo += aux.get(i) + "\n";
			}
		}else if (showProviders.getValue().equals("Actives")) {
			if (screenBoxProv.isSelected()) {
				loadActiveProvidersList();
			}

			ArrayList<Provider> aux = (ArrayList<Provider>) control.getProviders();
			for (int i = 0; i < aux.size(); i++) {
				if (aux.get(i).isWorking()) {
					providersInfo += aux.get(i) + "\n";
				}
			}
		}else if (showProviders.getValue().equals("Inactives")) {
			if (screenBoxProv.isSelected()) {
				loadInactiveProvidersList();
			}
			
			ArrayList<Provider> aux = (ArrayList<Provider>) control.getProviders();
			for (int i = 0; i < aux.size(); i++) {
				if (!aux.get(i).isWorking()) {
					providersInfo += aux.get(i) + "\n";
				}
			}
		}else if (showProviders.getValue().equals("Without Bill")) {
			if (screenBoxProv.isSelected()) {
				loadWBProvidersList();
			}
			
			ArrayList<Provider> aux = (ArrayList<Provider>) control.getProviders();
			for (int i = 0; i < aux.size(); i++) {
				if (aux.get(i).getBills().isEmpty()) {
					providersInfo += aux.get(i) + "\n";
				}
			}
		}
		
		if (textFileBoxProv.isSelected()) {
			PrintWriter writer = new PrintWriter("data/Providers Information.txt");
			
			writer.println("          PROVIDERS INFO          ");
			writer.println("----------------------------------");
			writer.println("----------------------------------");
			writer.println("Business| NIT	| Bank	| Account #	| Intermediary	| Phone	| State");

			writer.println(providersInfo);
			
			writer.close();
		}
	}
	
	public void loadProvidersList() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProvidersTable.fxml"));
		fxmlLoader.setController(this);
		Parent providersList = fxmlLoader.load();
    	
		Scene scene = new Scene(providersList);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Providers List");
		stage.show();
    	initializeProvidersList();
    }
    
    public void initializeProvidersList() {
    	ObservableList<Provider> observableList;
    	observableList = FXCollections.observableArrayList(control.getProviders());
    	
		providersTable.setItems(observableList);
		businessColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("bussinessName"));
		nitColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("nit"));
		bankColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("bank"));
		accountColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("account"));
		intermediaryColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("intermediaryName"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("intermediaryPhone"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("working"));
    }
	
    public void loadActiveProvidersList() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProvidersTable.fxml"));
		fxmlLoader.setController(this);
		Parent providersList = fxmlLoader.load();
    	
		Scene scene = new Scene(providersList);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Providers List");
		stage.show();
    	initializeActiveProvidersList();
    }
    
    public void initializeActiveProvidersList() {
    	ObservableList<Provider> observableList;
    	observableList = FXCollections.observableArrayList(control.getActiveProviders());
    	
		providersTable.setItems(observableList);
		businessColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("bussinessName"));
		nitColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("nit"));
		bankColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("bank"));
		accountColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("account"));
		intermediaryColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("intermediaryName"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("intermediaryPhone"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("working"));
    }
    
    public void loadInactiveProvidersList() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProvidersTable.fxml"));
		fxmlLoader.setController(this);
		Parent providersList = fxmlLoader.load();
    	
		Scene scene = new Scene(providersList);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Providers List");
		stage.show();
    	initializeInactiveProvidersList();
    }
    
    public void initializeInactiveProvidersList() {
    	ObservableList<Provider> observableList;
    	observableList = FXCollections.observableArrayList(control.getInactiveProviders());
    	
		providersTable.setItems(observableList);
		businessColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("bussinessName"));
		nitColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("nit"));
		bankColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("bank"));
		accountColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("account"));
		intermediaryColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("intermediaryName"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("intermediaryPhone"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("working"));
    }
    
    public void loadWBProvidersList() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProvidersTable.fxml"));
		fxmlLoader.setController(this);
		Parent providersList = fxmlLoader.load();
    	
		Scene scene = new Scene(providersList);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Providers List");
		stage.show();
    	initializeWBProvidersList();
    }
    
    public void initializeWBProvidersList() {
    	ObservableList<Provider> observableList;
    	observableList = FXCollections.observableArrayList(control.getWBProviders());
    	
		providersTable.setItems(observableList);
		businessColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("bussinessName"));
		nitColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("nit"));
		bankColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("bank"));
		accountColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("account"));
		intermediaryColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("intermediaryName"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("intermediaryPhone"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<Provider, String>("working"));
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
		showPayList.addAll(all);
		showPayments.getItems().addAll(showPayList);

		sortPayList.removeAll(sortPayList);
		String value = "Value";
		sortPayList.addAll(value, conc);
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
