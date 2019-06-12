package newView;

public enum Action {
    ATTACK("attack"),
    RUN("run"),
    BREATHING("breathing"),
    DEATH("death"),
    HIT("hit"),
    NOTHING("nothing");

    private String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
