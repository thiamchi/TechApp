package com.techapp.bluetoothsniffer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.bluetooth.LocalDevice;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.RemoteDevice;

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
    private Button btConnect;

    @FXML
    private Button btDisconnect;

    @FXML
    private Button btTest;

    @FXML
    private TextArea tfDebug;

    @FXML
    void handleConnectAction(ActionEvent event) {
        lbBluetooth.setText("Connect!");
        logger.info("User clicked Connect!");
    }

    @FXML
    void handleDisconnectAction(ActionEvent event) {
        lbBluetooth.setText("Disconnect!");
        logger.info("User clicked Disconnect!");
    }
    
    private String str1;
    private String str2;
    @FXML
    void handleTestAction(ActionEvent event) throws IOException {
        try{
        LocalDevice device = LocalDevice.getLocalDevice();
        RemoteDevice[] remotedevice = device.getDiscoveryAgent().retrieveDevices(DiscoveryAgent.PREKNOWN);
        for(RemoteDevice d : remotedevice)
        {
            str1 = "Device name : " + d.getFriendlyName(false);
            str2 = "Bluetooth Address : " + d.getBluetoothAddress() + "\n";
            logger.info(str1);
            logger.info(str2);
        }
        }
        catch (Exception e){
            System.out.print(e);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
