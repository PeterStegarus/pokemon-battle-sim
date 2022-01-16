import com.google.gson.*;
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
        Set<String> items;  // items must be unique
    }

    private class TrainerData {
        String name;
        int age;
        PokemonData[] pokemons;
    }

    private Pokemon readPokemon(PokemonData p) {
        Pokemon pokemon = new PokemonFactory().make(p.name);
        for (String item : p.items) {
            pokemon.addItem(new ItemFactory().make(item));
        }
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

            System.out.println(trainer1.toString() + "\n" + trainer2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.readTrainers("test1.json");

        Adventure adventure = new Adventure(manager.trainer1, manager.trainer2);
        adventure.play();
    }
}