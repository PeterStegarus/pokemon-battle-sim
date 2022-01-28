package game;

import logger.Logger;
import pokemons.IPokemon;
import pokemons.PokemonFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArenaNeutrel extends ArenaEvent {
    private FighterPokemon neutrel;

    public ArenaNeutrel(int neutrel, Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        super(trainer1, pokemon1, trainer2, pokemon2);
        this.neutrel = new FighterPokemon(PokemonFactory.getInstance().make("Neutrel" + neutrel));
    }

    public void logFighters(Trainer trainer, IPokemon pokemon) {
        Logger.log(String.format("Arena Fight: %s's %s VS %s. Fight!\n", trainer.getName(), pokemon.toString(), neutrel.toString()));
    }

    private void execTurn(Trainer trainer, FighterPokemon pokemon, int i) {
        Logger.log("[Turn " + i + "]: {" + pokemon.getName() + ": (HP" + pokemon.getHp() + ") (Abilities: " +
                Arrays.toString(pokemon1.getAbilities()) + ")} VS {" + neutrel.getName() + " (HP" + neutrel.getHp() + ")}\n");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new AttackFightCommand(neutrel, pokemon));
        executor.execute(trainer.giveCommand(pokemon, neutrel));
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
        System.out.println("Both Pokemons beat Neutrel. Evolve!\n\tEvolved: " + pokemon1.toString() + "\n\tEvolved: " + pokemon2.toString());
        return 0;   // both trainers win
    }
}