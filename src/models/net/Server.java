package models.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private final List<ServerSideClient> clients = Collections.synchronizedList(new ArrayList<>());
    private Thread acceptorThread;

    private MyObservable<Boolean> socketState;

    public Server() {
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
}
