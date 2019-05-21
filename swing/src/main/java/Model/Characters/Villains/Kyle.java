package Model.Characters.Villains;

import Model.Characters.character;

public class Kyle extends character {


    public Kyle(int level, int HP, int XP, int Attack, int Defense, String type) {
        super(level, HP, XP, Attack, Defense, type);
    }

    public boolean inBattle(int Value) {
        if (Value == 1)
            return true;
        else
            return false;
    }
}
