package server_date;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Date;

public class ServerDate {
    public static void main(String[] args) {
        try (var listener = new ServerSocket(9990)) {
            System.out.println("The server is running");
            while (true){
                try (var socket = listener.accept()){
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
