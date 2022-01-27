package game;

import items.Item;
import pokemons.Ability;
import pokemons.Pokemon;

public class FighterPokemon implements IPokemon {
    private Pokemon pokemon;
    private boolean stunned = false;
    private boolean canDodge = false;
    private boolean fighting = false;
    private boolean finished = false;

    public FighterPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public boolean isFighting() {
        return fighting;
    }

    public boolean isFinished() {
        return finished;
    }

    public void startTurn() {
        fighting = true;
        finished = false;
        stunned = false;
        Ability[] abilities = pokemon.getAbilities();
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

    public void willDodge() {
        canDodge = true;
    }

    public boolean isStunned() {
        return stunned;
    }

    public boolean canDodge() {
        return canDodge;
    }

    public boolean isDefeated() {
        return pokemon.getHp() <= 0;
    }

    public void stun() {
        if (canDodge) {
            System.out.println(pokemon.getName() + " dodged the stun ability!");
            return;
        }
        stunned = true;
    }

    public void sufferAttack(int damage) {
        if (canDodge || damage < pokemon.getDefense()) {
//            System.out.println(this.name + " dodged!");
            return;
        }
        pokemon.sufferDamage(damage - pokemon.getDefense());
    }

    public void sufferSpecialAttack(int damage) {
        if (canDodge || damage < pokemon.getSpecialDefense()) {
//            System.out.println(this.name + " dodged!");
            return;
        }
        pokemon.sufferDamage(damage - pokemon.getSpecialDefense());
    }

    public void sufferAbilityDamage(int damage) {
        if (canDodge) {
//            System.out.println(this.name + " dodged!");
            return;
        }
        pokemon.sufferDamage(damage);
    }


    @Override
    public String getName() {
        return pokemon.getName();
    }

    @Override
    public int getHp() {
        return pokemon.getHp();
    }

    @Override
    public void reset() {
        pokemon.reset();
    }

    @Override
    public Integer getAttack() {
        return pokemon.getAttack();
    }

    @Override
    public Integer getSpecialAttack() {
        return pokemon.getSpecialAttack();
    }

    @Override
    public int getDefense() {
        return pokemon.getDefense();
    }

    @Override
    public int getSpecialDefense() {
        return pokemon.getSpecialDefense();
    }

    @Override
    public Ability[] getAbilities() {
        return pokemon.getAbilities();
    }

    @Override
    public void evolve() {
        pokemon.evolve();
    }

    public String toString() {
        return pokemon.toString();
    }
}
