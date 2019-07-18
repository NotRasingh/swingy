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

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public int getDefense() {
        return Defense;
    }

    public void setDefense(int defense) {
        Defense = defense;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
