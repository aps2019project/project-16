package models.item;

import com.gilecode.yagson.YaGson;
import models.Cell;
import models.Player;
import models.UniqueIDGenerator;

public abstract class Item {
    private String name;
    private int buyPrice;
    private int sellPrice;
    private int collectionID;
    private Type type;
    private int GameItemID;
    private int collectibleID;

    public Item(String name, int buyPrice, int sellPrice, Type type) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.type = type;
    }

    public enum Type {
        USABLE,
        COLLECTIBLE
    }

    public static abstract class ItemBuilder {
        private String name;
        private int buyPrice;
        private int sellPrice;
        private Type type;

        public ItemBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder setBuyPrice(int buyPrice) {
            this.buyPrice = buyPrice;
            return this;
        }

        public ItemBuilder setSellPrice(int sellPrice) {
            this.sellPrice = sellPrice;
            return this;
        }

        public ItemBuilder setType(Type type) {
            this.type = type;
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

        Type getType() {
            return type;
        }
    }

    public int getCollectibleID() {
        return collectibleID;
    }

    public void setCollectibleID(int collectibleID) {
        this.collectibleID = collectibleID;
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
