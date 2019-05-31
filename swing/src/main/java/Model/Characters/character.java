package Model.Characters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class character {
    private int Level;
    private int HP;
    private int XP;
    private int Attack;
    private int Defense;
    private String type;

    public character(int level, int HP, int XP, int Attack, int Defense, String type){
        this.Level = level;
        this.HP = HP;
        this.XP = XP;
        this.Attack = Attack;
        this.Defense = Defense;
        this.type = type;
    }

//    public abstract boolean inBattle(int Value);
 }
