package models;

public class CollectionUniqueIDGenerator {
    public static int lastUsedID = 0;

    public static int getUniqueID() {
        lastUsedID++;
        return lastUsedID;
    }
}
