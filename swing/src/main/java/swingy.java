import Model.Characters.Heroes.bob;
import Model.Characters.Villains.Kyle;
import Model.Map;

import java.io.IOException;
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
            if (first.equals("1")){
                bob Hero = new bob(1,1,1,1,1,"H");
            }
            if (first.equals("2")){
                System.out.println("FUNCTIONALITY ONLY AVAILABLE IN NEXT UPDATE");
                System.exit(0);

            }

//Character Instantiation

        bob Hero = new bob(1,1,1,1,1,"H");
//Map Instantiation
        Map map = new Map(Hero.getLevel());
        String[][] layout = map.getLayout();
        int size = map.getSize();

//START HERO MOVEMENTS       //////////////////////////////////////////////////////////////
        System.out.println("Enter 1 to quit");
            boolean gameActive = true;// keeps track of if game is active when program needs to stop
            Scanner in = new Scanner(System.in);
            int x = bob.InitialCoordinates(map.getSize());
            int old_x = 0;
            int old_y =0;
            int y = x;// initial placement, doesn't matter
            boolean run = false;
//GENERATE VILLAINS             //////////////////////////////////////////////////////////////////////
        Kyle[] Villains = new Kyle[size];
        for (int i = 0; i < size; i++) {
            Villains[i] = new Kyle(Hero.getLevel(),Hero.getHP(),Hero.getXP(),Hero.getAttack(),Hero.getDefense(),"V");
            int rdm_x = x;
            int rdm_y = y;
            while (rdm_x == x || rdm_y == y) {
                rdm_x = ThreadLocalRandom.current().nextInt(0, size);
                rdm_y = ThreadLocalRandom.current().nextInt(0, size);
            }
            layout[rdm_x][rdm_y] = Villains[i].getType();
        }
//END GENERATE VILLAINS        //////////////////////////////////////////////////////////////////////
            while (gameActive) {
                if (layout[y][x] != "." && layout[y][x] != "H")
                {
                    System.out.println("BATTLE[B] OR RUN[R]");
                }
                else {
                    layout[y][x] = Hero.getType();
                    if (!run) {
                        layout[old_y][old_x] = ".";
                    }
                    map.PrintMap();
                }
                run = false;
                String move = in.next();
                if (move.equals("b")){
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
                    /// COMMENCE BATTLE HERE
                    System.out.println("U DEAD");
                    System.exit(0);


                    /// END BATTLE CODE
                }
                if (move.equals("r")){
                    int chance = ThreadLocalRandom.current().nextInt(1, 100);;
                    if (chance % 2 == 0){
                        System.out.println("*FLEES THE SCENE*");
                        run = true;
                        x = old_x;
                        y = old_y;
                    }
                    else{
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
//END HERO MOVEMENTS            ///////////////////////////////////////////////////////////////////////
        }
}
