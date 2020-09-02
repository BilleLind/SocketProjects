package raspberrypi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRasp {

    public static void main(String[] args) {
        String host ="192.168.43.251";
        int port = 45050;


        try {
            Socket socket = new Socket(host,port);

            DataOutputStream outGoing = new DataOutputStream(socket.getOutputStream());
            DataInputStream inComing = new DataInputStream(socket.getInputStream());

            for (int i =0; i<=1000; i++) {
                outGoing.writeUTF("send");
                outGoing.flush();

                System.out.println(i + " message sent");
                String info = inComing.readUTF(); //readline() ?? why did they write it as a comment?

                System.out.println("Message" + info);

                Thread.sleep(30000);
            }
            outGoing.close();
            inComing.close();
            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }




}
