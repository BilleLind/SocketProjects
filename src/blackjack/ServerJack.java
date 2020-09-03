package blackjack;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerJack extends Application implements  BlackJackConstants{
    private int sessionNo = 1;

    @Override
    public void start(Stage stage) throws Exception {
        TextArea textArea = new TextArea();

        Scene scene = new Scene(new ScrollPane(textArea), 450, 200);
        stage.setTitle("BlackJackServer");
        stage.setScene(scene);
        stage.show();

        new Thread( () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(45050);
                Platform.runLater(() -> textArea.appendText(new Date() + ": server started at socket 45050\n"));

                while (true) {
                    Platform.runLater(() -> textArea.appendText(new Date() + ": Wait for players to join session "+ sessionNo +"\n"));
                    Socket player1 = serverSocket.accept();

                    Platform.runLater(()-> {
                        textArea.appendText(new Date() + ": Player 1 joined session"
                                + sessionNo + "\n");
                        textArea.appendText("Player 1's IP adress"+
                                player1.getInetAddress().getHostAddress()+ "\n");
                    });

                    //message to player that he/she is player1
                    new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);

                    //connect to player2
                    Socket player2 = serverSocket.accept();

                    Platform.runLater(() -> {
                        textArea.appendText(new Date() + ": Player 2 joined session " + sessionNo +'\n');
                        textArea.appendText("Player 2's IP adress" +
                                player2.getInetAddress().getHostAddress() +'\n');
                    });

                    //message to the new player that he/she is player 2
                    new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);

                    // display session and increment session number for the next pair of players
                    Platform.runLater(() -> textArea.appendText(new Date() + ":start a thread for session " + sessionNo++ + '\n'));


                    //launch a new thread for this session of two players
                    new Thread(new HandleASession(player1, player2)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //definition of th e thread class for handling a new session for two players
    class HandleASession implements Runnable, BlackJackConstants {
        private Socket player1;
        private Socket player2;

        //TODO more stuff down here / continue from here

        @Override
        public void run() {

        }


    }

}
