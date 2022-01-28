package game;

import logger.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArenaDuel extends ArenaEvent {
    public ArenaDuel(Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        super(trainer1, pokemon1, trainer2, pokemon2);
    }

    public void logFighters() {
        Logger.log(String.format("Arena Duel: %s vs %s. Fight!\n", trainer1.getName(),
                pokemon1.toString(), trainer2.getName(), pokemon2.toString()));
    }

    private void execTurn(Trainer trainer1, FighterPokemon pokemon1, Trainer FighterPokemon, FighterPokemon pokemon2, int i) {
        Logger.log("[Turn " + i + "]: {" +
                pokemon1.getName() + ": (HP" + pokemon1.getHp() + ") (Abilities: " + pokemon1.getAbilities() + ")} {" +
                pokemon2.getName() + ": (HP" + pokemon2.getHp() + ") (Abilities: " + pokemon2.getAbilities() + ")}\n");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(trainer1.giveCommand(pokemon1, pokemon2));
        executor.execute(trainer2.giveCommand(pokemon2, pokemon1));
        executor.shutdown();
    }

    // returns true if neutrel loses, false otherwise
    private int duel(Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        int i = 0;

        while (!pokemon1.isDefeated() && !pokemon2.isDefeated()) {
            execTurn(trainer1, pokemon1, trainer2, pokemon2, i);
            i++;
        }

        if (pokemon1.isDefeated() && pokemon2.isDefeated())
            return -1;  // draw

        if (pokemon1.isDefeated()) {    // trainer2 wins
            pokemon2.reset();
            pokemon2.evolve();
            return 2;
        }

        // else, trainer1 wins
        pokemon1.reset();
        pokemon1.evolve();
        return 1;
    }

    public int fight() {
        lock.lock();
        logFighters();
        int result = duel(trainer1, pokemon1, trainer2, pokemon2);
        lock.unlock();
        return result;
    }
}
