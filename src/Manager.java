import com.google.gson.*;
import pokemons.Pokemon;
import pokemons.PokemonFactory;

import java.io.FileReader;
import java.util.Arrays;


public class Manager {
    private Gson gson = new Gson();
    private Trainer trainer1;
    private Trainer trainer2;

    class TrainerJson {
        String name;
        int age;
        String[] pokemons;
    }

    private Trainer readTrainer(TrainerJson t) {
        PokemonFactory pokemonFactory = new PokemonFactory();
        Pokemon[] pokemons = new Pokemon[]{pokemonFactory.make(t.pokemons[0]),
                pokemonFactory.make(t.pokemons[1]), pokemonFactory.make(t.pokemons[2])};
        return new Trainer(t.name, t.age, pokemons);
    }

    private void readTrainers(String filePath) {
        try {
            TrainerJson[] trainers = gson.fromJson(new FileReader(filePath), TrainerJson[].class);
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
    }
}