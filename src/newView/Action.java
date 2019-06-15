package newView;

public enum Action {
    IDLE("idle"),
    ATTACK("attack"),
    RUN("run"),
    BREATHING("breathing"),
    DEATH("death"),
    HIT("hit"),
    ACTIVE("active"),
    NOTHING("");

    private String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
