package models.net.requests.battleRequests;

public class InsertCardRequest extends BattleRequest {
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
