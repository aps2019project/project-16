package models.card.exception;

public class UnitAttackedThisTurnException extends AttackException {
    @Override
    public String getMessage() {
        return "Unit has attacked in this turn.";
    }
}
