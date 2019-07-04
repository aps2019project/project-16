package models.net;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.Gson;
import com.gilecode.yagson.com.google.gson.JsonStreamParser;
import com.gilecode.yagson.com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ServerSideClient {
    private final YaGson serializer;
    private final JsonWriter jsonWriter;
    private final Gson deserializer;
    private final JsonStreamParser parser;

    private Socket socket;
    private String clientName;

    private MyObservable<Boolean> socketState;

    private Thread writerThread;
    private BlockingQueue<UpdatePacket> sendQueue = new LinkedBlockingDeque<>();
    private Thread readerThread;

    public ServerSideClient(Socket socket) throws IOException {
        this.socket = socket;
        this.serializer = new YaGson();
        this.jsonWriter = new JsonWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.deserializer = new Gson();
        this.parser = new JsonStreamParser(new InputStreamReader(socket.getInputStream()));

        initSocket();
    }

    private void initSocket() {
        this.socketState = new MyObservable<>(true);

        this.writerThread = new Thread(() ->
        {
            try {
                while (true) {
                    serializer.toJson(sendQueue.take(), UpdatePacket.class, jsonWriter);
                    jsonWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ignored) {
            } finally {
                try {
                    socket.close();
                } catch (Exception ignored) {
                }
                socketState.setState(false);
            }
        });
        this.writerThread.start();

        this.readerThread = new Thread(() ->
        {
            try {
                while (parser.hasNext()) {
                    RequestPacket packet = deserializer.fromJson(parser.next(), RequestPacket.class);
                    packet.run();
                }
            } finally {
                socketState.setState(false);
            }
        });
        this.readerThread.start();
    }

    public void sendPacket(UpdatePacket packet) {
        try {
            sendQueue.put(packet);
        } catch (InterruptedException e) {
            try {
                socket.close();
            } catch (Exception ignored) {
            }
            socketState.setState(false);
        }
    }

    public void close() throws IOException {
        try {
            writerThread.interrupt();
            readerThread.interrupt();
            socket.close();
        } finally {
            socketState.setState(false);
        }
    }

    public String getClientName() {
        return clientName;
    }

    public MyObservable<Boolean> getSocketState() {
        return socketState;
    }
}
