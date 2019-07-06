package newView.battleView;

import models.Account;
import models.Game;
import models.artificialIntelligence.AIAccount;
import models.net.Server;
import models.net.UpdatePacket;
import models.net.updates.BattleUpdate;
import newView.battleView.gameActs.GameAct;

import java.util.ArrayList;

public class ClientSender {
    public static void sendToAllViewers(GameAct gameAct) {
        UpdatePacket packet = new BattleUpdate(gameAct);
        Game game = Server.getInstance().getCurrentGameByThread();
        ArrayList<Account> accounts = game.getSpectators();
        for (Account account : accounts) {
            if (account instanceof AIAccount) {
                continue;
            }
            Server.getInstance().sendPacketTo(account.getName(), packet);
        }
    }
}
