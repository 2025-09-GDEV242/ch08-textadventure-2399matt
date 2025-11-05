import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room shire, oldForest, barrowDowns, breeGates, bree, prancingPony, rivendell, deadMarshes, blackGates, mordor, mountDoom, weatherTop, shelobsLair;
        shire = new Room("You find yourself in Bag End, sitting at your table enjoying a nice cup of tea. \n" +
                "Your very old friend Gandalf has entrusted your uncle's ring to you, and urges you to go on an adventure!", false, RoomLabel.SHIRE);
        oldForest = new Room("Surrounded by trees older than time itself, you find yourself further from The Shire than" +
                "ever before. \nThe leaves on this path crinkle beneath your feet, and the mist clouds your judgement on which route to take.", false, RoomLabel.OLDFOREST);
        barrowDowns = new Room("Your eyes are enchanted by the endless tree-less hills in front of you. \nYou see a barrow nearby" +
                " with some trinkets within your grasp. You don't know the history of this place, but its treasures may be of some use to you.", false, RoomLabel.BARROWDOWNS);
        breeGates = new Room("The large wooden gates of Bree, a town of men, stands before you. A voice seeps through the cracks " +
                "asking you your business.\nYou remember that Gandalf was to meet you at the Prancing Pony, perhaps you should enter the town" +
                "and take a look around.", false, RoomLabel.BREEGATES);
        bree = new Room("Thank goodness for reservations! Compared to The Shire, this is a busy place! Men three times your size are all around you, going about their business.\n" +
                "In the distance, you see a sign for the Prancing Pony.", true, RoomLabel.BREE);
        prancingPony = new Room("They have pints! Among the many men, you spot your dear friend Gandalf, clothed in his gray robe.\n" +
                "As you reminace about old tales, all others in the pub seem to fade out, and Gandalf becomes serious as he discusses" +
                "the severity of the ring that has been entrusted with you, it seems you must make your way to its place of creation and destroy it." +
                "\nHe talks about the journey you must take, and the long roads you'll endure going forward. Your mind grows slow, you sure hope you remembered to bring the ring.", false, RoomLabel.PRANCINGPONY);
        rivendell = new Room("It has been some days since you were at Bree. But, you find yourself in the last home of the elves; Rivendell!\n" +
                "Enchanting music, tasty food, and wise council surround you. You know what you must do, but you think; staying here forever would be quite the blessing", false, RoomLabel.RIVENDELL);
        deadMarshes = new Room("Long from Rivendell have you gone, and it is apparent. Swampy marshes, filled with the corpses of fallen" +
                " elves and men alike surround you. You hear a faint voice, 'don't follow the lights'.", false, RoomLabel.DEADMARSHES);
        weatherTop = new Room("WeatherTop. You can see all of the plains from here. Though you know that means all eyes are also on you.\n" +
                " There may be some items here, but it's best to keep moving. AND NO FIRES!", false, RoomLabel.WEATHERTOP);
        shelobsLair = new Room("Shelob's Lair. A dark cave covered in massive spider webs. You see that if you drop your sword" +
                " through some of these webs on the floor, you may be able to make it through unscathed.", false, RoomLabel.SHELOBSLAIR);
        blackGates = new Room("Never before have you felt such evil. The massive black gates of mordor are in your sight.\n" +
                " You know you cannot simply walk into Mordor. Surely it would take the likes of elven magic to pass through the gates to Mordor lying in the south!", false, RoomLabel.BLACKGATES);
        mordor = new Room("Desolate, dry, and fire everywhere. This is Mordor, you can feel it. Every second that passes, you" +
                " start to feel more ill, like this place is sapping the life away from you. This is no time to tarry.\n" +
                "The peaks of Mount Doom lie to the west.", true, RoomLabel.MORDOR);
        mountDoom = new Room("After what has felt like years, you find yourself in the place that the one ring was created.\n" +
                "Mount Doom has been reached. You know that you must drop the ring into the fire, but why does it feel so heavy now?\n" +
                "Turn back, or finish this. You know there are only two choices.", false, RoomLabel.MOUNTDOOM);

        //Routes
        shire.setExit("north", barrowDowns);
        shire.setExit("east", oldForest);
        barrowDowns.setExit("south", shire);
        oldForest.setExit("west", shire);
        oldForest.setExit("east", breeGates);
        breeGates.setExit("west", oldForest);
        breeGates.setExit("south", weatherTop);
        weatherTop.setExit("north", breeGates);
        breeGates.setExit("east", bree);
        bree.setExit("west", breeGates);
        bree.setExit("north", prancingPony);
        prancingPony.setExit("south", bree);
        bree.setExit("south", rivendell);
        rivendell.setExit("north", bree);
        rivendell.setExit("south", deadMarshes);
        deadMarshes.setExit("north", rivendell);
        deadMarshes.setExit("west", blackGates);
        blackGates.setExit("east", deadMarshes);
        deadMarshes.setExit("south", shelobsLair);
        shelobsLair.setExit("north", deadMarshes);
        blackGates.setExit("south", mordor);
        mordor.setExit("north", blackGates);
        shelobsLair.setExit("west", mordor);
        mordor.setExit("east", shelobsLair);
        mordor.setExit("west", mountDoom);
        mountDoom.setExit("east", mordor);

        //Items
        shire.addItem(new Item("the-one-ring", 1));
        shire.addItem(new Item("inn-reservation", 1));
        shire.addItem(new Item("longbottom-leaf", 1));
        barrowDowns.addItem(new Item("sword", 5));
        barrowDowns.addItem(new Item("mithril-armor",10));
        oldForest.addItem(new Item("mushrooms", 2));
        weatherTop.addItem(new Item("torch", 2));
        weatherTop.addItem(new Item("KingsFoil", 1));
        bree.addItem(new Item("pony-food", 2));
        prancingPony.addItem(new Item("pint-of-ale", 1));
        prancingPony.addItem(new Item("meat", 3));
        rivendell.addItem(new Item("healing-potion", 1));
        rivendell.addItem(new Item("elven-harp", 10));
        rivendell.addItem(new Item("elven-cloak", 3));
        deadMarshes.addItem(new Item("severed-finger", 1));
        deadMarshes.addItem(new Item("crow-meat", 2));
        blackGates.addItem(new Item("cogwheel", 1));
        shelobsLair.addItem(new Item("spider-silk", 1));
        mordor.addItem(new Item("orc-draught", 1));
        mordor.addItem(new Item("orc-armor", 10));
        mountDoom.addItem(new Item("palantir", 5));
        player.setCurrentRoom(shire);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished && !player.isHeroic()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case BACK:
                player.goBack();
                break;

            case TAKE:
                takeItem(command);
                break;

            case DROP:
                dropItem(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void takeItem(Command command) {
        String itemDescription = command.getSecondWord();
        Room currRoom = player.getCurrentRoom();
        Item item = currRoom.findItem(itemDescription);
        if(item == null) {
            System.out.println("There is no " + itemDescription + " in this room!");
            return;
        }
        player.addItem(item);
        currRoom.removeItem(item);
        System.out.println("You have picked up " + itemDescription);
    }

    private void dropItem(Command command) {
        String itemDescription = command.getSecondWord();
        Item item = player.findItem(itemDescription);
        if(item == null) {
            System.out.println("You are not carrying " + itemDescription);
            return;
        }
        player.removeItem(item);
        player.getCurrentRoom().addItem(item);
        System.out.println("You have dropped " + itemDescription);
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        player.moveRooms(direction);
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
