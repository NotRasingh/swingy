package Model.Characters.Heroes;

import Model.Characters.character;
import Model.Characters.Villains.Kyle;
import Model.Characters.utils;
import Model.Map.Map;
import View.messages;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Harry extends character {

    utils Utils;
    private int HeroCoordinates[] = new int[2];
    private String Weapon;
    private String Name;


    public Harry() {
        super(1, 1000, 1000, 600, 400, "H");
        this.Weapon = "Wand";
    }


    public Harry(String Name, int level, int HP, int XP, int Attack, int Defense, String Type) {
        super(level, HP, XP, Attack, Defense, Type);
        this.Weapon = "Wand";
        this.Name = Name;
    }

//    public Harry() {
//        super();
//    }


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


    public Map fight(Kyle Vil, int[][] VillainCoords, int fighter, Map map, int old_x, int old_y) {
        int hit = ThreadLocalRandom.current().nextInt(0, 1);
        messages Messages = new messages();
        String[][] layout = map.getLayout();
        while (this.getHP() > 0 && Vil.getHP() > 0) {
            Messages.Health(this, Vil);
            if (hit == 1) {
                if (Vil.getDefense() > 0) {
                    Messages.VillainDefense(Vil);
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
                    Messages.HeroDefense(this);
                    this.setDefense(this.getDefense() - Vil.getAttack());
                } else {
                    if (this.getDefense() < 0) {
//                        System.out.println(this.getDefense());
                        this.setHP(this.getHP() + this.getDefense());
                        this.setDefense(0);
                    }
                    this.setHP(this.getHP() - Vil.getAttack());
                }
                hit = 1;
            }
        }
        if (this.getHP() <= 0) {
            Messages.Dead();
            this.setHP(1000);
            Utils.getUtils().SavePlayer(this);
            System.exit(0);
        }
        if (this.getDefense() < 0) {
            this.setDefense(0);
        }
        if (Vil.getHP() <= 0) {

            int artifact = 0;
            Messages.WinBattle(this);
            layout[VillainCoords[fighter][1]][VillainCoords[fighter][0]] = this.getType();
            layout[old_y][old_x] = ".";
            //run = true; MIGHT BE IMPORTANT
            this.setXP(this.getXP() + (Vil.getAttack()));
            Messages.NewXP(this);
            if (ThreadLocalRandom.current().nextInt(0, 100) % 2 == 0) {
                Messages.PickArtifact();
                Scanner scan = new Scanner(System.in);
                String first = scan.next();
                while (true) {
                    if (first.equals("1")) {
                        Messages.OldAttack(this);
                        this.setAttack(this.getAttack() + 150);
                        Messages.NewAttack(this);

                        break;
                    } else if (first.equals("2")) {
                        Messages.OldDefense(this);
                        this.setDefense(this.getDefense() + 150);
                        Messages.NewDefense(this);
                        break;
                    } else if (first.equals("3")) {
                        Messages.OldHP(this);
                        this.setHP(this.getHP() + 150);
                        Messages.NewHP(this);
                        break;
                    } else
                        Messages.InvalidInput();
                }
            }
            if (this.getXP() >= (((this.getLevel() + 1) * 1000) + (Math.pow(this.getLevel(), 2) * 450))) {
                Messages.Congrats();
                //REGENERATE MAP AND RECENTER HERO
                this.setLevel(this.getLevel() + 1);
                map = new Map(this.getLevel());
                layout = map.getLayout();
                int size = map.getSize();
                map.setX(InitialCoordinates(map.getSize()));
                int x = map.getX();
                map.setY(map.getX());
                int y = map.getY();
//REGENERATE VILLAINS FOR LEVEL UP
                /// UPDATE PLAYERS PROGRESS IN DB/TXTFILE
            }
        }
        return map;
        /// END BATTLE CODE
    }

    public Kyle[] generateVillains(Map map) {

        int size = map.getSize();
        String[][] layout = map.getLayout();
        int x = map.getX();
        int y = map.getY();
        int[][] VillainCoords = new int[size][2];
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
            map.setLayout(rdm_y, rdm_x, Villains[i].getType());
            VillainCoords[i][0] = rdm_x;
            VillainCoords[i][1] = rdm_y;
            layout[y][x] = this.getType();
        }
        Villains[0].setVillainCoords(VillainCoords);
        return Villains;
    }
}

