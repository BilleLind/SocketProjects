package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server extends Application {

    DataInputStream inputFromClient;
    DataOutputStream toClient;
    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        TextArea textArea = new TextArea();
        Scene scene = new Scene(new ScrollPane(textArea), 450, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                textArea.appendText("Server started at " +  new Date()+ "\n");

                //serverSocket.accepts lytter efter forbindelser til serveren
                Socket socket = serverSocket.accept();

                inputFromClient = new DataInputStream(socket.getInputStream());
                toClient = new DataOutputStream(socket.getOutputStream());

                while (true){

                    double interest = inputFromClient.readDouble();
                    int year = inputFromClient.readInt();
                    double loanAmount = inputFromClient.readDouble();

                    Loan loan = new Loan(interest, year, loanAmount);
                    double monthlyPay = loan.getMonthlyPayment();
                    double totalPay = loan.getTotalPayment();

                    toClient.writeDouble(monthlyPay);
                    toClient.writeDouble(totalPay);

                    textArea.appendText("annual interest: "  + interest + "\n");
                    textArea.appendText("Number of years: " + year + "\n");
                    textArea.appendText("Loan amount: " + loanAmount + "\n");
                    textArea.appendText("Monthly payments: " + monthlyPay + "\n");
                    textArea.appendText("Total payments: " + totalPay + "\n");

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
