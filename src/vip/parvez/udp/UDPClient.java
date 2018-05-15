package vip.parvez.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPClient implements Runnable {
    private int port;

    public UDPClient(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        InetAddress IP = null;
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
            IP = InetAddress.getByName("127.0.0.1");
            System.out.print("Client Start:");
            while (true) {
                byte[] sendBuffer = new byte[1024];
                byte[] receivedBuffer = new byte[1024];

                System.out.print("\nClient: ");
                BufferedReader clientRead = new BufferedReader(new InputStreamReader(System.in));
                String clientData = clientRead.readLine();

                sendBuffer = clientData.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendBuffer, sendBuffer.length, IP, port);
                clientSocket.send(sendPacket);

                if (clientData.equalsIgnoreCase("bye")) {
                    System.out.println("Connection ended by client");
                    break;
                }


                DatagramPacket receivePacket =
                        new DatagramPacket(receivedBuffer, receivedBuffer.length);
                clientSocket.receive(receivePacket);
                String serverData = new String(receivePacket.getData());
                System.out.print("\nServer: " + serverData);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }


    }
}
