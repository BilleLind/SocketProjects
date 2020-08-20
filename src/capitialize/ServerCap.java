package capitialize;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCap {


    public static void main(String[] args) {
        int port = 9999;
        DataInputStream in;
        DataOutputStream out;
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(port);
          socket = serverSocket.accept();

              in = new DataInputStream(socket.getInputStream());
              out = new DataOutputStream(socket.getOutputStream());


              System.out.println(in.readUTF());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
