package game;

import logger.Logger;
import pokemons.IPokemon;
import pokemons.PokemonFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArenaNeutrel extends ArenaEvent {
    private FighterPokemon neutrel;

    public ArenaNeutrel(int neutrel, Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        super(trainer1, pokemon1, trainer2, pokemon2);
        this.neutrel = new FighterPokemon(PokemonFactory.getInstance().make("Neutrel" + neutrel));
    }

    public void logFighters(Trainer trainer, IPokemon pokemon) {
        Logger.log(String.format("Arena Fight: %s vs %s. Fight!\n", trainer.getName(), pokemon.toString(), neutrel.toString()));
    }

    private void execTurn(Trainer trainer, FighterPokemon pokemon, int i) {
        Logger.log("[Turn " + i + "]: {" + pokemon.getName() + ": (HP" + pokemon.getHp() + ") (Abilities: " +
                pokemon.getAbilities() + ")} {" + neutrel.getName() + " (HP" + neutrel.getHp() + ")}\n");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new AttackFightCommand(neutrel, pokemon));
        executor.execute(trainer.giveCommand(pokemon, neutrel));
        executor.shutdown();
    }

    // returns true if neutrel loses, false otherwise
    private boolean neutrelFight(Trainer trainer, FighterPokemon pokemon) {
        int i = 0;

        while (!pokemon.isDefeated() && !neutrel.isDefeated()) {
            execTurn(trainer, pokemon, i);
            i++;
        }

        boolean result = !pokemon.isDefeated();
        pokemon.reset();
        neutrel.reset();
        return result;
    }

    public int fight() {
        lock.lock();
        logFighters(trainer1, pokemon1);
        boolean result1 = neutrelFight(trainer1, pokemon1);
        logFighters(trainer2, pokemon2);
        boolean result2 = neutrelFight(trainer2, pokemon2);
        if (!result1 && !result2)
            return -1;  // both lose
        if (!result1)
            return 2;   // trainer2 wins
        if (!result2)
            return 1;   // trainer1 wins

        pokemon2.evolve();
        pokemon1.evolve();
        lock.unlock();
        return 0;   // both trainers win
    }
}