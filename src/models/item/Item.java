package models.item;

import com.gilecode.yagson.YaGson;
import models.Cell;
import models.Player;
import models.UniqueIDGenerator;
import models.magic.Buff;

public abstract class Item {
    private Buff buff;
    private String name;
//    private TargetSociety targetSociety;
    private int buyPrice;
    private int sellPrice;
    private int collectionID;
    private int GameItemID;

    public int getCollectionID() {
        return collectionID;
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public String getName() {
        return name;
    }
//    public TargetSociety getTargetSociety() {
//        return targetSociety;
//    }

//    public void setTargetSociety(TargetSociety targetSociety) {
//        this.targetSociety = targetSociety;
//    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getDescription() {
        // TODO: 5/3/19
        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////

    public void use(Player player, Cell cell) {

    }

    public Item getCopy(boolean setCollectionID) {
        YaGson yaGson = new YaGson();
        String json = yaGson.toJson(this);
        Item newItem = yaGson.fromJson(json, this.getClass());
        if (setCollectionID) {
            newItem.collectionID = UniqueIDGenerator.getCollectionUniqueID();
        }
        return newItem;
    }
}