package game;

import logger.Logger;
import pokemons.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Trainer implements FighterPokemonController {
    private final String name;
    private int age;
    private Pokemon[] pokemons;

    public Trainer(String name, int age, Pokemon[] pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " " + age + " " + Arrays.toString(pokemons);
    }

    public Pokemon choosePokemon(int i) {
        return pokemons[i];
    }

    private FightCommand generateRandomCommand(FighterPokemon attacker, FighterPokemon target) {
        ArrayList<FightCommand> possibleFightCommands = new ArrayList<>();
        possibleFightCommands.add(new AttackFightCommand(attacker, target));
        for (int i = 0; i < attacker.getAbilities().length; i++) {
            if (attacker.getAbilities()[i].getCooldown() == 0)
                possibleFightCommands.add(new AbilityFightCommand(attacker, target, i));
        }

        int random = new Random().nextInt(possibleFightCommands.size());
        return possibleFightCommands.get(random);
    }

    public FightCommand giveCommand(FighterPokemon friend, FighterPokemon enemy) {
        return generateRandomCommand(friend, enemy);
    }

    // best: highest level. if equal level, lowest lexicographically
    public Pokemon chooseBestPokemon() {
        Pokemon bestPokemon = pokemons[0];
        for (int i = 1; i < pokemons.length; i++) {
            if (pokemons[i].getLevel() > bestPokemon.getLevel()) {
                if (pokemons[i].getName().compareTo(bestPokemon.getName()) < 0)
                    bestPokemon = pokemons[i];
            }
        }

        return bestPokemon;
    }
}