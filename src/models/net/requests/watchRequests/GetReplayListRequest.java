package models.net.requests.watchRequests;

import models.MatchDetail;
import models.net.RequestPacket;
import models.net.Server;
import models.net.updates.watchUpdates.ReplayListUpdate;

import java.util.ArrayList;

public class GetReplayListRequest extends RequestPacket {
    // TODO Sadegh: 7/6/19 call it


    @Override
    public void run() {
        ArrayList<MatchDetail> matchDetails = new ArrayList<>();
        Server.getInstance().getGameHistory().forEach(game -> {
            boolean firstIsWinner = game.getWinner().getName().equals(game.getFirstAccount().getName());
            MatchDetail detail = new MatchDetail(game.getFirstAccount().getName(), game.getSecondAccount().getName(),
                    firstIsWinner, game.getMatchId());
            matchDetails.add(detail);
        });
        Server.getInstance().sendPacketByThread(new ReplayListUpdate(matchDetails));
    }
}
