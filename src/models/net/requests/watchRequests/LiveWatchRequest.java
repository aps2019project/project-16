package models.net.requests.watchRequests;

import models.Account;
import models.Game;
import models.GameContents;
import models.net.RequestHandlerThread;
import models.net.RequestPacket;
import models.net.Server;
import models.net.updates.watchUpdates.FastShowUpdate;
import newView.battleView.gameActs.GameAct;

import java.util.ArrayList;

public class LiveWatchRequest extends RequestPacket {
    private int matchID;

    // TODO Sadegh: 7/6/19 call it
    public LiveWatchRequest(int matchID) {
        this.matchID = matchID;
    }

    @Override
    public void run() {
        Game game = Server.getInstance().getGame(matchID);
        Server.getInstance().sendPacketByThread(new FastShowUpdate(game.getGameActs()));
        Account account = GameContents.findAccount(((RequestHandlerThread) Thread.currentThread()).getAccountName());
        game.addSpectator(account);
    }
}
