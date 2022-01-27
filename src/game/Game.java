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

    public void takeAdventure(Adventure adventure) {
        adventures.add(adventure);
    }

    public void play() {
        System.out.println(trainer1.toString() + "\n" + trainer2.toString());

        for (Adventure adventure : adventures) {
            System.out.println("Winner of " + adventure.toString() + ":" + adventure.play());
        }
    }
}
