package exception;

public class OpponentNotInRangeException extends AttackException {
    @Override
    public String getMessage() {
        return "Opponent is not in range!";
    }
}
