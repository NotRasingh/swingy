package Model.Characters.Heroes;

import Model.Characters.character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Harry extends character {

    String Weapon;
    public Harry(int level, int HP, int XP) {
        super(level, HP, XP, 600, 400, "H");
        this.Weapon = "Wand";
    }


    @Override
    public boolean inBattle(int Value) {
        if (Value == 1)
            return true;
        else
            return false;
    }

    public static int InitialCoordinates(int size) {
        double result = (double)size/2;
        int fin = (int)result;
        return fin;
    }
}
