package models.net.requests.watchRequests;

import models.net.RequestPacket;

public class LiveWatchRequest extends RequestPacket {
    private int matchID;

    // TODO Sadegh: 7/6/19 call it
    public LiveWatchRequest(int matchID) {
        this.matchID = matchID;
    }

    @Override
    public void run() {
        // TODO Mostafa: 7/6/19
    }
}
