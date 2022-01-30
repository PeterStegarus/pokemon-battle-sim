package game.event;

import game.FighterPokemon;
import game.Trainer;
import game.fight.AttackFightCommand;
import logger.Logger;
import pokemons.IPokemon;
import pokemons.factory.PokemonFactory;

import java.util.Arrays;

public class ArenaNeutrel extends ArenaEvent {
    private final FighterPokemon neutrel;

    public ArenaNeutrel(int neutrel, Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        super(trainer1, pokemon1, trainer2, pokemon2);
        this.neutrel = new FighterPokemon(PokemonFactory.getInstance().make("Neutrel" + neutrel));
    }

    public void logFighter(Trainer trainer, IPokemon pokemon) {
        Logger.log(String.format("\nArena Fight: %s's %s VS %s. Fight!\n", trainer.getName(), pokemon.toString(), neutrel.toString()));
    }

    // each turn, both pokemons (trainer's pokemon and neutrel) attack concurrently
    private void execTurn(Trainer trainer, FighterPokemon pokemon, int i) {
        Logger.log("[Turn " + i + "]: {" + pokemon.getName() + ": (HP" + pokemon.getHp() + ") (Abilities: " +
                Arrays.toString(pokemon1.getAbilities()) + ")} VS {" + neutrel.getName() + " (HP" + neutrel.getHp() + ")}\n");

        Runnable command1 = new AttackFightCommand(neutrel, pokemon);
        Runnable command2 = trainer.giveCommand(pokemon, neutrel);
        execTurn(command1, command2);
    }

    // returns true if neutrel loses, false otherwise
    private boolean neutrelFight(Trainer trainer, FighterPokemon pokemon) {
        int i = 0;

        // fight turn by turn until either pokemon is defeated
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
        // trainer1's pokemon vs neutrel
        logFighter(trainer1, pokemon1);
        boolean result1 = neutrelFight(trainer1, pokemon1);

        // trainer2's pokemon vs neutrel
        logFighter(trainer2, pokemon2);
        boolean result2 = neutrelFight(trainer2, pokemon2);

        if (!result1 && !result2)
            return -1;  // both lose
        if (!result1) {
            pokemon2.evolve();
            Logger.log("\tEvolved: " + pokemon2.toString() + "\n");
            return 2;   // trainer2 wins
        }
        if (!result2) {
            pokemon1.evolve();
            Logger.log("\tEvolved: " + pokemon1.toString() + "\n");
            return 1;   // trainer1 wins
        }

        pokemon2.evolve();
        pokemon1.evolve();
        Logger.log("Both Pokemons beat Neutrel. Evolve!\n\tEvolved: " + pokemon1.toString() + "\n\tEvolved: " + pokemon2.toString() + "\n");
        return 0;   // both trainers win
    }
}