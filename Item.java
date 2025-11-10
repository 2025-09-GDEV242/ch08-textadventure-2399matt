/**
 * Item class used to store the state of a game-item. An item should know its
 * description, weight, and id if it pertains to game progression.
 *
 * @author Matt Witham
 * @version 11.4.25
 */
public class Item {

    private String description;

    private int weight;

    private ItemLabel id;

    /**
     * Constructor for Items without an id.
     * @param description The description of the item.
     * @param weight The weight of the item.
     */
    public Item(String description, int weight) {
        this.description = description;
        this.weight = weight;
        this.id = null;
    }

    /**
     * Overloaded constructor to include ItemLabel for identifying specific items.
     * @param description The description of the item.
     * @param weight The weight of the item.
     * @param label The id of the item.
     */
    public Item(String description, int weight, ItemLabel label) {
        this.description = description;
        this.weight = weight;
        this.id = label;
    }

    /**
     * Returns the description of the item.
     * @return the description of the item. 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of item.
     * @param The description of the item.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the weight of a given item.
     * @return the weight of the item.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the weight of an item. 
     * @param The weight of the item.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Returns the id of the item.
     * @return the id of the item.
     */
    public ItemLabel getId() {
        return id;
    }

    /**
     * Sets the id for an item.
     * @param the id for the item.
     */
    public void setId(ItemLabel id) {
        this.id = id;
    }
}
