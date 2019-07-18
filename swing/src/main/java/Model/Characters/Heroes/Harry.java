package Model.Characters.Heroes;

import Model.Characters.character;
import Model.Characters.Villains.Kyle;
import Model.Characters.utils;
import Model.Map.Map;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Harry extends character {

    utils Utils = new utils();
    private int HeroCoordinates[] = new int[2];
    String Weapon;


    public Harry(int level, int HP, int XP) {
        super(level, HP, XP, 600, 400, "H");
        this.Weapon = "Wand";
    }


//    @Override
//    public boolean inBattle(int Value) {
//        if (Value == 1)
//            return true;
//        else
//            return false;
//    }

    public static int InitialCoordinates(int size) {
        double result = (double) size / 2;
        int fin = (int) result;
        return fin;
    }

    public void setHeroCoordinates(int x, int y) {
        this.HeroCoordinates[0] = x;
        this.HeroCoordinates[1] = y;
    }


    public boolean fight(Kyle Vil, int[][] VillainCoords, int fighter, Map map, int old_x, int old_y, int x, int y, int size) {
        int hit = 1;
        String[][] layout = map.getLayout();
        while (this.getHP() > 0 && Vil.getHP() > 0) {
            System.out.println(" HERO Remaining Health: " + this.getHP() + "      VILLAIN HEALTH: " + Vil.getHP());
            if (hit == 1) {
                if (Vil.getDefense() > 0) {

                    System.out.println("Remaining VILLAIN Defense: " + Vil.getDefense());
                    Vil.setDefense(Vil.getDefense() - this.getAttack());
                } else {
                    if (Vil.getDefense() < 0) {
                        Vil.setHP(Vil.getHP() + Vil.getDefense());
                        Vil.setDefense(0);
                    }
                    Vil.setHP(Vil.getHP() - this.getAttack());
                }
                hit = 0;
            } else {
                if (this.getDefense() > 0) {
                    System.out.println("Remaining HERO Defense: " + this.getDefense());
                    this.setDefense(this.getDefense() - Vil.getAttack());
                } else {
                    if (this.getDefense() < 0) {
                        System.out.println(this.getDefense());
                        this.setHP(this.getHP() + this.getDefense());
                        this.setDefense(0);
                    }
                    this.setHP(this.getHP() - Vil.getAttack());
                }
                hit = 1;
            }
        }
        if (this.getHP() <= 0) {
            System.out.println("U DEAD");
            Utils.SavePlayer(this);
            System.exit(0);
        }
        if (Vil.getHP() <= 0) {

            int artifact = 0;
            System.out.println("Remaining Health: " + this.getHP());
            System.out.println("U WIN");
            layout[VillainCoords[fighter][1]][VillainCoords[fighter][0]] = this.getType();
            layout[old_y][old_x] = ".";
            //run = true; MIGHT BE IMPORTANT
            this.setXP(this.getXP() + (Vil.getAttack()));
            System.out.println("New XP: " + this.getXP());
            if (ThreadLocalRandom.current().nextInt(0, 100) % 2 == 0) {
//                        long incAttack = ThreadLocalRandom.current().nextLong(Math.round(Hero.getAttack() * 0.10) ,Math.round(Hero.getAttack() * 0.25 ));
//                        long incDefense = ThreadLocalRandom.current().nextLong(Math.round(Hero.getDefense() * 0.10) ,Math.round(Hero.getDefense() * 0.25 ));
//                        long incHP = ThreadLocalRandom.current().nextLong(Math.round(Hero.getHP() * 0.10) ,Math.round(Hero.getHP() * 0.25 ));
                System.out.println("Raid the Villains lifeless body.");
                System.out.println("Pick one of the 3 artifacts below: ");
                System.out.println("[1] Weapon : Increase attack by 150");
                System.out.println("[2]Armor : Increase defense by 150");
                System.out.println("[3]Helm : Increase HP by 150");
                Scanner scan = new Scanner(System.in);
                String first = scan.next();
//                System.out.println("FIRST SCAN : "+ first);
//                System.out.println("MOVE SCAN : "+ move);
                if (first.equals("1")) {
                    System.out.println("OLD ATTACK: " + this.getAttack());
                    this.setAttack(this.getAttack() + 150);
                    System.out.println("New ATTACK: " + this.getAttack());
                }
                if (first.equals("2")) {
                    System.out.println("OLD DEFENSE: " + this.getDefense());
                    this.setDefense(this.getDefense() + 150);
                    System.out.println("NEW DEFENSE: " + this.getDefense());
                }
                if (first.equals("3")) {
                    System.out.println("OLD HP: " + this.getHP());
                    this.setHP(this.getHP() + 150);
                    System.out.println("NEW HP: " + this.getHP());
                }
            }
            if (this.getXP() >= (((this.getLevel() + 1) * 1000) + (Math.pow(this.getLevel(), 2) * 450))) {
                System.out.println("CONGRATULATIONS, YOU LEVELED UP");
                //REGENERATE MAP AND RECENTER HERO
                this.setLevel(this.getLevel() + 1);
                map = new Map(this.getLevel());
                layout = map.getLayout();
                size = map.getSize();
                x = InitialCoordinates(map.getSize());
                old_x = 0;
                old_y = 0;
                y = x;
//REGENERATE VILLAINS FOR LEVEL UP
                VillainCoords = new int[size][2];
                Kyle Villains[] = new Kyle[size];
                for (int i = 0; i < size; i++) {
                    Villains[i] = new Kyle(this.getLevel(), this.getHP(), this.getXP(), ThreadLocalRandom.current().nextInt(this.getAttack() - 50, this.getAttack() - 25), ThreadLocalRandom.current().nextInt(this.getDefense() - 75, this.getDefense() - 50), "V");
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
                /// UPDATE PLAYERS PROGRESS IN DB/TXTFILE
            }
        }
        return true;
        /// END BATTLE CODE
    }
}
