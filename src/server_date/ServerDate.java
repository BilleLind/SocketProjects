package server_date;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerDate {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        System.out.println("server running");
        try{
            serverSocket = new ServerSocket(9900);
            System.out.println("server running still");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("server running works?");

                var out = new PrintWriter(socket.getOutputStream(), true);
                out.println(new Date().toString());
                System.out.println("server running works");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
