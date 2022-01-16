import pokemons.Pokemon;

import java.util.Arrays;
import java.util.Random;

public class Trainer {
    private String name;
    private int age;
    private Pokemon[] pokemons;

    public Trainer(String name, int age, Pokemon[] pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public String toString() {
        return name + " " + age + " " + Arrays.toString(pokemons);
    }

    private Pokemon selectRandomPokemon() {
        int random = new Random().nextInt(3);
        return pokemons[random];
    }

    public Pokemon choosePokemon() {
        return selectRandomPokemon();
    }
// antrenorul da comenzi pokemonilor
}
