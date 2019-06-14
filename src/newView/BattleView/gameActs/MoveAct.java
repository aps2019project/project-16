package newView.BattleView.gameActs;

public class MoveAct extends GameAct {
    private int startRow;
    private int startColumn;
    private int destinationRow;
    private int destinationColumn;

    public MoveAct(int startRow, int startColumn, int destinationRow, int destinationColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.destinationRow = destinationRow;
        this.destinationColumn = destinationColumn;
    }

    @Override
    public void showAction() {
        // TODO: 6/14/19
    }
}
