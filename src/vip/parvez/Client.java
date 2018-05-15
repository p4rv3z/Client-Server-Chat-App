package vip.parvez;

import vip.parvez.helper.ClientHelper;
import vip.parvez.helper.ServerHelper;

public class Client {
    public static void main(String[] args) {
        ClientHelper client = new ClientHelper(ServerHelper.TCP);
        client.startClient();
    }
}
