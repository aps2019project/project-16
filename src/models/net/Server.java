package models.net;

import controllers.AccountController;
import models.Account;
import models.Game;
import models.GameContents;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private static Server instance;
    private ServerSocket serverSocket;
    private final List<ServerSideClient> clients = Collections.synchronizedList(new ArrayList<>());
    private Thread acceptorThread;

    private MyObservable<Boolean> socketState;

    private String matchQueue;
    public final String matchQueueLock = "Lock";

    ArrayList<Game> liveGames = new ArrayList<>();
    ArrayList<Game> gameHistory = new ArrayList<>();

    private Server() {
        new AccountController().loadAccounts();
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socketState = new MyObservable<>(true);
        acceptorThread = new Thread(() -> {
            while (true) {
                try {
                    while (true)
                        acceptClient(serverSocket.accept());
                } catch (IOException ignored) {
                } finally {
                    try {
                        close();
                    } catch (IOException ignored) {
                    }
                }
            }
        });
        acceptorThread.start();
    }

    public void sendPacketByThread(UpdatePacket packet) {
        if (Thread.currentThread() instanceof RequestHandlerThread)
            ((RequestHandlerThread) Thread.currentThread()).getServerSideClient().sendPacket(packet);
    }

    private void acceptClient(Socket socket) throws IOException {
        System.out.println("client request received");
        ServerSideClient client = new ServerSideClient(socket);
        client.getSocketState().addObserver(newState -> {
            if (!newState)
                clients.remove(client);
        });
        clients.add(client);
    }

    public void close() throws IOException {
        try {
            acceptorThread.interrupt();
            serverSocket.close();
        } finally {
            socketState.setState(false);
        }
    }

    public Game getCurrentGameByThread() {
        String accountName = ((RequestHandlerThread) Thread.currentThread()).getAccountName();
        Account account = GameContents.findAccount(accountName);
        return account.getCurrentGame();
    }

    public void sendPacketTo(String accountName, UpdatePacket packet) {
        for (ServerSideClient client : clients)
            if (client.getAccountName() != null && client.getAccountName().equals(accountName))
                client.sendPacket(packet);
    }

    public void broadcastPacket(UpdatePacket packet) {
        for (ServerSideClient client : clients)
            client.sendPacket(packet);
    }

    public String getMatchQueue() {
        return matchQueue;
    }

    public void setMatchQueue(String matchQueue) {
        this.matchQueue = matchQueue;
    }

    public boolean isOnline(String accountName) {
        for (ServerSideClient client : clients) {
            if (accountName.equals(client.getAccountName())) {
                return true;
            }
        }
        return false;
    }

    public static Server getInstance() {
        if (instance == null)
            instance = new Server();
        return instance;
    }

    public void addLiveGame(Game game) {
        liveGames.add(game);
    }

    public void liveGameFinish(Game game) {
        liveGames.remove(game);
        gameHistory.add(game);
    }

    public ArrayList<Game> getLiveGames() {
        return liveGames;
    }

    public ArrayList<Game> getGameHistory() {
        return gameHistory;
    }

    public Game getGame(int matchId) {
        for (Game game : liveGames)
            if (game.getMatchId() == matchId)
                return game;
        for (Game game : gameHistory)
            if (game.getMatchId() == matchId)
                return game;
        return null;
    }
}
