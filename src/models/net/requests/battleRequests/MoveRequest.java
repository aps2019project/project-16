package models.net.requests.battleRequests;

import controllers.InGameController;

public class MoveRequest extends BattleRequest {
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
        InGameController controller = new InGameController();
        try {
            controller.selectCard(unitName, gameID);
            controller.moveToCell(row, column);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
