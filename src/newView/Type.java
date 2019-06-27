package newView;

public enum Type {
    HERO("hero"),
    MINION("minion"),
    SPELL("spell"),
    ITEM("item"),
    SPECIAL_POWER("specialPower");

    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
