package skolebasicio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBasic {


    public static void main(String[] args) {
        int port = 9900;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();

            while (true) {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                double yo = dataInputStream.readDouble();

                System.out.println(yo);
                dataOutputStream.writeDouble(yo*2.5);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
