package models.net.requests.watchRequests;

import models.MatchDetail;
import models.net.RequestPacket;
import models.net.Server;
import models.net.updates.watchUpdates.LiveListUpdate;

import java.util.ArrayList;

public class GetLiveListRequest extends RequestPacket {
    // TODO Sadegh: 7/6/19 call it


    @Override
    public void run() {
        ArrayList<MatchDetail> matchDetails = new ArrayList<>();
        Server.getInstance().getLiveGames().forEach(game -> {
            MatchDetail detail = new MatchDetail(game.getFirstAccount().getName(), game.getSecondAccount().getName(),
                    game.getMatchId());
            matchDetails.add(detail);
        });
        Server.getInstance().sendPacketByThread(new LiveListUpdate(matchDetails));
    }
}
