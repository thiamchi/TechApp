package com.techapp.samplegui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FXMLController implements Initializable {
    private static final Logger logger = LogManager.getLogger();
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        logger.info("Hi from the logger log4j2!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
