package View;

import Model.Characters.Heroes.Harry;
import Model.Characters.utils;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class messages {

utils Utils;

    public void PrintWinMessage(Harry hero){
        System.out.println("WINNER WINNER CHICKEN DINNER");
        hero.setLevel(hero.getLevel() +1);
        hero.setXP((int) (((hero.getLevel() + 1) * 1000) + (Math.pow(hero.getLevel(), 2) * 450)));
        Utils.getUtils().SavePlayer(hero);
    }

    public void PrintVilStats(int Att, int Def){
        System.out.println("Villain Attack: " + Att);
        System.out.println("Villain Defense: " + Def);
    }

    public void PrintStartBattle(){
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
    }

    public Harry Intro(){

        utils Utils = new utils();
        Harry Hero = new Harry();
        System.out.println("__________");
        System.out.println("WELCOME");
        System.out.println("__________");
        System.out.println("CREATE A HERO[1]");
        System.out.println("LOAD A HERO[2]");
        Scanner scan = new Scanner(System.in);
        String first = scan.next();
        if (first.equals("1")) {
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir") + "\n");
//            Scanner scan = new Scanner(System.in);
            Hero = new Harry();
            Utils.getUtils().SaveNewPlayer(Hero);
//            System.out.printf("WELCOME TO HOGWARTS %s%n", String.valueOf(Model.Characters.Heroes.Hero.getClass()).substring(String.valueOf(Hero.getClass()).lastIndexOf('.') + 1));
            System.out.printf("WELCOME \n");
            // INSERT NEW HERO TO DB
        }
        if (first.equals("2")) {
           String[] Stats = Utils.LoadPlayer();
            Hero = new Harry(Integer.parseInt(Stats[1]), Integer.parseInt(Stats[2]),Integer.parseInt(Stats[3]), Integer.parseInt(Stats[4]), Integer.parseInt(Stats[5]), Stats[6]);
            System.out.printf("WELCOME \n");
            /// CREATE NEW INSTANCE OF HERO CLASS BASED ON LOADED STATS
        }
        System.out.println("Enter 1 to quit\n");
        return (Hero);
    }
}
