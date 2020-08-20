package laeneBeregner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Application {
    DataInputStream fromServer = null;
    DataOutputStream toServer = null;
    String host = "localhost";
    TextField interest = new TextField();
    TextField years = new TextField();
    TextField amount = new TextField();

    TextArea textArea = new TextArea();
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane paneInterest = new BorderPane();
        paneInterest.setPadding(new Insets(5,5,5,5));
        paneInterest.setLeft(new Label("Enter Interest"));

        interest.setAlignment(Pos.TOP_RIGHT);
        paneInterest.setCenter(interest);

        BorderPane paneYears = new BorderPane();
        paneYears.setPadding(new Insets(5,5,5,5));
        paneYears.setLeft(new Label("Enter years"));

        years.setAlignment(Pos.BASELINE_RIGHT);
        paneYears.setCenter(years);

        BorderPane paneAmount = new BorderPane();
        paneAmount.setPadding(new Insets(5,5,5,5));
        paneAmount.setLeft(new Label("Enter amount"));

        amount.setAlignment(Pos.BOTTOM_RIGHT);
        paneAmount.setCenter(amount);

        Button button = new Button("Submit");

        VBox vBox = new VBox();
        vBox.getChildren().add(paneInterest);
        vBox.getChildren().add(paneYears);
        vBox.getChildren().add(paneAmount);
        vBox.getChildren().add(button);


        BorderPane mainPane = new BorderPane();

        mainPane.setCenter(new ScrollPane(textArea));
        mainPane.setTop(vBox);

        Scene scene = new Scene(mainPane, 450, 200);
        stage.setTitle("client");
        stage.setScene(scene);
        stage.show();


        button.setOnAction(new ButtonListener());


    }
    private class ButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                Socket socket = new Socket(host, 8000);

                 fromServer = new DataInputStream(socket.getInputStream());
                 toServer = new DataOutputStream(socket.getOutputStream());

                double inte = Double.parseDouble(interest.getText());
                int y = Integer.parseInt(years.getText());
                double am = Double.parseDouble(amount.getText());

                toServer.writeDouble(inte);
                toServer.writeInt(y);
                toServer.writeDouble(am);
                toServer.flush();

                double monthly = fromServer.readDouble();
                double total = fromServer.readDouble();

                textArea.appendText("annual interest: "  + inte + "\n");
                textArea.appendText("Number of years: " + y + "\n");
                textArea.appendText("Loan amount: " + am + "\n");
                textArea.appendText("Monthly payments: " + monthly + "\n");
                textArea.appendText("Total payments: " + total + "\n");


            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
