package Model.Characters.Villains;

import Model.Characters.character;

public class Kyle extends character {

    int[][] VillainCoords = new int[10][2];



    public int[][] getVillainCoords() {
        return VillainCoords;
    }


    public void setVillainCoords(int[][] villainCoords) {
        VillainCoords = villainCoords;
    }

    public Kyle(int level, int HP, int XP, int Attack, int Defense, String type) {
        super(level, HP, XP, Attack, Defense, type);
    }
}

