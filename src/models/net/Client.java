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
    private Socket socket;
    private MyObservable<Boolean> socketState;

    private Thread readerThread;
    private Thread writerThread;
    private BlockingQueue<RequestPacket> sendQueue = new LinkedBlockingDeque<>();
    private final YaGson serializer;
    private final JsonWriter jsonWriter;

    public Client(String ip, int port, String clientName) throws IOException {
        this.socket = new Socket(ip, port);
        this.serializer = new YaGson();
        this.jsonWriter = new JsonWriter(new OutputStreamWriter(socket.getOutputStream()));

        initClient();

        serializer.toJson(clientName, String.class, jsonWriter);
        jsonWriter.flush();
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
                    UpdatePacket packet = deserializer.fromJson(parser.next(), UpdatePacket.class);
                    //todo run packet
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

    public void sendPacket(RequestPacket packet) throws InterruptedException {
        sendQueue.put(packet);
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
}

