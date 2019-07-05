package models.net.requests.battleRequests;

import models.net.RequestPacket;

public class SpecialPowerRequest extends RequestPacket {
    private int row;
    private int column;

    public SpecialPowerRequest(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
        // TODO mostafa:
        //  :
        //  controller.useSpecialPower(row, column);
    }
}
