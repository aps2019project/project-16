package models.net;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.JsonStreamParser;
import com.gilecode.yagson.com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Client {
    private static Client instance;
    private Socket socket;
    private MyObservable<Boolean> socketState;
    private String authToken;

    private Thread readerThread;
    private Thread writerThread;
    private BlockingQueue<RequestPacket> sendQueue = new LinkedBlockingDeque<>();
    private final YaGson serializer;
    private final JsonWriter jsonWriter;

    private Client(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        this.serializer = new YaGson();
        this.jsonWriter = new JsonWriter(new OutputStreamWriter(socket.getOutputStream()));

        initClient();
    }

    private void initClient() {
        this.socketState = new MyObservable<>(true);

        this.writerThread = new Thread(() ->
        {
            try {
                while (true) {
                    serializer.toJson(sendQueue.take(), RequestPacket.class, jsonWriter);
                    jsonWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (Exception ignored) {
                }
            } catch (InterruptedException ignored) {
            } finally {
                socketState.setState(false);
            }
        });
        this.writerThread.start();

        this.readerThread = new Thread(() ->
        {
            YaGson deserializer = new YaGson();
            try (InputStream inputStream = socket.getInputStream()) {
                JsonStreamParser parser = new JsonStreamParser(new InputStreamReader(inputStream));
                while (parser.hasNext()) {
                    try {
                        UpdatePacket packet = deserializer.fromJson(parser.next(), UpdatePacket.class);
                        packet.update();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socketState.setState(false);
            }
        });
        this.readerThread.start();
    }

    public void sendPacket(RequestPacket packet) {
        try {
            packet.setAuthToken(authToken);
            sendQueue.put(packet);
        } catch (InterruptedException e) {
            System.exit(0);
        }
    }

    public MyObservable<Boolean> getSocketState() {
        return socketState;
    }

    public void close() throws IOException {
        try {
            readerThread.interrupt();
            writerThread.interrupt();
            socket.close();
        } finally {
            socketState.setState(false);
        }
    }

    public static Client getInstance() {
        if (instance == null) {
            try {
                instance = new Client("127.0.0.1", 8080); // todo change port
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}

