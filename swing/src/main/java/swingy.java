import Model.Characters.Heroes.Harry;
import Model.Characters.Villains.Kyle;
import Model.Map;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class swingy {

    public static void main(String[] args) {

        System.out.println("__________");
        System.out.println("WELCOME");
        System.out.println("__________");
        System.out.println("CREATE A HERO[1]");
        System.out.println("LOAD A HERO[2]");
        Scanner scan = new Scanner(System.in);
        String first = scan.next();
        if (first.equals("1")) {
            Harry Hero = new Harry(1, 100, 1000);
            System.out.println("WELCOME TO HOGWARTS " + String.valueOf(Hero.getClass()).substring(String.valueOf(Hero.getClass()).lastIndexOf('.') + 1));
        }
        if (first.equals("2")) {
            System.out.println("FUNCTIONALITY ONLY AVAILABLE IN NEXT UPDATE");
            System.exit(0);

        }

//Character Instantiation

        Harry Hero = new Harry(1, 1000, 1000);///remove when above if stmt is completed
//Map Instantiation
        Map map = new Map(Hero.getLevel());
        String[][] layout = map.getLayout();
        int size = map.getSize();

        System.out.println("Enter 1 to quit");
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
                System.out.println("WINNER WINNER CHICKEN DINNER");
                System.exit(0);
            }
            run = false;
            String move = in.next();
            if (move.equals("b")) {
                System.out.println("THIS.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("IS.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("SPARTA.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /// COMMENCE BATTLE HERE
                int fighter = 0;
                for (int i = 0; i < size; i++) {
                    if (x == VillainCoords[i][0] && y == VillainCoords[i][1]) {
                        fighter = i;
                        break;
                    }
                }
                int hit = 1;
                System.out.println("Villain Attack: " + Villains[fighter].getAttack());
                System.out.println("Villain Defense: " + Villains[fighter].getDefense());
                while (Hero.getHP() > 0 && Villains[fighter].getHP() > 0) {
                    System.out.println(" HERO Remaining Health: " + Hero.getHP()+"      VILLAIN HEALTH: "+Villains[fighter].getHP());
                    if (hit == 1) {
                        if (Villains[fighter].getDefense() > 0) {

                            System.out.println("Remaining VILLAIN Defense: " + Villains[fighter].getDefense());
                            Villains[fighter].setDefense(Villains[fighter].getDefense() - Hero.getAttack());
                        } else {
                            if ( Villains[fighter].getDefense() < 0){
                                Villains[fighter].setHP(Villains[fighter].getHP() + Villains[fighter].getDefense());
                                Villains[fighter].setDefense(0);
                            }
                            Villains[fighter].setHP(Villains[fighter].getHP() - Hero.getAttack());
                        }
                        hit = 0;
                    } else {
                        if (Hero.getDefense() > 0) {
                            System.out.println("Remaining HERO Defense: " + Hero.getDefense());
                                Hero.setDefense(Hero.getDefense() - Villains[fighter].getAttack());
                        } else {
                            if ( Hero.getDefense() < 0){
                                System.out.println(Hero.getDefense());
                                Hero.setHP(Hero.getHP() + Hero.getDefense());
                                Hero.setDefense(0);
                            }
                            Hero.setHP(Hero.getHP() - Villains[fighter].getAttack());
                        }
                        hit = 1;
                    }
                }

                if (Hero.getHP() <= 0) {
                    System.out.println("U DEAD");
                    System.exit(0);
                }
                if (Villains[fighter].getHP() <= 0) {

                    int artifact = 0;
                    if ( ThreadLocalRandom.current().nextInt(0,100) % 3 == 0)
                    {
                        long incAttack = ThreadLocalRandom.current().nextLong(Math.round(Hero.getAttack() * 0.10) ,Math.round(Hero.getAttack() * 0.25 ));
                        long incDefense = ThreadLocalRandom.current().nextLong(Math.round(Hero.getDefense() * 0.10) ,Math.round(Hero.getDefense() * 0.25 ));
                        long incHP = ThreadLocalRandom.current().nextLong(Math.round(Hero.getHP() * 0.10) ,Math.round(Hero.getHP() * 0.25 ));
                        System.out.println("Raid the Villains lifeless body.");
                        System.out.println("Pick one of the 3 artifacts below: ");
                        System.out.println("[1] Weapon : Increase attack by " +  incAttack);
                        System.out.println("[2]Armor : Increase defense by "+ incDefense);
                        System.out.println("[3]Helm : Increase HP by "+ incHP);
                    scan = new Scanner(System.in);
                    first = scan.next();
                    if (first.equals('1')) {
                        
                    }
                    }
                    System.out.println("Remaining Health: " + Hero.getHP());
                    System.out.println("U WIN");
                    layout[VillainCoords[fighter][1]][VillainCoords[fighter][0]] = Hero.getType();
                    run = true;
                    layout[old_y][old_x] = ".";
                    Hero.setXP(Hero.getXP() + (Villains[fighter].getAttack()));
                    System.out.println("New XP" + Hero.getXP());
                    if (Hero.getXP() >= (((Hero.getLevel() + 1) * 1000) + (Math.pow(Hero.getLevel(), 2) * 450))) {
                        System.out.println("CONGRATULATIONS, YOU LEVELED UP");
                        //REGNERATE MAP AND RECENTER HERO
                        Hero.setLevel(Hero.getLevel() + 1);
                        map = new Map(Hero.getLevel());
                        layout = map.getLayout();
                        size = map.getSize();
                        x = Harry.InitialCoordinates(map.getSize());
                        old_x = 0;
                        old_y = 0;
                        y = x;
//REGENERATE VILLAINS FOR LEVEL UP
                        VillainCoords = new int[size][2];
                        Villains = new Kyle[size];
                        for (int i = 0; i < size; i++) {
                            Villains[i] = new Kyle(Hero.getLevel(), Hero.getHP(), Hero.getXP(), ThreadLocalRandom.current().nextInt(Hero.getAttack() - 50, Hero.getAttack() - 25), ThreadLocalRandom.current().nextInt(Hero.getDefense() - 75, Hero.getDefense() - 50), "V");
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
                    }
                }
                /// END BATTLE CODE
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
                    System.out.println("NAH FAM.");
                    System.out.println("U DEAD");
                    System.exit(0);
                    // CALL BATTLE CODE HERE
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
