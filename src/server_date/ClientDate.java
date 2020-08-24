package server_date;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientDate {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("pass the server Ip as the sole command line argument");
            return;
        }
        try {
            var socket = new Socket(args[0], 9900);
            var in = new Scanner(socket.getInputStream());
            System.out.println("Server respons "+ in.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
