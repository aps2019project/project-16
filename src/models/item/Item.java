package models.item;

import com.gilecode.yagson.YaGson;
import models.Cell;
import models.Player;
import models.UniqueIDGenerator;
import models.magic.Buff;

public abstract class Item {
    private String name;
    private int buyPrice;
    private int sellPrice;
    private int collectionID;
    private int GameItemID;

    public Item(String name, int buyPrice, int sellPrice) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public static abstract class Builder {
        private String name;
        private int buyPrice;
        private int sellPrice;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBuyPrice(int buyPrice) {
            this.buyPrice = buyPrice;
            return this;
        }

        public Builder setSellPrice(int sellPrice) {
            this.sellPrice = sellPrice;
            return this;
        }

        public abstract Item create();

        String getName() {
            return name;
        }

        int getBuyPrice() {
            return buyPrice;
        }

        int getSellPrice() {
            return sellPrice;
        }
    }

    public int getCollectionID() {
        return collectionID;
    }

    public String getName() {
        return name;
    }

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

    public abstract void use(Player player, Cell cell);

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
