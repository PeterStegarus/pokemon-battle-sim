package game;

import logger.Logger;
import pokemons.Pokemon;

import java.util.ArrayList;

public class Game {
    private final Trainer trainer1;
    private final Trainer trainer2;
    private ArrayList<Adventure> adventures = new ArrayList<>();

    public Game(Trainer[] trainers) {
        this.trainer1 = trainers[0];
        this.trainer2 = trainers[1];
        init();
    }

    private void takeAdventure(Adventure adventure) {
        adventures.add(adventure);
    }

    private void init() {
        for (int i = 0; i < 3; i++) {
            Pokemon pokemon1 = trainer1.choosePokemon(i);
            Pokemon pokemon2 = trainer2.choosePokemon(i);
            takeAdventure(new Adventure(trainer1, pokemon1, trainer2, pokemon2));
        }
        takeAdventure(new Adventure(trainer1, trainer1.chooseBestPokemon(), trainer2, trainer2.chooseBestPokemon()));
    }

    public void play() {
        for (Adventure adventure : adventures) {
            Logger.log("Result of " + adventure.toString() + ": " + adventure.play() + "\n\n");
        }
    }
}
