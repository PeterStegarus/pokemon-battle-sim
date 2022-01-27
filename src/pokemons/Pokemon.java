package pokemons;

import game.IPokemon;
import items.Item;

import java.util.Arrays;

public class Pokemon implements IPokemon {
    private String name;
    private int baseHp;
    private int hp;
    private Integer attack;
    private Integer specialAttack;
    private int defense;
    private int specialDefense;
    private Ability[] abilities;

    public Pokemon(String name, int hp, Integer attack, Integer specialAttack, int defense, int specialDefense) {
        this.name = name;
        this.baseHp = hp;
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

    public void reset() {
        hp = baseHp;
        for (Ability ability : abilities) {
            if (ability != null)
                ability.setCooldown(0);
        }
    }

    private void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public void sufferDamage(int damage) {
        hp -= damage;
    }

    public void addItem(Item item) {
        baseHp += item.getHp();
        hp = baseHp;
        if (attack != null) attack += item.getAttack();
        if (specialAttack != null) specialAttack += item.getSpecialAttack();
        defense += item.getDefense();
        specialDefense += item.getSpecialDefense();
    }

    public void evolve() {
        addItem(new Item("_", 1, 1, 1, 1, 1));
    }

    public String toString() {
        return name + " " + hp + " " + attack + " " + specialAttack + " " + defense + " " + specialDefense + " " + Arrays.toString(abilities);
    }
}