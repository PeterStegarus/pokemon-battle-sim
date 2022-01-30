package game.event;

import game.FighterPokemon;
import game.Trainer;
import logger.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class ArenaEvent {
    protected Trainer trainer1;
    protected FighterPokemon pokemon1;
    protected Trainer trainer2;
    protected FighterPokemon pokemon2;

    public ArenaEvent(Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        this.trainer1 = trainer1;
        this.pokemon1 = pokemon1;
        this.trainer2 = trainer2;
        this.pokemon2 = pokemon2;
    }

    // execute a turn concurrently
    protected void execTurn(Runnable command1, Runnable command2) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(command1);
        executor.execute(command2);
        executor.shutdown();

        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                Logger.log("Duel was interrupted");
                System.exit(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract int fight();
}
