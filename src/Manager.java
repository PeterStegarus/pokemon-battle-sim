import com.google.gson.*;
import game.Adventure;
import game.Trainer;
import items.Item;
import items.ItemFactory;
import pokemons.Pokemon;
import pokemons.PokemonFactory;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Set;

public class Manager {
    private Gson gson = new Gson();
    private Trainer trainer1;
    private Trainer trainer2;

    private class PokemonData {
        String name;
    }

    private class TrainerData {
        String name;
        int age;
        PokemonData[] pokemons;
    }

    private Pokemon readPokemon(PokemonData p) {
        Pokemon pokemon = new PokemonFactory().make(p.name);
        return pokemon;
    }

    private Trainer readTrainer(TrainerData t) {
        Pokemon pokemon1 = readPokemon(t.pokemons[0]);
        Pokemon pokemon2 = readPokemon(t.pokemons[1]);
        Pokemon pokemon3 = readPokemon(t.pokemons[2]);

        Pokemon[] pokemons = new Pokemon[]{pokemon1, pokemon2, pokemon3};
        return new Trainer(t.name, t.age, pokemons);
    }

    private void readTrainers(String filePath) {
        try {
            TrainerData[] trainers = gson.fromJson(new FileReader(filePath), TrainerData[].class);
            trainer1 = readTrainer(trainers[0]);
            trainer2 = readTrainer(trainers[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.readTrainers("test1.json");

        System.out.println(manager.trainer1.toString() + "\n" + manager.trainer2.toString());

        Adventure adventure = new Adventure(manager.trainer1, manager.trainer2);
        adventure.play();
    }
}