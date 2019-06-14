package exception;

public class UnitStunnedException extends AttackException {
    @Override
    public String getMessage() {
        return "Unit is stunned.";
    }
}
