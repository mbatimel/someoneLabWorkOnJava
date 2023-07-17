public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
public interface Iterator<T> {

    boolean hasNext();

    T next();

}
public enum ItemType {

    ANY, WEAPON, RING, POTION

}
public class Item {

    private ItemType type;
    private final String name;

    public Item(ItemType type, String name) {
        this.setType(type);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public final void setType(ItemType type) {
        this.type = type;
    }

}
public class TreasureChestItemIterator implements Iterator<Item> {

    private final TreasureChest chest;
    private final ItemType type;

    private int idx;

    public TreasureChestItemIterator(
            TreasureChest chest,
            ItemType type
    ) {
        this.chest = chest;
        this.type = type;
        this.idx = -1;
    }

    @Override
    public boolean hasNext() {
        return -1 != findNextIdx();
    }

    @Override
    public Item next() {
        idx = findNextIdx();
        if (-1 != idx)
            return chest.getItems().get(idx);

        return null;
    }

    private int findNextIdx() {
        List<Item> items = chest.getItems();
        int tempIdx = idx;
        while (true) {
            tempIdx++;
            if (tempIdx >= items.size()) {
                tempIdx = -1;
                break;
            }

            if (type.equals(ItemType.ANY)
                    || items.get(tempIdx).getType().equals(type)) {
                break;
            }
        }
        return tempIdx;
    }

}
public class TreasureChest {

    private final List<Item> items;

    /**
     * Constructor.
     */
    public TreasureChest() {
        items = List.of(
                new Item(ItemType.POTION, "Potion of courage"),
                new Item(ItemType.RING, "Ring of shadows"),
                new Item(ItemType.POTION, "Potion of wisdom"),
                new Item(ItemType.POTION, "Potion of blood"),
                new Item(ItemType.WEAPON, "Sword of silver +1"),
                new Item(ItemType.POTION, "Potion of rust"),
                new Item(ItemType.POTION, "Potion of healing"),
                new Item(ItemType.RING, "Ring of armor"),
                new Item(ItemType.WEAPON, "Steel halberd"),
                new Item(ItemType.WEAPON, "Dagger of poison")
        );
    }

    public Iterator<Item> iterator(ItemType itemType) {
        return new TreasureChestItemIterator(this, itemType);
    }

    /**
     * Get all items.
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

}