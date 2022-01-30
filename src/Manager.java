import com.google.gson.*;
import game.Game;
import game.Trainer;
import items.factory.ItemFactory;
import logger.ConsoleLogger;
import logger.FileLogger;
import logger.ILogger;
import logger.Logger;
import pokemons.Pokemon;
import pokemons.factory.PokemonFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    private final Gson gson = new Gson();

    private static class InputData {
        String logger;
        TrainerData[] trainers;
    }

    private static class PokemonData {
        String name;
        Set<String> items;  // items must be unique
    }

    private static class TrainerData {
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

    // first read the json as is, then build the trainers and pokemons
    private Trainer[] readTrainers(String filePath) throws FileNotFoundException, com.google.gson.JsonParseException {
        Trainer trainer1;
        Trainer trainer2;

        InputData inputData = gson.fromJson(new FileReader(filePath), InputData.class);
        TrainerData[] trainers = inputData.trainers;
        trainer1 = readTrainer(trainers[0]);
        trainer2 = readTrainer(trainers[1]);

        return new Trainer[]{trainer1, trainer2};
    }

    private String readLoggerType(String filePath) throws FileNotFoundException, com.google.gson.JsonParseException {
        InputData inputData = gson.fromJson(new FileReader(filePath), InputData.class);
        return inputData.logger;
    }

    private int getInputFile() {
        Scanner scanner = new Scanner(System.in);
        int inputFileNo = -1;
        while (!(0 < inputFileNo && inputFileNo < 11)) {
            System.out.println("Enter test number: (1..10)");
            inputFileNo = scanner.nextInt();
        }
        return inputFileNo;
    }

    private String getOutputFile(String inputFile) {
        Matcher matcher = Pattern.compile("(?<=input/)(.*)(?=.json)").matcher(inputFile);
        matcher.find();
        return matcher.group() + ".log";
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        String inputFile = "input/test" + manager.getInputFile() + ".json";
        String outputFile = manager.getOutputFile(inputFile);
        String loggerType = "";
        Trainer[] trainers = new Trainer[0];

        // read logger type and trainers
        try {
            loggerType = manager.readLoggerType(inputFile);
            trainers = manager.readTrainers(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Input file doesn't exist, try again with new input file.");
        } catch (com.google.gson.JsonParseException e) {
            System.out.println("Input file may be corrupt. Make sure it's a valid JSON format.");
        }

        // init logger
        ILogger logger = switch (loggerType) {
            case "console" -> new ConsoleLogger();
            case "file" -> new FileLogger(outputFile);
            case "both" -> new FileLogger(outputFile, new ConsoleLogger());
            default -> null;
        };
        Logger.init(logger);

        // game setup
        Game game = new Game(trainers);
        // run game
        game.play();
    }
}