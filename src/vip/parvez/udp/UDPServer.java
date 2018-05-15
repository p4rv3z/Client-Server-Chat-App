/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vip.parvez.udp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer implements Runnable {
    private int port;

    public UDPServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(port);
            System.out.print("Server Start");
            while (true) {
                byte[] receivedBuffer = new byte[1024];
                byte[] sendBuffer = new byte[1024];

                //received data from server
                DatagramPacket receivedDatagramPacket = new DatagramPacket(receivedBuffer, receivedBuffer.length);
                serverSocket.receive(receivedDatagramPacket);

                //get client data
                String clientData = new String(receivedDatagramPacket.getData());
                //print client message
                System.out.println("\nClient : " + clientData);

                //get message from server
                System.out.print("\nServer (Reply): ");

                BufferedReader serverRead = new BufferedReader(new InputStreamReader(System.in));
                String reply = serverRead.readLine();

                sendBuffer = reply.getBytes();

                //get client info
                InetAddress IP = receivedDatagramPacket.getAddress();
                int portNo = receivedDatagramPacket.getPort();

                //send message to client
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, IP, portNo);
                serverSocket.send(sendPacket);

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
                serverSocket.close();
            }
        }
    }
}

