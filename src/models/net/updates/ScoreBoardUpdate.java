package models.net.updates;

import models.net.UpdatePacket;
import models.AccountProperty;
import newView.SceneMakers.ScoreBoardSceneMaker;

import java.util.ArrayList;

public class ScoreBoardUpdate extends UpdatePacket {
    private ArrayList<AccountProperty> accountProperties;

    // TODO Mostafa : 7/7/19 call it
    public ScoreBoardUpdate(ArrayList<AccountProperty> accountProperties) {
        this.accountProperties = accountProperties;
    }

    @Override
    public void update() {
        ScoreBoardSceneMaker.getScoreBoardPane().updateScoreBoard(accountProperties);
    }
}
