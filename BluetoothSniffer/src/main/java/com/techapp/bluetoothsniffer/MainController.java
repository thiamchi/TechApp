package com.techapp.bluetoothsniffer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * FXML Controller class
 *
 * @author Tianzhi
 */
public class MainController implements Initializable {
    private static final Logger logger = LogManager.getLogger();
    
    @FXML
    private Label lbBluetooth;
    
    @FXML
    private void handleConnectAction(ActionEvent event){
        lbBluetooth.setText("Connect!");
        logger.info("User clicked Connect!");
    }
    
    @FXML
    private void handleDisconnectAction(ActionEvent event){
        lbBluetooth.setText("Disconnect!");
        logger.info("User clicked Disconnect!");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
