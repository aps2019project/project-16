package models.net.requests.battleRequests;

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
        // TODO mostafa:
        //  :
        //  controller.selectCollectable(collectibleID);
        //  controller.useSelectedCollectable(row, column);
    }
}