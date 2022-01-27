package game;

import pokemons.Ability;

public interface IPokemon {
    String getName();

    int getHp();

    void reset();

    Integer getAttack();

    Integer getSpecialAttack();

    int getDefense();

    int getSpecialDefense();

    Ability[] getAbilities();

    void evolve();

    String toString();
}
