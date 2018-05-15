package vip.parvez;

import vip.parvez.helper.ServerHelper;

public class Server {
    public static void main(String[] args) {
        ServerHelper server = new ServerHelper(ServerHelper.TCP);
        server.startServer();
    }
}
