package models.net.requests.battleRequests;

import models.net.RequestPacket;

public class InsertCardRequest extends RequestPacket {
    private String cardName;
    private int row;
    private int column;

    public InsertCardRequest(String cardName, int row, int column) {
        this.cardName = cardName;
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
        // TODO mostafa:
        //  :
        //  controller.insertCard(cardName, row, column);
    }
}
