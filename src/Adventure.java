import pokemons.Pokemon;

import java.util.Random;

public class Adventure {
    private ArenaEvent[] arenaEvents;
    private Trainer trainer1;
    private Pokemon pokemon1;
    private Trainer trainer2;
    private Pokemon pokemon2;

    public Adventure(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.pokemon1 = trainer1.choosePokemon();
        this.trainer2 = trainer2;
        this.pokemon2 = trainer2.choosePokemon();
        arenaEvents = new ArenaEvent[]{new ArenaDuel(trainer1, pokemon1, trainer2, pokemon2),
                new ArenaNeutrel(1, trainer1, pokemon1, trainer2, pokemon2), new ArenaNeutrel(2, trainer1, pokemon1, trainer2, pokemon2)};
    }

    private ArenaEvent selectRandomEvent() {
        int random = new Random().nextInt(3);
        return arenaEvents[random];
    }

    public void play() {
        ArenaEvent arenaEvent;
        for (int i = 0; i < 3; i++) {
            arenaEvent = selectRandomEvent();
            arenaEvent.fight();
        }
    }
}
