package vip.parvez.tcp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class TCPServer implements Runnable {
    private int port;

    public TCPServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader clientRead = null, serverRead = null;
        PrintStream printStream = null;
        try {
            System.out.println("Server Start");
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            while (true) {
                clientRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Client: " + clientRead.readLine());

                System.out.println("Server:");
                serverRead = new BufferedReader(new InputStreamReader(System.in));
                String reply = serverRead.readLine();

                printStream = new PrintStream(socket.getOutputStream());
                printStream.println(reply);

                //check condition and bye for end connection
                if (reply.equalsIgnoreCase("bye")) {
                    System.out.println("Connection ended by server");
                    break;
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
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

