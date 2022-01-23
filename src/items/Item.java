package items;

public class Item {
    private String name;
    private int hp;
    private int attack;
    private int specialAttack;
    private int defense;
    private int specialDefense;

    public Item(String name, int hp, int attack, int specialAttack, int defense, int specialDefense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }
}
