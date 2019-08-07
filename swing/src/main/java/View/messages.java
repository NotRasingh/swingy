package View;

import Model.Characters.Heroes.Harry;
import Model.Characters.Villains.Kyle;
import Model.Characters.utils;
import lombok.NonNull;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class messages {

utils Utils;

    public void InvalidInput() {
        System.out.println("Invalid Input \n");
    }

//    @NonNull
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

    public void Intro(){
        System.out.println("__________");
        System.out.println("WELCOME");
        System.out.println("__________");
        System.out.println("CREATE A HERO[1]");
        System.out.println("LOAD A HERO[2]");
    }

    public void PickHero() {
        System.out.println("PICK A HERO");
        System.out.println("_________________________");
        System.out.println("[1] HARRY");
        System.out.println("[2] RONALD");
        System.out.println("[3] HERMOINE");
    }

    public void PickArtifact() {
        System.out.println("Raid the Villains lifeless body.");
        System.out.println("Pick one of the 3 artifacts below: ");
        System.out.println("[1] Weapon : Increase attack by 150");
        System.out.println("[2]Armor : Increase defense by 150");
        System.out.println("[3]Helm : Increase HP by 150");
    }

    public void OldAttack(Harry hero) {
        System.out.println("OLD ATTACK: " + hero.getAttack());
    }

    public void NewAttack(Harry harry) {
        System.out.println("New ATTACK: " + harry.getAttack());
    }

    public void OldDefense(Harry harry) {
        System.out.println("OLD DEFENSE: " + harry.getDefense());
    }

    public void NewDefense(Harry harry) {
        System.out.println("NEW DEFENSE: " + harry.getDefense());
    }

    public void NewHP(Harry harry) {
        System.out.println("NEW HP: " + harry.getHP());
    }

    public void OldHP(Harry harry) {
        System.out.println("OLD HP: " + harry.getHP());
    }

    public void Congrats() {
        System.out.println("CONGRATULATIONS, YOU LEVELED UP");
    }

    public void Dead() {
        System.out.println("YOU DIED");
    }

    public void WinBattle(Harry hero) {
        System.out.println("Remaining Health: " + hero.getHP() + "\n");
        System.out.println("U WIN");
    }

    public void NewXP(Harry harry) {

        System.out.println("New XP: " + harry.getXP() + "\n");
    }

    public void HeroDefense(Harry harry) {
        System.out.println("Remaining HERO Defense: " + harry.getDefense() + "\n");
    }

    public void VillainDefense(Kyle vil) {
        System.out.println("Remaining VILLAIN Defense: " + vil.getDefense() + "\n");
    }

    public void Health(Harry harry, Kyle vil) {
        System.out.println("HERO Remaining Health: " + harry.getHP() + " || VILLAIN HEALTH: " + vil.getHP() + "\n");
    }

    public void BattleOrRun() {
        System.out.println("BATTLE[B] OR RUN[R]");
    }

    public void quit() {
        System.out.println("Enter 1 to quit\n");
    }

    public void Flee() {
        System.out.println("*FLEES THE SCENE*");
    }

    public void Nah() {
        System.out.println("NAH FAM");
    }

    public void EnterName() {
        System.out.println("Enter your Hero name: \n");
    }

    public void NameTaken() {

        System.out.println("Name is Taken Enter a new name: ");
    }

    public void FileException(String message) {
        System.out.println(message);
    }

    public void LoadPlayer() {
        System.out.println("Enter Number of the Player you wish to load");
    }

    public void PrintStat(String toString) {

        System.out.println(toString);
    }

    public void printCoord(String s) {

        System.out.print(s);
    }
    public void printBlank() {

        System.out.println("");
    }
}
