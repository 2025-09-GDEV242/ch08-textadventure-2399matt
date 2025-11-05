import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Player class. This class is used to represent a player's state.
 * The player should know what rooms they have visited and are in, along with the items they are carrying.
 *
 * @author Matt Witham
 * @version 11.4.25
 */
public class Player {

    private boolean isHeroic;
    private List<Item> playerItems;
    private Stack<Room> visitedRooms;
    private Room currentRoom;

    public Player() {
        isHeroic = false;
        playerItems = new ArrayList<>();
        visitedRooms = new Stack<>();
    }

    /**
     * Method to help with the 'back' command of the game.
     * Uses a stack to back-pedal rooms unless there are no previous rooms visited.
     */
    public void goBack() {
        if (visitedRooms.isEmpty()) {
            System.out.println("You cannot go back any further!");
            return;
        }
        currentRoom = visitedRooms.pop();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Method to help with moving the player.
     * Grabs the next room, checks if it is locked / null. If not, pushes the current room on the stack
     * and defines the new current room.
     *
     * @param nextRoom The room that the player will be entering.
     */
    public void moveRooms(Room nextRoom) {
        visitedRooms.push(currentRoom);
        currentRoom = nextRoom;
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        playerItems.add(item);
    }

    /**
     * Removes an item from the players inventory.
     * NOTE: If the item is the one ring, and the player is in Mt. Doom, the game's ending will trigger.
     *
     * @param item The item to remove from the player's inventory.
     */
    public void removeItem(Item item) {
        if (item.getId() != null && item.getId() == ItemLabel.THE_ONE_RING) {
            if (currentRoom.getId() == RoomLabel.MOUNTDOOM) {
                isHeroic = true;
                System.out.println("MiddleEarth is saved! The time of men has come.\n");
            }
        }
        playerItems.remove(item);
    }

    /**
     * Finds an item in the player's inventory.
     *
     * @param itemDescription The description of the item to search for.
     * @return The found item, or null if the player does not have it.
     */
    public Item findItem(String itemDescription) {
        return playerItems
                .stream()
                .filter(item -> item.getDescription().equals(itemDescription))
                .findFirst()
                .orElse(null);
    }

    /**
     * Finds an item in the player's inventory based off id.
     * @param id The ItemLabel of the item to search for.
     * @return The Item object with that id.
     */
    public Item findItemById(ItemLabel id) {
        return playerItems
                .stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the current room that the player is in.
     *
     * @return The current room that the player is in.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the players current room.
     *
     * @param room The room that the player is currently in.
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     * Returns a list of all the items in a players inventory.
     *
     * @return The list of the items that the player currently has.
     */
    public List<Item> getPlayerItems() {
        return playerItems;
    }

    /**
     * Returns the current heroic state of the player.
     * If the player drops the ring in Mt. Doom, they are heroic
     * and will trigger the end of the game.
     *
     * @return The heroic status of the player.
     */
    public boolean isHeroic() {
        return isHeroic;
    }
}
