import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {

    private List<Item> playerItems;
    private Stack<Room> visitedRooms;
    private Room currentRoom;

    public Player() {
        playerItems = new ArrayList<Item>();
        visitedRooms = new Stack<Room>();
    }

    public void goBack() {
        if(visitedRooms.isEmpty()) {
            System.out.println("You cannot go back any further!");
            return;
        }
        currentRoom = visitedRooms.pop();
        System.out.println(currentRoom.getLongDescription());
    }

    public void moveRooms(String direction) {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            visitedRooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    public void addItem(Item item) {
        playerItems.add(item);
    }

    public void removeItem(Item item) {
        playerItems.remove(item);
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Item findItem(String itemDescription) {
        return playerItems
                .stream()
                .filter(item -> item.getDescription().equals(itemDescription))
                .findFirst()
                .orElse(null);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public List<Item> getPlayerItems() {
        return playerItems;
    }
}
