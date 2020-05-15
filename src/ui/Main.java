package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	private DebtsToPayGUI debtsGUI;
	
	public Main() {
		debtsGUI = new DebtsToPayGUI();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmll = new FXMLLoader(getClass().getResource("InitialScreen.fxml"));
		fxmll.setController(debtsGUI);
		Parent root = fxmll.load();
		root.getStylesheets().add(getClass().getResource("styleFirstScreen.css").toExternalForm());
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Debts To Pay Application");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
