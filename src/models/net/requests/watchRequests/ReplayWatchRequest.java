package models.net.requests.watchRequests;

import models.net.RequestPacket;
import models.net.Server;
import models.net.UpdatePacket;
import models.net.updates.watchUpdates.MatchReplayUpdate;

public class ReplayWatchRequest extends RequestPacket {
    private int matchID;

    // TODO Sadegh: 7/6/19 call it
    public ReplayWatchRequest(int matchID) {
        this.matchID = matchID;
    }

    @Override
    public void run() {
        UpdatePacket packet = new MatchReplayUpdate(Server.getInstance().getGame(matchID).getGameActs());
        Server.getInstance().sendPacketByThread(packet);
    }
}
