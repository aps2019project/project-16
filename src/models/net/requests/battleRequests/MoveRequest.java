package models.net.requests.battleRequests;

import models.net.RequestPacket;

public class MoveRequest extends RequestPacket {
    private String unitName;
    private int gameID;
    private int row;
    private int column;

    public MoveRequest(String unitName, int gameID, int row, int column) {
        this.unitName = unitName;
        this.gameID = gameID;
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
        // TODO mostafa:
        //  :
        //  controller.selectCard(unitName, gameID);
        //  controller.moveToCell(row, column);
    }
}
