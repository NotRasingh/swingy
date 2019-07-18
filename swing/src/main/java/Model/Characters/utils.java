package Model.Characters;


import Model.Characters.Heroes.Harry;
import Model.filehandling.ReadFile;
import Model.filehandling.WriteFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class utils {
    String Name;

    public void SaveNewPlayer(Harry hero) {
        Scanner scan = new Scanner(System.in);
        try {

            ReadFile file = new ReadFile("GameProgress");
            String[] aryLines = file.OpenFile();
            System.out.println("Enter your Hero name: \n");
            this.Name = scan.next();
            int i = 0;
            while (i < aryLines.length) {
                if (aryLines[i].contains(this.Name + ',')) {
                    System.out.println("Name is Taken Enter a new name: ");
                    this.Name = scan.next();
                    i = 0;
                }
                i++;
            }
            //WRITING NEW NAME TO FILE
            WritePlayer(hero);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void SavePlayer(Harry hero) {
        try {
            ReadFile file = new ReadFile("GameProgress");
            String[] aryLines = file.OpenFile();
            int i = 0;
            while (i < aryLines.length) {
                if (aryLines[i].contains(this.Name + ',')) {
                    WritePlayer(hero);
                }
                i++;
            }
            //WRITING NAME TO FILE
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void WritePlayer(Harry hero) {
        try {
            WriteFile data = new WriteFile("GameProgress", true);
            String PlayerStats = this.Name + ',' + hero.getLevel() + ',' + hero.getHP() + ',' + hero.getXP() + ',' + hero.getAttack() + ',' + hero.getDefense() + ',' + hero.getType();
            data.writeToFile(PlayerStats);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void LoadPlayer() {
        Scanner scan = new Scanner(System.in);
//        String Load = new String();
        int Load = -1;
        try {
            ReadFile file = new ReadFile("GameProgress");
            String[] aryLines = file.OpenFile();
            int i;
            for (i = 1; i <= aryLines.length; i++) {
                System.out.printf("%d.) %s%n", i, aryLines[i - 1]);
            }
            boolean tru = true;
            while (tru) {
                System.out.println("Enter Number of the Player you wish to load");
                Load = Integer.parseInt(scan.next());
                if (Load > 0 && Load <= aryLines.length) {
                    break;
                }
            }
            String Stats[] = aryLines[Load - 1].split(",");
            System.out.println(Arrays.toString(Stats));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
