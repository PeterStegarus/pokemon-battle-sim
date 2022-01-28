package game;

import pokemons.Pokemon;

import java.util.ArrayList;

public class Adventure {
    private ArrayList<ArenaEvent> arenaEvents = new ArrayList<>();
    private Trainer trainer1;
    FighterPokemon pokemon1;
    private Trainer trainer2;
    FighterPokemon pokemon2;

    public Adventure(Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2) {
        this.trainer1 = trainer1;
        this.pokemon1 = new FighterPokemon(pokemon1);
        this.trainer2 = trainer2;
        this.pokemon2 = new FighterPokemon(pokemon1);
    }

    private String end(int result) {
        if (result == -1)
            return "Draw";
        if (result == 1)
            return trainer1.getName();
        return trainer2.getName();
    }

    private void setupEvents() {
        ArenaEvent arenaEvent = null;
        while (!(arenaEvent instanceof ArenaDuel)) {
            arenaEvent = ArenaEventFactory.getInstance().makeRandom(trainer1, pokemon1, trainer2, pokemon2);
            arenaEvents.add(arenaEvent);
        }
    }

    public String play() {
        setupEvents();

        int result = 0;
        for (ArenaEvent arenaEvent : arenaEvents) {
            result = arenaEvent.fight();
            if (result != 0)
                return end(result);
        }

        return end(result);
    }

    public String toString() {
        return trainer1.getName() + " vs " + trainer2.getName();
    }
}
