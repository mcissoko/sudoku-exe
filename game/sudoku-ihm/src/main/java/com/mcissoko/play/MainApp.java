package com.mcissoko.play;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcissoko.play.exception.ApplicationExceptionHandler;
import com.mcissoko.play.view.RootLayoutController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    private AnchorPane loginLayout;
        
    /**
     * The data as an observable list of Persons.
     */
    Logger log = LoggerFactory.getLogger(MainApp.class);    

    /**
     * Constructor
     */
    public MainApp() {
    	System.setProperty("log4j.configurationFile", "../config/log4j2.xml");
    	log = LoggerFactory.getLogger(MainApp.class);
    	log.info("============================================");
    	
    	
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	Thread.setDefaultUncaughtExceptionHandler(ApplicationExceptionHandler::showError);
    	
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Signal Manager");
        
        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/1485291533_Address_Book.png"));


        initRootLayout();
        
    }

    public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			this.primaryStage.setTitle("Sudoku");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			loginLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(loginLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			controller.initGrille();
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
	public static void main(String[] args) {
        launch(args);
    }
}