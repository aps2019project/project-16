package models.net.requests.gameRequests;

import models.net.RequestHandlerThread;
import models.net.RequestPacket;
import models.net.Server;

public class RefuseRequest extends RequestPacket {
    @Override
    public void run() {
        Server server = Server.getInstance();
        String accountName = ((RequestHandlerThread) Thread.currentThread()).getAccountName();
        synchronized (server.matchQueueLock) {
            if (server.getMatchQueue() != null && server.getMatchQueue().equals(accountName))
                server.setMatchQueue(null);
        }
    }
}
