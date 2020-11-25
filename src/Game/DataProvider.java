package Game;

public class DataProvider
{
    public static Room[] createRooms() {
        // Crée les pièces du jeu
        Room bedroom = new Room("bedroom", "This is your bedroom. It's decorated with cute pink striped wallpaper. Your king-sized bed lies in a corner, and your desk sits opposite to it.");
        Room bathroom = new Room("bathroom", "This is your bathroom. It has ocean-blue tiling on the walls, and there's a tub (and not a shower).");
        Room kitchen = new Room("kitchen", "This is your kitchen. There are leftovers from last night's dinner on your IKEA's EKBACKEN workbench.");
        Room corridor = new Room("corridor", "There's a coat rack with your cagoule hanging at it. Your shoes are sitting underneath.");

        // Crée les liens entre les pièces du jeu
        bedroom.setDirection(Direction.East, bathroom);
        bathroom.setDirection(Direction.West, bedroom);
        bedroom.setDirection(Direction.North, corridor);
        corridor.setDirection(Direction.South, bedroom);
        corridor.setDirection(Direction.West, kitchen);
        kitchen.setDirection(Direction.East, corridor);

        // Ajoute les objets dans les pièces
        bedroom.addItem( createDesk() );
        bedroom.addItem( createBed() );
        kitchen.addItem( createFridge() );
        
        // Renvoie la liste des pièces
        return new Room[] {
            bedroom,
            bathroom,
            kitchen,
            corridor
        };
    }

    private static Item createDesk()
    {
        Item desk = new Item("desk", "This is your desk. It's very old.");
        desk.setSwitch("open", false);
        desk.addAction(Action.Examine,
            (item) -> {
                String text;
                if (item.getSwitch("open")) {
                    text = "The drawer is open.";
                } else {
                    text = "The drawer is closed.";
                }
                return text;
            }
        );
        desk.addAction(Action.Open,
            (item) -> {
                if (item.getSwitch("open")) {
                    return "The drawer is already open.";
                }
                item.setSwitch("open", true);
                return "You open the drawer.";
            }
        );
        desk.addAction(Action.Close,
            (item) -> {
                if (!item.getSwitch("open")) {
                    return "The drawer is already closed.";
                }
                item.setSwitch("open", false);
                return "You close the drawer.";
            }
        );
        desk.addAction(Action.Use,
            (item) -> {
                return "You don't need to work right now.";
            }
        );

        return desk;
    }

    private static Item createBed()
    {
        Item bed = new Item("bed", "This is your bed. This is where you spend most of your day.");
        bed.setSwitch("messy", true);
        bed.addAction(Action.Examine,
            (item) -> {
                String text;
                if (item.getSwitch("messy")) {
                    text = "Your bed is a mess.";
                } else {
                    text = "Its neatly tidied.";
                }
                return text;
            }
        );
        bed.addAction(Action.Tidy,
            (item) -> {
                if (!item.getSwitch("messy")) {
                    return "It's already tidy.";
                }
                item.setSwitch("messy", false);
                return "You take a minute to tidy your bed.";
            }
        );
        bed.addAction(Action.Use,
            (item) -> {
                item.setSwitch("messy", true);
                return "You take a quick nap. Your bed is messy again.";
            }
        );

        return bed;
    }

    private static Item createFridge()
    {
        Item fridge = new Item("fridge", "It's an American-style fridge with a cutting-edge digital pad.");
        fridge.setSwitch("open", false);
        fridge.addAction(Action.Examine,
            (item) -> {
                String text;
                if (item.getSwitch("open")) {
                    text = "It's empty. You'll need to do some shopping.";
                } else {
                    text = "It's closed. You should open it to find out what's inside.";
                }
                return text;
            }
        );
        fridge.addAction(Action.Open,
            (item) -> {
                if (item.getSwitch("open")) {
                    return "It's already open.";
                }
                item.setSwitch("open", true);
                return "You open the fridge.";
            }
        );
        fridge.addAction(Action.Close,
            (item) -> {
                if (!item.getSwitch("open")) {
                    return "It's already closed.";
                }
                item.setSwitch("open", false);
                return "You close the fridge.";
            }
        );

        return fridge;
    }
}
