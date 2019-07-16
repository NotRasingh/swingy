package Model.Characters;


import Model.filehandling.ReadFile;
import Model.filehandling.WriteFile;

import java.io.IOException;
import java.util.Scanner;

public class utils {


    public void SaveNewPlayer(String Name) {
        Scanner scan = new Scanner(System.in);
        try {
            ReadFile file = new ReadFile("GameProgress");
            String[] aryLines = file.OpenFile();

            int i = 0;
            while (i < aryLines.length) {
                if (aryLines[i].contains(Name)) {
                    System.out.println("Name is Taken Enter a new name: ");
                    Name = scan.next();
                    i = 0;
                }
                i++;
            }
            //WRITING NEW NAME TO FILE
            try {
                WriteFile data = new WriteFile("GameProgress", true);
                data.writeToFile(Name);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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
                if (Load > 0 && Load <= aryLines.length){
                    break;
                }
            }
            System.out.println(aryLines[Load-1]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
