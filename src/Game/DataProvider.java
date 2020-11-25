package Game;

import Game.Inventory;

public class DataProvider
{
    public static Room[] createRooms() {
        // Crée les pièces du jeu
        Room bedroom = new Room("bedroom", "This is your bedroom. It's decorated with cute pink striped wallpaper. Your king-sized bed lies in a corner, and your desk sits opposite to it.");
        Room bathroom = new Room("bathroom", "This is your bathroom. It has ocean-blue tiling on the walls, and there's a tub (and not a shower).");
        Room kitchen = new Room("kitchen", "This is your kitchen. There are leftovers from last night's dinner on your IKEA's EKBACKEN workbench.");
        Room corridor = new Room("corridor", "There's a coat rack with your cagoule hanging at it. Your shoes are sitting underneath.");
        Room garage = new Room("garage", "This is your garage.");

        // Crée les liens entre les pièces du jeu
        bedroom.setDirection(Direction.East, bathroom);
        bathroom.setDirection(Direction.West, bedroom);
        bedroom.setDirection(Direction.North, corridor);
        corridor.setDirection(Direction.South, bedroom);
        corridor.setDirection(Direction.West, kitchen);
        kitchen.setDirection(Direction.East, corridor);
        garage.setDirection(Direction.South, corridor);
        corridor.setDirection(Direction.North, garage);

        // Ajoute les objets dans les pièces
        bedroom.addItem( createDesk() );
        bedroom.addItem( createBed() );
        kitchen.addItem( createFridge() );
        kitchen.addItem( createCarKeys() );
        bathroom.addItem( createToothbrush() );
        garage.addItem( createCar() );
        
        // Renvoie la liste des pièces
        return new Room[] {
            bedroom,
            bathroom,
            kitchen,
            corridor,
            garage
        };
    }

    private static Item createDesk()
    {
        Item desk = new Item("desk", "This is your desk. It's very old.");
        desk.setSwitch("open", false);
        desk.addAction(Action.Examine,
            (parameters) -> {
                String text;
                if (parameters.getItem().getSwitch("open")) {
                    text = "The drawer is open.";
                } else {
                    text = "The drawer is closed.";
                }
                return text;
            }
        );
        desk.addAction(Action.Open,
            (parameters) -> {
                if (parameters.getItem().getSwitch("open")) {
                    return "The drawer is already open.";
                }
                parameters.getItem().setSwitch("open", true);
                return "You open the drawer.";
            }
        );
        desk.addAction(Action.Close,
            (parameters) -> {
                if (!parameters.getItem().getSwitch("open")) {
                    return "The drawer is already closed.";
                }
                parameters.getItem().setSwitch("open", false);
                return "You close the drawer.";
            }
        );
        desk.addAction(Action.Use,
            (iparameterstparametersem) -> {
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
            (parameters) -> {
                String text;
                if (parameters.getItem().getSwitch("messy")) {
                    text = "Your bed is a mess.";
                } else {
                    text = "Its neatly tidied.";
                }
                return text;
            }
        );
        bed.addAction(Action.Tidy,
            (parameters) -> {
                if (!parameters.getItem().getSwitch("messy")) {
                    return "It's already tidy.";
                }
                parameters.getItem().setSwitch("messy", false);
                return "You take a minute to tidy your bed.";
            }
        );
        bed.addAction(Action.Use,
            (parameters) -> {
                parameters.getItem().setSwitch("messy", true);
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
            (parameters) -> {
                String text;
                if (parameters.getItem().getSwitch("open")) {
                    text = "It's empty. You'll need to do some shopping.";
                } else {
                    text = "It's closed. You should open it to find out what's inside.";
                }
                return text;
            }
        );
        fridge.addAction(Action.Open,
            (parameters) -> {
                if (parameters.getItem().getSwitch("open")) {
                    return "It's already open.";
                }
                parameters.getItem().setSwitch("open", true);
                return "You open the fridge.";
            }
        );
        fridge.addAction(Action.Close,
            (parameters) -> {
                if (!parameters.getItem().getSwitch("open")) {
                    return "It's already closed.";
                }
                parameters.getItem().setSwitch("open", false);
                return "You close the fridge.";
            }
        );

        return fridge;
    }

    private static Item createToothbrush()
    {
        Item toothbrush = new Item("toothbrush", "This is your toothbrush. It's full of surprises.");
        toothbrush.addAction(Action.PickUp,
            (parameters) -> {
                parameters.addItemToInventory();

                return "You pick up the toothbrush.";
            }
        );

        return toothbrush;
    }

    private static Item createCar()
    {
        Item car = new Item("car", "This is your car. It's Renault Twingo. You bought a cheap car because you had to invest in a good fridge.");
        car.setSwitch("open", false);
        car.addAction(Action.Open,
            (parameters) -> {
                if (parameters.getItem().getSwitch("open")) {
                    return "The car is already open.";
                }
                if (parameters.getGame().getInventory().containsNamed("car keys")) {
                    parameters.getItem().setSwitch("open", true);
                    return "You open the car.";
                }
                return "You need the keys to open the car. Where could you have hidden them?";
            }
        );

        return car;
    }

    private static Item createCarKeys()
    {
        Item carKeys = new Item("car keys", "These are the keys that open your Renault Twingo.");
        carKeys.addAction(Action.PickUp,
            (parameters) -> {
                parameters.addItemToInventory();

                return "You pick up the car keys.";
            }
        );

        return carKeys;
    }
}
