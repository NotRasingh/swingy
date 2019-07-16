package View;

import Model.Characters.Heroes.Harry;
import Model.Characters.Heroes.Hero;
import Model.Characters.utils;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class messages {


    public void PrintWinMessage(){
        System.out.println("WINNER WINNER CHICKEN DINNER");
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

    public void Intro(){

        utils Utils = new utils();
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
            System.out.println("Enter your Hero name: \n");
            first = scan.next();
            Utils.SaveNewPlayer(first);
            Harry Hero = new Harry(1, 100, 1000);
//            System.out.printf("WELCOME TO HOGWARTS %s%n", String.valueOf(Model.Characters.Heroes.Hero.getClass()).substring(String.valueOf(Hero.getClass()).lastIndexOf('.') + 1));
            System.out.printf("WELCOME \n");
            // INSERT NEW HERO TO DB
        }
        if (first.equals("2")) {
            System.out.println("FUNCTIONALITY ONLY AVAILABLE IN NEXT UPDATE");
            Utils.LoadPlayer();
            System.exit(0);
            ///PROMPT USER FOR HERO NAME
            ///LOAD HERO STATS FROM DB IF EXISTS
            /// CREATE NEW INSTANCE OF HERO CLASS BASED ON LOADED STATS
        }
        System.out.println("Enter 1 to quit\n");
    }
}
