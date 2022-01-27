package game;

import pokemons.Pokemon;

import java.util.Random;

public class Adventure {
    private ArenaEvent[] arenaEvents;
    private Trainer trainer1;
    private FighterPokemon pokemon1;
    private Trainer trainer2;
    private FighterPokemon pokemon2;

    public Adventure(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.pokemon1 = new FighterPokemon(trainer1.choosePokemon(1));
        this.trainer2 = trainer2;
        this.pokemon2 = new FighterPokemon(trainer2.choosePokemon(1));
        arenaEvents = new ArenaEvent[]{new ArenaDuel(trainer1, pokemon1, trainer2, pokemon2),
                new ArenaNeutrel(1, trainer1, pokemon1, trainer2, pokemon2), new ArenaNeutrel(2, trainer1, pokemon1, trainer2, pokemon2)};
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
}
