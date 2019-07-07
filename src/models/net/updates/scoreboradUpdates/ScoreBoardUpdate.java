package models.net.updates.scoreboradUpdates;

import models.AccountProperty;
import models.net.UpdatePacket;
import newView.SceneMakers.ScoreBoardSceneMaker;

import java.util.ArrayList;

public class ScoreBoardUpdate extends UpdatePacket {
    private ArrayList<AccountProperty> accountProperties;

    public ScoreBoardUpdate(ArrayList<AccountProperty> accountProperties) {
        this.accountProperties = accountProperties;
    }

    @Override
    public void update() {
        ScoreBoardSceneMaker.getScoreBoardPane().updateScoreBoard(accountProperties);
    }
}
