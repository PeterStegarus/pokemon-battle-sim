package game.event;

import game.FighterPokemon;
import game.Trainer;

import java.util.Random;

public class ArenaEventFactory {
    private static ArenaEventFactory instance;

    private ArenaEventFactory() {
    }

    public static ArenaEventFactory getInstance() {
        if (instance == null)
            instance = new ArenaEventFactory();
        return instance;
    }

    // chooses a random event between duel, fight vs neutrel1, fight vs neutrel2
    public ArenaEvent makeRandom(Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        ArenaEvent[] arenaEvents = new ArenaEvent[]{new ArenaDuel(trainer1, pokemon1, trainer2, pokemon2),
                new ArenaNeutrel(1, trainer1, pokemon1, trainer2, pokemon2),
                new ArenaNeutrel(2, trainer1, pokemon1, trainer2, pokemon2)};

        int random = new Random().nextInt(3);
        return arenaEvents[random];
    }
}
