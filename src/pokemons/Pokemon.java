package pokemons;

import items.Item;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pokemon {
    private String name;
    private int baseHp;
    private int hp;
    private Integer attack;
    private Integer specialAttack;
    private int defense;
    private int specialDefense;
    private Ability[] abilities;
    private boolean stunned = false;
    private boolean canDodge = false;
    private boolean fighting = false;
    private boolean finished = false;

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

    public Integer getHp() {
        return hp;
    }

    public void reset() {
        hp = baseHp;
        for (Ability ability : abilities) {
            if (ability != null)
                ability.setCooldown(0);
        }
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public boolean isFighting() {
        return fighting;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isDefeated() {
        return hp <= 0;
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
        if (canDodge || damage < defense) {
//            System.out.println(this.name + " dodged!");
            return;
        }
        hp -= damage - defense;
    }

    public void sufferSpecialAttack(int damage) {
        if (canDodge || damage < defense) {
//            System.out.println(this.name + " dodged!");
            return;
        }
        hp -= damage - specialDefense;
    }

    public void sufferAbilityDamage(int damage) {
        if (canDodge || damage < defense) {
//            System.out.println(this.name + " dodged!");
            return;
        }
        hp -= damage;
    }

    public void stun() {
        if (canDodge) {
            System.out.println(this.name + " dodged the stun ability!");
            return;
        }
        stunned = true;
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

    public void startTurn() {
        fighting = true;
        finished = false;
        stunned = false;
        for (Ability ability : abilities) {
            if (ability != null)
                ability.reduceCooldown();
        }
    }

    public void endTurn() {
        fighting = false;
        finished = false;
        canDodge = false;
    }

    public void preEndTurn() {
        finished = true;
    }

    public String toString() {
        return name + " " + hp + " " + attack + " " + specialAttack + " " + defense + " " + specialDefense + " " + Arrays.toString(abilities);
    }

    public void willDodge() {
        canDodge = true;
    }
}