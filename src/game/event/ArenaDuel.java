package game.event;

import game.FighterPokemon;
import game.Trainer;
import logger.Logger;

import java.util.Arrays;

public class ArenaDuel extends ArenaEvent {
    public ArenaDuel(Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        super(trainer1, pokemon1, trainer2, pokemon2);
    }

    private void logFighters() {
        Logger.log(String.format("\nArena Duel: %s's %s VS %s's %s. Fight!\n", trainer1.getName(),
                pokemon1.toString(), trainer2.getName(), pokemon2.toString()));
    }

    // each turn, both pokemons (trainer1's vs trainer2's) attack concurrently
    private void execTurn(int i) {
        Logger.log("[Turn " + i + "]:\n{" +
                pokemon1.getName() + ": (HP" + pokemon1.getHp() + ") (Abilities: " + Arrays.toString(pokemon1.getAbilities()) + ")} VS\n{" +
                pokemon2.getName() + ": (HP" + pokemon2.getHp() + ") (Abilities: " + Arrays.toString(pokemon2.getAbilities()) + ")}\n");

        Runnable command1 = trainer1.giveCommand(pokemon1, pokemon2);
        Runnable command2 = trainer2.giveCommand(pokemon2, pokemon1);
        execTurn(command1, command2);
    }

    // returns true if neutrel loses, false otherwise
    private int duel() {
        int i = 0;
        while (!pokemon1.isDefeated() && !pokemon2.isDefeated()) {
            execTurn(i);
            i++;
        }

        int result;

        if (pokemon1.isDefeated() && pokemon2.isDefeated()) {
            result = -1;    // draw
        } else if (pokemon1.isDefeated()) {
            Logger.log("\tEvolved: " + pokemon2 + "\n");
            pokemon2.evolve();
            result = 2;     // trainer2 wins
        } else {
            Logger.log("\tEvolved: " + pokemon1 + "\n");
            pokemon1.evolve();
            result = 1;     // else, trainer1 wins
        }

        pokemon2.reset();
        pokemon1.reset();
        return result;
    }

    public int fight() {
        logFighters();
        return duel();
    }
}
