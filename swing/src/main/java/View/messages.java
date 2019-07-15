package View;

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
}
