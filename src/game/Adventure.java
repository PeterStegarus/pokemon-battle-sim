package game;

import pokemons.Pokemon;

import java.util.Random;

public class Adventure {
    private ArenaEvent[] arenaEvents;
    private Trainer trainer1;
    private FighterPokemon pokemon1;
    private Trainer trainer2;
    private FighterPokemon pokemon2;

    public Adventure(Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2) {
        this.trainer1 = trainer1;
        this.pokemon1 = new FighterPokemon(pokemon1);
        this.trainer2 = trainer2;
        this.pokemon2 = new FighterPokemon(pokemon1);
        arenaEvents = new ArenaEvent[]{new ArenaDuel(trainer1, this.pokemon1, trainer2, this.pokemon2),
                new ArenaNeutrel(1, trainer1, this.pokemon1, trainer2, this.pokemon2),
                new ArenaNeutrel(2, trainer1, this.pokemon1, trainer2, this.pokemon2)};
    }

    private ArenaEvent selectRandomEvent() {
        int random = new Random().nextInt(3);
        return arenaEvents[random];
    }

    private String end(int result) {
        if (result == 0)
            return "Draw";
        if (result == 1)
            return trainer1.getName();
        return trainer2.getName();
    }

    public String play() {
        ArenaEvent arenaEvent;
        int result;
        while (true) {
            arenaEvent = selectRandomEvent();
            result = arenaEvent.fight();
            if (result != 0) {
                return end(result);
            }
        }
    }

    public String toString() {
        return trainer1.getName() + " & " + pokemon1.getName() + " vs " + trainer2.getName() + " & " + pokemon2.getName();
    }
}
