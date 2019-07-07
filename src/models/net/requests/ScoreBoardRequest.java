package models.net.requests;

import models.AccountProperty;
import models.GameContents;
import models.net.RequestPacket;
import models.net.Server;
import models.net.updates.scoreboradUpdates.ScoreBoardUpdate;

import java.util.ArrayList;

public class ScoreBoardRequest extends RequestPacket {
    @Override
    public void run() {
        ArrayList<AccountProperty> accountProperties = new ArrayList<>();

        GameContents.getAccounts().forEach(account -> {
            boolean online = Server.getInstance().isOnline(account.getName());

            AccountProperty property = new AccountProperty(account.getMoney(), account.getName()
                    , account.getWins(), online);

            accountProperties.add(property);
        });


        Server.getInstance().sendPacketByThread(new ScoreBoardUpdate(accountProperties));
    }
}
