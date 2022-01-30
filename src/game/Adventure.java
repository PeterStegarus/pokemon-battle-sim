package game;

import game.event.ArenaDuel;
import game.event.ArenaEvent;
import game.event.ArenaEventFactory;
import logger.Logger;
import pokemons.Pokemon;

import java.util.ArrayList;

public class Adventure {
    private final ArrayList<ArenaEvent> arenaEvents = new ArrayList<>();
    private final Trainer trainer1;
    private final FighterPokemon pokemon1;
    private final Trainer trainer2;
    private final FighterPokemon pokemon2;

    public Adventure(Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2) {
        this.trainer1 = trainer1;
        this.pokemon1 = new FighterPokemon(pokemon1);
        this.trainer2 = trainer2;
        this.pokemon2 = new FighterPokemon(pokemon2);
    }

    // concludes who's the winner
    private String end(int result) {
        if (result == -1)
            return "Draw.";
        if (result == 1)
            return pokemon1 + " wins!";
        return pokemon2 + " wins!";
    }

    // randomly add fights to the event pool. Events will take place in the order they are added.
    // an adventure can't continue past the duel event
    private void setupEvents() {
        ArenaEvent arenaEvent = null;
        while (!(arenaEvent instanceof ArenaDuel)) {
            arenaEvent = ArenaEventFactory.getInstance().makeRandom(trainer1, pokemon1, trainer2, pokemon2);
            arenaEvents.add(arenaEvent);
        }
    }

    // play the fights in the order they've been added to the event pool
    public String play() {
        Logger.log("Start of new " + this + "\n");
        setupEvents();

        int result = 0;
        for (ArenaEvent arenaEvent : arenaEvents) {
            result = arenaEvent.fight();
            if (result != 0)
                return end(result);
        }

        return end(result);
    }

    @Override
    public String toString() {
        return "Adventure: " + trainer1.getName() + "'s " + pokemon1.getName() + " vs " + trainer2.getName() + "'s " + pokemon2.getName();
    }
}
