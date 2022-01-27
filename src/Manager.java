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
        Set<String> items;  // items must be unique
    }

    private class TrainerData {
        String name;
        int age;
        PokemonData[] pokemons;
    }

    private Pokemon readPokemon(PokemonData p) {
        Pokemon pokemon = PokemonFactory.getInstance().make(p.name);
        for (String item : p.items) {
            pokemon.addItem(ItemFactory.getInstance().make(item));
        }
        return pokemon;
    }

    private Trainer readTrainer(TrainerData t) {
        Pokemon[] pokemons = new Pokemon[3];
        for (int i = 0; i < 3; i++) {
            pokemons[i] = readPokemon(t.pokemons[0]);
        }

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
        System.out.println("Winner: " + adventure.play());
    }
}