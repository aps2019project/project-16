package models.net.requests.battleRequests;

import controllers.InGameController;

public class CastCollectibleRequest extends BattleRequest {
    private int collectibleID;
    private int row;
    private int column;

    public CastCollectibleRequest(int collectibleID, int row, int column) {
        this.collectibleID = collectibleID;
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
        InGameController controller = new InGameController();
        try {
            controller.selectCollectable(collectibleID);
            controller.useSelectedCollectable(row, column);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
