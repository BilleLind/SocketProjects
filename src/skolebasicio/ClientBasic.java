package skolebasicio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBasic {

    public static void main(String[] args) {
        int port = 9900;
        String host = "192.168.43.251";
        DataInputStream in;
        DataOutputStream out;
        Socket socket;

        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.writeDouble(85);
            System.out.println(in.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
