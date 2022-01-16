package pokemons;

import items.Item;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pokemon {
    private String name;
    private Integer hp;
    private Integer attack;
    private Integer specialAttack;
    private int defense;
    private int specialDefense;
    private Ability[] abilities;
    private boolean stunned = false;
    private boolean canDodge = false;
    private boolean fighting = false;

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

    public boolean isFighting() {
        return fighting;
    }

    public Integer getHp() {
        return hp;
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

    public boolean isStunned() {
        return stunned;
    }

    public boolean canDodge() {
        return canDodge;
    }

    public void sufferAttack(int damage) {
        if (canDodge) return;
        hp -= damage - defense;
    }

    public void sufferSpecialAttack(int damage) {
        if (canDodge) return;
        hp -= damage - specialDefense;
    }

    public void sufferAbilityDamage(int damage) {
        if (canDodge) return;
        hp -= damage;
    }

    public void stun() {
        if (canDodge) return;
        stunned = true;
    }

    public void addItem(Item item) {
        hp += item.getHp();
        if (attack != null) attack += item.getAttack();
        if (specialAttack != null) specialAttack += item.getSpecialAttack();
        defense += item.getDefense();
        specialDefense += item.getSpecialDefense();
    }

    public void evolve() {
        addItem(new Item("_", 1, 1, 1, 1, 1));
    }

    public void startTurn() {
        fighting = true;
        stunned = false;
        for (Ability ability : abilities) {
            if (ability != null)
                ability.reduceCooldown();
        }
    }

    public void endTurn() {
        fighting = false;
        canDodge = false;
    }

    public String toString() {
        return name + " " + hp + " " + attack + " " + specialAttack + " " + defense + " " + specialDefense + " " + Arrays.toString(abilities);
    }

    public void willDodge() {
        canDodge = true;
    }
}
