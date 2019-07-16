package Controller;

import Model.Characters.Heroes.Harry;
import Model.Characters.Villains.Kyle;
import Model.Map.Map;
import View.messages;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class swingy {


    public static void main(String[] args) {
        messages Messages = new messages();
        Messages.Intro();

//Character Instantiation

        Harry Hero = new Harry(1, 1000, 1000);///remove when above if stmt is completed
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
        while (gameActive) {
            if (layout[y][x] != "." && layout[y][x] != "H") {
                System.out.println("BATTLE[B] OR RUN[R]");
            } else {
                layout[y][x] = Hero.getType();
                if (!run) {
                    layout[old_y][old_x] = ".";
                }
                map.PrintMap();
            }
            if ((x == size - 1 || y == size - 1 || x == 0 || y == 0) && (layout[y][x] == "." || layout[y][x] == "H")) {
                Messages.PrintWinMessage();
                /// SAVE PLAYERS PROGRESS
                System.exit(0);
            }
            int fighter = 0;
            run = false;
            String move = in.next();
            ///// BATTLE STARTS
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
                run = Hero.fight(Villains[fighter], VillainCoords, fighter, map, old_x, old_y, x, y, size);
            }
            if (move.equals("r")) {
                int chance = ThreadLocalRandom.current().nextInt(1, 100);
                ;
                if (chance % 2 == 0) {
                    System.out.println("*FLEES THE SCENE*");
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
                    System.out.println("NAH FAM");
                    Messages.PrintVilStats(Villains[fighter].getAttack(), Villains[fighter].getDefense());
                    run = Hero.fight(Villains[fighter], VillainCoords, fighter, map, old_x, old_y, x, y, size);
                }
            }
            old_y = y;
            old_x = x;
            if (move.equals("1")) { //quits
                gameActive = false;
            }
            if (move.equals("w")) { //movement commands
                y--;
            }
            if (move.equals("s")) {
                y++;
            }
            if (move.equals("a")) {
                x--;
            }
            if (move.equals("d")) {
                x++;
            }
        }
//END GAME LOOP          ///////////////////////////////////////////////////////////////////////
    }
}
