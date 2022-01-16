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

    private Pokemon selectRandomPokemon() {
        int random = new Random().nextInt(3);
        return pokemons[random];
    }

    public Pokemon choosePokemon() {
        return selectRandomPokemon();
    }

    private Command generateRandomCommand(Pokemon attacker, Pokemon target) {
        ArrayList<Command> possibleCommands = new ArrayList<>();
        possibleCommands.add(new AttackCommand(attacker, target));
        for (int i = 0; i < attacker.getAbilities().length; i++) {
            if (attacker.getAbilities()[i].getCooldown() == 0)
                possibleCommands.add(new AbilityCommand(attacker, target, i));
        }

        int random = new Random().nextInt(possibleCommands.size());
        return possibleCommands.get(random);
    }

    public Command giveCommand(Pokemon friend, Pokemon enemy) {
        return generateRandomCommand(friend, enemy);
    }
}
