package Controller;

import Model.Characters.Heroes.Harry;
import Model.Characters.Villains.Kyle;
import Model.Characters.utils;
import Model.Map.Map;
import View.messages;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class swingy {


    public static void main(String[] args) {
        messages Messages = new messages();
        Messages.Intro();
        utils Util = new utils();
        Harry Hero = newHero();

//Character Instantiation
//Map Instantiation
        Map map = new Map(Hero.getLevel());
        String[][] layout = map.getLayout();
        int size = map.getSize();
        boolean gameActive = true;// keeps track of if game is active when program needs to stop
        Scanner in = new Scanner(System.in);
        int x = Harry.InitialCoordinates(map.getSize());
        int old_x = 0;
        int old_y = 0;
        int y = x;
        boolean run = false;
//GENERATE VILLAINS             //////////////////////////////////////////////////////////////////////
        Kyle[] Villains = new Kyle[size];
        int[][] VillainCoords = new int[size][2];
        for (int i = 0; i < size; i++) {
            Villains[i] = new Kyle(Hero.getLevel(), Hero.getHP(), Hero.getXP(), ThreadLocalRandom.current().nextInt(Hero.getAttack() - 300, Hero.getAttack() - 250), ThreadLocalRandom.current().nextInt(Hero.getDefense() - 90, Hero.getDefense() - 75), "V");
            int rdm_x = x;
            int rdm_y = y;
            while (rdm_x == x || rdm_y == y) {
                rdm_x = ThreadLocalRandom.current().nextInt(0, size);
                rdm_y = ThreadLocalRandom.current().nextInt(0, size);
                if (layout[rdm_y][rdm_x] != ".") {
                    rdm_x = x;
                    rdm_y = y;
                }
            }
            layout[rdm_y][rdm_x] = Villains[i].getType();
            VillainCoords[i][0] = rdm_x;
            VillainCoords[i][1] = rdm_y;

        }
//END GENERATE VILLAINS        //////////////////////////////////////////////////////////////////////

//START GAME LOOP       //////////////////////////////////////////////////////////////
        boolean Collision = false;
        while (gameActive) {
            if ((x == size - 1 || y == size - 1 || x == 0 || y == 0) && (layout[y][x] == "." || layout[y][x] == Hero.getType())) {
                layout[y][x] = Hero.getType();
                if (!run) {
                    layout[old_y][old_x] = ".";
                }
                Util.clearScreen();
                map.PrintMap();
                Messages.PrintWinMessage(Hero);
                /// SAVE PLAYERS PROGRESS
                System.exit(0);
            }
            if (layout[y][x] != "." && !layout[y][x].equals(Hero.getType())) {

                Messages.BattleOrRun();
                Collision = true;

            } else {
                layout[y][x] = Hero.getType();
                if (!run) {
                    layout[old_y][old_x] = ".";
                }
                Util.clearScreen();
                map.PrintMap();
            }

            int fighter = 0;
            run = false;
            String move = in.next();
            ///// BATTLE STARTS
            while (Collision) {
                if (move.equals("b")) {
                    Messages.PrintStartBattle();
                    /// COMMENCE BATTLE HERE
                    for (int i = 0; i < size; i++) {
                        if (x == VillainCoords[i][0] && y == VillainCoords[i][1]) {
                            fighter = i;
                            break;
                        }
                    }
                    Messages.PrintVilStats(Villains[fighter].getAttack(), Villains[fighter].getDefense());
                    map = Hero.fight(Villains[fighter], VillainCoords, fighter, map, old_x, old_y);
                    run = true;
                    if (size < map.getSize()) {
                        size = map.getSize();
                        layout = map.getLayout();
                        Villains = Hero.generateVillains(map);
                        VillainCoords = Villains[0].getVillainCoords();
                        y = map.getY();
                        x = map.getX();
                    }
                    move = "battle";
                    Collision = false;
                    break;
                }
                if (move.equals("r")) {
                    int chance = ThreadLocalRandom.current().nextInt(1, 100);
                    ;
                    if (chance % 2 == 0) {
                        Messages.Flee();
                        run = true;
                        x = old_x;
                        y = old_y;
                    } else {
                        for (int i = 0; i < size; i++) {
                            if (x == VillainCoords[i][0] && y == VillainCoords[i][1]) {
                                fighter = i;
                                break;
                            }
                        }
                        Messages.Nah();
                        Messages.PrintVilStats(Villains[fighter].getAttack(), Villains[fighter].getDefense());
                        map = Hero.fight(Villains[fighter], VillainCoords, fighter, map, old_x, old_y);
                        run = true;
                        if (size < map.getSize()) {
                            size = map.getSize();
                            layout = map.getLayout();
                            Villains = Hero.generateVillains(map);
                            VillainCoords = Villains[0].getVillainCoords();
                            y = map.getY();
                            x = map.getX();
                        }
                    }
                    move = "battle";
                    Collision = false;
                    break;
                } else {

                    Messages.InvalidInput();
                    Messages.BattleOrRun();
                    move = in.next();
                }
            }
            if (move.equals("1")) { //quits
                gameActive = false;
            } else if (move.equals("w")) { //movement commands
                old_y = y;
                old_x = x;
                y--;
            } else if (move.equals("s")) {
                old_y = y;
                old_x = x;
                y++;
            } else if (move.equals("a")) {
                old_y = y;
                old_x = x;
                x--;
            } else if (move.equals("d")) {
                old_y = y;
                old_x = x;
                x++;
            } else if (move.equals("battle")) {
                move = " ";
                old_y = y;
                old_x = x;
            } else {
                Messages.InvalidInput();
            }
        }
//END GAME LOOP          ///////////////////////////////////////////////////////////////////////
    }

    public static Harry newHero() {

        utils Utils = new utils();
        messages Messages = new messages();
        Harry Hero;
        Scanner scan = new Scanner(System.in);

        while (true) {
            String first = scan.next();
            if (first.equals("1")) {
                Messages.PickHero();

                while (true) {
                    String Second = scan.next();
                    if (Second.equals("1")) {
                        Hero = new Harry("Harry", 1, 1000, 1000, 600, 400, "H");
                        break;
                    } else if (Second.equals("2")) {
                        Hero = new Harry("Ron", 1, 900, 1000, 500, 300, "R");
                        break;
                    } else if (Second.equals("3")) {
                        Hero = new Harry("Hermoine", 1, 1250, 1000, 400, 600, "G");
                        break;
                    } else {
                        Messages.InvalidInput();
                    }
                }

                Utils.getUtils().SaveNewPlayer(Hero);
                break;
            } else if (first.equals("2")) {
                String[] Stats = Utils.LoadPlayer();
                Hero = new Harry(Stats[0], Integer.parseInt(Stats[1]), Integer.parseInt(Stats[2]), Integer.parseInt(Stats[3]), Integer.parseInt(Stats[4]), Integer.parseInt(Stats[5]), Stats[6]);
                break;
            } else {
                Messages.InvalidInput();
            }
        }
        Messages.quit();
        return (Hero);
    }
}
