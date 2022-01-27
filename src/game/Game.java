package game;

import pokemons.Pokemon;

import java.util.ArrayList;

public class Game {
    private Trainer trainer1;
    private Trainer trainer2;
    private ArrayList<Adventure> adventures = new ArrayList<>();

    public Game(Trainer[] trainers) {
        this.trainer1 = trainers[0];
        this.trainer2 = trainers[1];
    }

    private void queueAdventure(Adventure adventure) {
        adventures.add(adventure);
    }

    public void play() {
        System.out.println(trainer1.toString() + "\n" + trainer2.toString());

        // TODO: for command pattern, the client (manager) should give commands to the invoker (game)
        for (int i = 0; i < 3; i++) {
            Pokemon pokemon1 = trainer1.choosePokemon(i);
            Pokemon pokemon2 = trainer2.choosePokemon(i);
            adventures.add(new Adventure(trainer1, pokemon1, trainer2, pokemon2));
        }
        adventures.add(new Adventure(trainer1, trainer1.chooseBestPokemon(), trainer2, trainer2.chooseBestPokemon()));

        for (Adventure adventure : adventures) {
            System.out.println("Winner of " + adventure.toString() + ":" + adventure.play());
        }
    }
}
