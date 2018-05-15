package vip.parvez.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class TCPClient implements Runnable {
    private int port;

    public TCPClient(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket = null;
        PrintStream printStream = null;
        BufferedReader clientRead = null;
        BufferedReader serverRead = null;
        try {
            socket = new Socket("localhost", port);
            System.out.println("Client Start");
            while (true) {
                System.out.println("Client: ");
                printStream = new PrintStream(socket.getOutputStream());
                clientRead = new BufferedReader(new InputStreamReader(System.in));
                String clientData = clientRead.readLine();
                //sending the massage to the server
                printStream.println(clientData);
                serverRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Server: "+serverRead.readLine());

                if (clientData.equalsIgnoreCase("bye")) {
                    System.out.println("Connection ended by client");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (printStream != null) {
                printStream.flush();
            }
            if (clientRead != null) {
                try {
                    clientRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverRead != null) {
                try {
                    serverRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
