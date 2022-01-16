package pokemons;

import items.Item;

import java.util.Arrays;

public class Pokemon {
    private String name;
    private int hp;
    private Integer attack;
    private Integer specialAttack;
    private int defense;
    private int specialDefense;
    private boolean stunned = false;
    private Ability[] abilities;

    public Pokemon(String name, int hp, Integer attack, Integer specialAttack, int defense, int specialDefense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public void addItem(Item item) {
        hp += item.getHp();
        if (attack != null) attack += item.getAttack();
        if (specialAttack != null) specialAttack += item.getSpecialAttack();
        defense += item.getDefense();
        specialDefense += item.getSpecialDefense();
    }

    public String toString() {
        return name + " " + hp + " " + attack + " " + specialAttack + " " + defense + " " + specialDefense + " " + Arrays.toString(abilities);
    }
}
