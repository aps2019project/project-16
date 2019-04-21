package models;

import java.util.List;

public interface Buffable {
    void addBuff(Buff buff);
    void addBuffs(List<Buff> buffs);
    void doBuffs();
}
