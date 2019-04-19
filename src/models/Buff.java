package models;

public class Buff {
    public static final int INFINITY = Integer.MAX_VALUE;
    private int remainingDuration;
    private boolean holy;
    private int deltaHP;
    private int deltaAP;
    private int poison;
    private boolean stun;
    private boolean disarm;

    public void cast(Unit unit) {

    }

    public void cast(Cell cell) {

    }

    public boolean hasStun() {
        return stun;
    }

    public boolean hasDisarm() {
        return disarm;
    }
}