package models.net.requests.battleRequests;

import controllers.InGameController;

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
        InGameController controller = new InGameController();
        try {
            controller.insertCard(cardName, row, column);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
