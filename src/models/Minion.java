package models;

public class Minion extends Unit{
    private Spell specialPower;
    private SpecialPowerCastTime specialPowerCastTime;

    public Minion(String name, int manaCost, int buyPrice, int sellPrice, int hp, int ap, AttackType attackType,
                  Spell specialPower, SpecialPowerCastTime specialPowerCastTime) {
        super(name, manaCost, buyPrice, sellPrice, hp, ap, attackType);
        this.specialPower = specialPower;
        this.specialPowerCastTime = specialPowerCastTime;
    }

    public void castSpecialPower(SpecialPowerCastTime time, Cell cell) {
        if (time == specialPowerCastTime)
            specialPower.cast(getPlayer(), cell);
    }
}
