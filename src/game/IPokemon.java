package game;

import items.Item;
import pokemons.Ability;

import java.util.Arrays;

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
