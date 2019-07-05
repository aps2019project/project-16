package models.net.requests.battleRequests;

public class SpecialPowerRequest extends BattleRequest {
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
