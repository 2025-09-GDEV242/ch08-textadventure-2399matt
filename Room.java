import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author Matt Witham
 * @author Michael Kölling and David J. Barnes
 * @version 11.4.25
 */

public class Room {
    private RoomLabel id;
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private List<Item> roomItems;
    private boolean isLocked;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description, boolean isLocked, RoomLabel id) {
        this.id = id;
        this.description = description;
        this.isLocked = isLocked;
        roomItems = new ArrayList<>();
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the room in the form:
     * You are in the kitchen.
     * Exits: north west
     *
     * @return A long description of this room
     */
    public String getLongDescription() {
        String str = (roomItems.isEmpty()) ? "There are no items here!" : "Items in this room: ";
        for (Item item : roomItems) {
            str += item.getDescription() + ", ";
        }
        return description + "\n" + getExitString() + "\n" + str;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     *
     * @return Details of the room's exits.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Adds an item to a room.
     *
     * @param item The item to add to the room.
     */
    public void addItem(Item item) {
        roomItems.add(item);
    }

    /**
     * Removes an item from a room.
     *
     * @param item The item to be removed.
     */
    public void removeItem(Item item) {
        roomItems.remove(item);
    }

    /**
     * Finds a given item in the room by its description.
     *
     * @param itemDescription The description of the item.
     * @return The searched-for item, or null in the case the room does not have a match.
     */
    public Item findItem(String itemDescription) {
        return roomItems
                .stream()
                .filter(item -> item.getDescription().equals(itemDescription))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns all of the items in a room.
     *
     * @return The list of Items in the room.
     */
    public List<Item> getRoomItems() {
        return roomItems;
    }

    /**
     * Returns the state of the lock on a room.
     *
     * @return The current status of the room being locked.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Sets the locked status of a room.
     *
     * @param locked true to lock a room, false to unlock.
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    /**
     * Returns the id (RoomLabel) for a given room.
     *
     * @return The id of a room.
     */
    public RoomLabel getId() {
        return id;
    }
}

