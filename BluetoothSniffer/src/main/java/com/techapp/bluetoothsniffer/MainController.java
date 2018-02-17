package com.techapp.bluetoothsniffer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javax.bluetooth.BluetoothStateException;

import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * FXML Controller class
 *
 * @author Tianzhi
 */
public class MainController implements Initializable, DiscoveryListener {
    private static final Logger logger = LogManager.getLogger();
    private static final Object lock = new Object();
    LocalDevice device;
    public ArrayList<RemoteDevice> remotedevices;
    private String str1;
    private String str2;
    
    public MainController(){
        
    }
    
    @FXML
    private Label lbBluetooth;

    @FXML
    private Button btConnect;

    @FXML
    private Button btDisconnect;

    @FXML
    private Button btTest;
    
    @FXML
    private Button btTest2;

    @FXML
    private Button btTest3;
    
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
    
    @FXML
    void handleTestAction(ActionEvent event) throws IOException {
        /*try{
        
            for(RemoteDevice d : remotedevice)
            {
                str1 = "Device name : " + d.getFriendlyName(false);
                str2 = "Bluetooth Address : " + d.getBluetoothAddress();
                
                logger.info(str1);
                logger.info(str2);
            }
        } catch (IOException e){
            logger.info("Exception : "+ e.toString());
        }*/
    }
    
    @FXML
    void handleTest2Action(ActionEvent event) throws BluetoothStateException, IOException {
        try {
            DiscoveryAgent agent = device.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, this);
            this.remotedevices.clear();
            try {
                synchronized(lock){
                    lock.wait();
                }
            }catch (InterruptedException e) {
                logger.info("Exception : "+ e.toString());
                return;
            }
            
            logger.info("Device Inquiry Completed");
            
            UUID[] uuidSet = new UUID[1];
            uuidSet[0] = new UUID(0x1105);
            
            int[] attrIDs = new int[] {
                0x0100 // Service name
            };
            for (RemoteDevice device : this.remotedevices) {
                agent.searchServices(attrIDs,uuidSet,device,this);
                try {
                    synchronized(lock){
                        lock.wait();
                    }
                }
                catch (InterruptedException e) {
                    logger.info("Exception : "+ e.toString());
                    return;
                }
                logger.info("Service search finished." + device.getFriendlyName(false));
            }
        } catch (Exception e) {
            logger.info("Exception : "+ e.toString());
        }
    }

    @FXML
    void handleTest3Action(ActionEvent event) {

    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { // TODO
        try {
            this.device = LocalDevice.getLocalDevice();
            logger.info("My Device name : " + device.getFriendlyName());
            logger.info("My Bluetooth Address : " + device.getBluetoothAddress());
        } catch (BluetoothStateException ex) {
            logger.info(MainController.class.getName() + " : " + ex);
        }
        this.remotedevices = (ArrayList<RemoteDevice>) new ArrayList();
        try {
            //this.remotedevice = device.getDiscoveryAgent().retrieveDevices(DiscoveryAgent.PREKNOWN);
        } catch (Exception e){
            logger.info("Exception : "+ e.toString());
        }
    }    

    @Override
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass dc) {
        String name, name2 = null;
        try {
            name = btDevice.getFriendlyName(false);
            name2 = btDevice.getBluetoothAddress();
        } catch (Exception e) {
            name = btDevice.getBluetoothAddress();
        }
        
        this.remotedevices.add(btDevice);
        logger.info("device found: " + name + " | " + name2);
    }

    @Override
    public void servicesDiscovered(int j, ServiceRecord[] servRecord) {
        for (int i = 0; i < servRecord.length; i++) {
            String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            if (url == null) {
                continue;
            }
            DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
            if (serviceName != null) {
                logger.info("service " + serviceName.getValue() + " found " + url);
                
                if(serviceName.getValue().equals("OBEX Object Push")){
                    //sendMessageToDevice(url);                
                }
            } else {
                logger.info("service found " + url);
            }
            
        }
        logger.info("Service discover complete..." + j);
    }

    @Override
    public void serviceSearchCompleted(int i, int i1) {
        synchronized (lock) {
            lock.notify();
        }
        logger.info("Service Search complete..." + i);
    }

    @Override
    public void inquiryCompleted(int i) {
        synchronized(lock){
            lock.notify();
        }
        logger.info("Inquiry complete..." + i);
    }
    
}
