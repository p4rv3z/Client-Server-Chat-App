package vip.parvez.helper;

import vip.parvez.tcp.TCPServer;
import vip.parvez.udp.UDPServer;

public class ServerHelper {
    public static final String UDP = "udp";
    public static final String TCP = "tcp";
    String connectionType;

    public ServerHelper(String connectionType) {
        this.connectionType = connectionType;
    }

    public void startServer() {
        //start UDP Server
        if (connectionType.equalsIgnoreCase(UDP)) {
            new UDPServer(3001).run();
        }
        //start TCP server
        else if (connectionType.equalsIgnoreCase(TCP)) {
            new TCPServer(3001).run();
        }
        //start default server
        else {

        }
    }
}
