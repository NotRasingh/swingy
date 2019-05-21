package Model.Characters.Heroes;

import Model.Characters.character;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class bob extends character {


    public bob(int level, int HP, int XP, int Attack, int Defense, String type) {
        super(level, HP, XP, Attack, Defense, type);
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
