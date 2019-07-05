package models.net.updates.scoreboradUpdates;

import models.net.UpdatePacket;

public class UserConnectionUpdate extends UpdatePacket {
    private String username;
    private int score;
    private boolean isOnline;
    private int money;

    public UserConnectionUpdate(String username, int score, boolean isOnline, int money) {
        this.username = username;
        this.score = score;
        this.isOnline = isOnline;
        this.money = money;
    }

    @Override
    public void update() {
        // TODO sepehr
    }
}
