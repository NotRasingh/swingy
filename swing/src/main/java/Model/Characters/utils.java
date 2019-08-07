package Model.Characters;


import Model.Characters.Heroes.Harry;
import Model.filehandling.ReadFile;
import Model.filehandling.WriteFile;
import View.messages;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;


public class utils {
    String Name;
    //    private static utils = new utils();
    static private utils Utils = new utils();
    messages Messages = new messages();

    public void SaveNewPlayer(Harry hero) {
        Scanner scan = new Scanner(System.in);
        try {

            ReadFile file = new ReadFile("GameProgress");
            String[] aryLines = file.OpenFile();

            Messages.EnterName();
            this.Name = scan.next();
            hero.setName(this.Name);
            int i = 0;
            while (i < aryLines.length) {
                if (aryLines[i].contains(this.Name + ',')) {
                    Messages.NameTaken();
                    this.Name = scan.next();
                    hero.setName(this.Name);
                    i = 0;
                }
                i++;
            }
            //WRITING NEW NAME TO FILE
            WritePlayer(hero);
        } catch (IOException e) {
            Messages.FileException(e.getMessage());
        }
    }

    public static utils getUtils() {
        return Utils;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void SavePlayer(Harry hero) {

//        Arrays.toString(hero);
//        System.out.println("NEW STATS: " + hero.getLevel() + ',' + hero.getHP() + ',' + hero.getXP() + ',' + hero.getAttack() + ',' + hero.getDefense() + ',' + hero.getType());
        int i = 0;
        try {
            ReadFile file = new ReadFile("GameProgress");
            String[] aryLines = file.OpenFile();
//            System.out.println("THIS NAME : " + hero.getName());
            while (i < aryLines.length) {
                if (aryLines[i].contains(hero.getName() + ',')) {
//                    WritePlayer(hero);
                    aryLines[i] = hero.getName() + ',' + hero.getLevel() + ',' + hero.getHP() + ',' + hero.getXP() + ',' + hero.getAttack() + ',' + hero.getDefense() + ',' + hero.getType();
                }
                i++;
            }
            i=0;
            WriteFile data = new WriteFile("GameProgress", true);
            data.clearFile("GameProgress");
            while (i < aryLines.length) {
                try {
                    data.writeToFile(aryLines[i]);
                } catch (IOException e) {
                    Messages.FileException(e.getMessage());
                }
                i++;
            }
            //WRITING NAME TO FILE
        } catch (IOException e) {
            Messages.FileException(e.getMessage());

        }

    }

    private void WritePlayer(Harry hero) {
        try {
            WriteFile data = new WriteFile("GameProgress", true);
            String PlayerStats = this.Name + ',' + hero.getLevel() + ',' + hero.getHP() + ',' + hero.getXP() + ',' + hero.getAttack() + ',' + hero.getDefense() + ',' + hero.getType();
            data.writeToFile(PlayerStats);
        } catch (IOException e) {
            Messages.FileException(e.getMessage());
        }
    }


    public String[] LoadPlayer() {
        Scanner scan = new Scanner(System.in);
        Dictionary Stats = new Hashtable();
        String[] Stat = new String[7];
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
                Messages.LoadPlayer();
                Load = Integer.parseInt(scan.next());
                if (Load > 0 && Load <= aryLines.length) {
                    break;
                }
            }
            Stat = aryLines[Load - 1].split(",");
            this.Name = Stat[0];
            Stats.put("name",Stat[0]);
            Stats.put("level",Stat[1]);
            Stats.put("hp",Stat[2]);
            Stats.put("xp",Stat[3]);
            Stats.put("attack",Stat[4]);
            Stats.put("defense",Stat[5]);
            Stats.put("char",Stat[6]);
            Messages.PrintStat(Arrays.toString(Stat));
        } catch (IOException e) {
            Messages.FileException(e.getMessage());
        }
        return (Stat);
    }
}
