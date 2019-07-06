package models.net.requests.battleRequests;

import controllers.InGameController;

public class SpecialPowerRequest extends BattleRequest {
    private int row;
    private int column;

    public SpecialPowerRequest(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
        InGameController controller = new InGameController();
        try {
            controller.useSpecialPower(row, column);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
