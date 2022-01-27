package game;

import pokemons.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Trainer {
    private String name;
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

    public int getAge() {
        return age;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public String toString() {
        return name + " " + age + " " + Arrays.toString(pokemons);
    }

    private Pokemon selectPokemon(int i) {
        return pokemons[i];
    }

    public Pokemon choosePokemon(int i) {
        Pokemon pokemon = selectPokemon(i);
        return pokemon;
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
}