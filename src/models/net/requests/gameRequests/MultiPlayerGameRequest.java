package models.net.requests.gameRequests;

import controllers.MultiPlayerController;
import models.net.RequestHandlerThread;
import models.net.RequestPacket;
import models.net.Server;
import view.views.MultiPlayerView;

public class MultiPlayerGameRequest extends RequestPacket {
    @Override
    public void run() {
        Server server = Server.getInstance();
        synchronized (server.matchQueueLock) {
            if (server.getMatchQueue() == null)
                server.setMatchQueue(((RequestHandlerThread) Thread.currentThread()).getAccountName());
            else {
                MultiPlayerController controller = new MultiPlayerController(new MultiPlayerView());
                controller.selectOppUser(server.getMatchQueue());
                controller.startMultiGame(1,0);
                server.setMatchQueue(null);
            }
        }
    }
}
