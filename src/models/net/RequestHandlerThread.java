package models.net;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.JsonStreamParser;
import models.Game;
import models.GameContents;
import models.net.requests.LoginRequest;
import models.net.requests.ScoreBoardRequest;
import models.net.requests.SignUpRequest;
import models.net.requests.battleRequests.BattleRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandlerThread extends Thread {
    private final YaGson deserializer = new YaGson();
    private final JsonStreamParser parser;
    private MyObservable<Boolean> socketState;
    private String accountName;
    private ServerSideClient serverSideClient;

    public RequestHandlerThread(ServerSideClient serverSideClient, Socket socket, MyObservable<Boolean> socketState)
            throws IOException {
        this.serverSideClient = serverSideClient;
        parser = new JsonStreamParser(new InputStreamReader(socket.getInputStream()));
        this.socketState = socketState;
    }

    @Override
    public void run() {
        try {
            while (parser.hasNext()) {
                RequestPacket packet = deserializer.fromJson(parser.next(), RequestPacket.class);
                System.out.println(packet);
                if (!(packet instanceof SignUpRequest)
                        && !(packet instanceof LoginRequest)
                        && !(packet instanceof ScoreBoardRequest))
                    if (packet.getAuthToken() == null || !packet.getAuthToken().equals(serverSideClient.getAuthToken()))
                        continue;
                if (packet instanceof BattleRequest) {
                    Game game = GameContents.findAccount(accountName).getCurrentGame();
                    String currentPlayerName = game.getCurrentPlayer().getName();
                    if (!currentPlayerName.equals(accountName))
                        continue;
                }
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

    public ServerSideClient getServerSideClient() {
        return serverSideClient;
    }
}
