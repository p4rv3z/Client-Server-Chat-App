package vip.parvez.helper;

import vip.parvez.tcp.TCPClient;
import vip.parvez.udp.UDPClient;

import static vip.parvez.helper.ServerHelper.TCP;
import static vip.parvez.helper.ServerHelper.UDP;

public class ClientHelper {
    String connectionType;

    public ClientHelper(String connectionType) {
        this.connectionType = connectionType;
    }

    public void startClient() {
        //start UDP Client
        if (connectionType.equalsIgnoreCase(UDP)) {
            new UDPClient(3001).run();
        }
        //start TCP server
        else if (connectionType.equalsIgnoreCase(TCP)) {
            new TCPClient(3001).run();
        }
        //start default server
        else {

        }
    }
}
