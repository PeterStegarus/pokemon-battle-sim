import com.google.gson.*;
import game.Adventure;
import game.Game;
import game.Trainer;
import items.ItemFactory;
import logger.ConsoleLogger;
import logger.FileLogger;
import logger.Logger;
import pokemons.Pokemon;
import pokemons.PokemonFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Set;

public class Manager {
    private Gson gson = new Gson();

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
            pokemons[i] = readPokemon(t.pokemons[i]);
        }

        return new Trainer(t.name, t.age, pokemons);
    }

    private Trainer[] readTrainers(String filePath) throws FileNotFoundException, com.google.gson.JsonParseException, IllegalArgumentException {
        Trainer trainer1 = null;
        Trainer trainer2 = null;

        TrainerData[] trainers = gson.fromJson(new FileReader(filePath), TrainerData[].class);
        trainer1 = readTrainer(trainers[0]);
        trainer2 = readTrainer(trainers[1]);

        if (trainer1 == null || trainer2 == null)
            throw new IllegalArgumentException();

        return new Trainer[]{trainer1, trainer2};
    }

    public static void main(String[] args) {
        String inputFile = "test1.json";
        String outputFile = "test1.out";
        Logger.init(new FileLogger(Path.of(outputFile), new ConsoleLogger()));
        Manager manager = new Manager();
        Trainer[] trainers = new Trainer[0];
        try {
            trainers = manager.readTrainers(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Input file doesn't exist, try again with new input file.");
        } catch (com.google.gson.JsonParseException e) {
            System.out.println("Input file may be corrupt. Make sure it's a valid JSON format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input file might not be in the correct format. Two trainers are needed.");
        }

        Game game = new Game(trainers);

        for (int i = 0; i < 3; i++) {
            Pokemon pokemon1 = trainers[0].choosePokemon(i);
            Pokemon pokemon2 = trainers[1].choosePokemon(i);
            game.takeAdventure(new Adventure(trainers[0], pokemon1, trainers[1], pokemon2));
        }
        game.takeAdventure(new Adventure(trainers[0], trainers[0].chooseBestPokemon(), trainers[1], trainers[1].chooseBestPokemon()));

        game.play();
    }
}