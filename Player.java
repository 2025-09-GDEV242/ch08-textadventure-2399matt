import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {

    private boolean isHeroic;
    private List<Item> playerItems;
    private Stack<Room> visitedRooms;
    private Room currentRoom;

    public Player() {
        isHeroic = false;
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
        else if(nextRoom.isLocked()) {
            handleLockedRoom(nextRoom);
        }
        else {
            visitedRooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private void handleLockedRoom(Room nextRoom) {
        switch(nextRoom.getId()){
            case BREE:
                if(findItem("inn-reservation") != null) {
                    nextRoom.setLocked(false);
                    visitedRooms.push(currentRoom);
                    currentRoom = nextRoom;
                    System.out.println(currentRoom.getLongDescription());
                } else {
                    System.out.println("The way is shut. It seems they are only accepting reservations!\n" +
                            "you have a faint memory of Gandalf leaving a voucher for you somewhere...");
                }
                break;
            case MORDOR:
                if(currentRoom.getId() == RoomLabel.SHELOBSLAIR) {
                    if(findItem("sword") != null) {
                        nextRoom.setLocked(false);
                        visitedRooms.push(currentRoom);
                        currentRoom = nextRoom;
                        System.out.println("THANK GOODNESS FOR THE SWORD! THOSE WEBS STOOD NO CHANCE!\n\n");
                        System.out.println(currentRoom.getLongDescription());
                    } else {
                        System.out.println("These webs are too thick! If only I had a sword..");
                    }
                } else {
                    if(findItem("elven-cloak") != null) {
                        nextRoom.setLocked(false);
                        visitedRooms.push(currentRoom);
                        currentRoom = nextRoom;
                        System.out.println("THAT ELVEN CLOAK WAS A LIFESAVER! *Seems one really can just walk into Mordor*");
                        System.out.println(currentRoom.getLongDescription());
                    } else {
                        System.out.println("An elven cloak may provide the cover I need to pass through these gates!");
                    }
                }
                break;
        }
    }

    public void addItem(Item item) {
        playerItems.add(item);
    }

    public void removeItem(Item item) {
        if(item.getDescription().equals("the-one-ring")) {
            if(currentRoom.getId() == RoomLabel.MOUNTDOOM) {
                isHeroic = true;
                System.out.println("MiddleEarth is saved! The time of men has come.\n");
            }
        }
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

    public boolean isHeroic() {
        return isHeroic;
    }
}
