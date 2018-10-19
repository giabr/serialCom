package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jssc.SerialPort;
import jssc.SerialPortException;

public class Controller{

    //Kolom input message
    @FXML
    private TextField inputData;

    //Kolom view message
    @FXML
    private TextArea view;

    //Initialize serial port
    SerialPort serialPort = new SerialPort("COM3");

    String loop = "";

    //Input message and send to port
    @FXML
    void input(ActionEvent event) {
        try {
            String userInput = String.valueOf(inputData.getText()) + "\n";
            serialPort.writeBytes(userInput.getBytes());
            loop += userInput;
            view.setText(loop + "\n");
        } catch (SerialPortException e) {
            e.printStackTrace();
            view.setText(String.valueOf(e));
        };
        }

    @FXML
    void add(ActionEvent event) {
        try {
            String userInput = String.valueOf(inputData.getText()) + "\n";
            serialPort.writeBytes(userInput.getBytes());
            loop += userInput;
            view.setText(loop + "\n");
        } catch (SerialPortException e) {
            e.printStackTrace();
            view.setText(String.valueOf(e));
        };
    }

    //Receive message from port
    @FXML
    void receive(ActionEvent event) {
        try {
            byte[] b = serialPort.readBytes();
            String receiveData = new String(b);
            loop += receiveData;
            view.setText(loop + "\n");
        } catch (SerialPortException e) {
            e.printStackTrace();
            view.setText(String.valueOf(e));
        }
    }

    //Connect to port
    @FXML
    void connect(ActionEvent event) {
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
//            if (serialPort.isOpened()){
//                loop="Port Opened" + "\n";
//                view.setText(loop);
//            }
            if (serialPort.isOpened()){
                loop="Port Opened" + "\n";
                view.setText(loop);
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    //Disconnect from port
    @FXML
    void disconnect(ActionEvent event) {
        try {
            serialPort.closePort();
            if (!serialPort.isOpened()){
                loop="Port Closed" + "\n";
                view.setText(loop);
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
