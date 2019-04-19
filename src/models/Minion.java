package models;

public class Minion extends Unit{
    private Spell specialPower;
    private SpecialPowerCastTime specialPowerCastTime;

    public Minion(int manaCost, int buyPrice, int sellPrice, int hp, int ap) { //todo correct
        super(manaCost, buyPrice, sellPrice, hp, ap);
    }

    public void castSpecialPower(SpecialPowerCastTime time) {

    }
}
