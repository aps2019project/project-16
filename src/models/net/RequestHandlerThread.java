package models.net;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.JsonStreamParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandlerThread extends Thread {
    private final YaGson deserializer = new YaGson();
    private final JsonStreamParser parser;
    private MyObservable<Boolean> socketState;
    private String accountName;

    public RequestHandlerThread(Socket socket, MyObservable<Boolean> socketState) throws IOException {
        parser = new JsonStreamParser(new InputStreamReader(socket.getInputStream()));
        this. socketState = socketState;
    }
    @Override
    public void run() {
        try {
            while (parser.hasNext()) {
                RequestPacket packet = deserializer.fromJson(parser.next(), RequestPacket.class);
                System.out.println(packet);
                packet.run();
            }
        } finally {
            socketState.setState(false);
        }
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
