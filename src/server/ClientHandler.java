package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    private ArrayList<ClientHandler> clients;
    private Socket socket;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
        try {
            this.socket = socket;
            this.clients = clients;

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            String msg;
            while ((msg = dataInputStream.readUTF()) != null) {
                if (msg.equalsIgnoreCase( "exit")) {
                    break;
                }
                for (ClientHandler cl : clients) {
                    cl.dataOutputStream.writeUTF(msg);
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        finally {
            try {
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
    }

}
