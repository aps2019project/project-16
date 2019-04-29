package models;

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

    /////////////////////////////////////////////////////////////////////////////////
    public abstract void use();


    public Item getCopy(boolean setCollectionID) {
        // TODO: 4/29/19 copy by kind of Item
        Item newItem = this;//this is chert and movaghat!!
        if (setCollectionID) {
            collectionID = CollectionUniqueIDGenerator.getUniqueID();
        }
        return newItem;
    }
}
