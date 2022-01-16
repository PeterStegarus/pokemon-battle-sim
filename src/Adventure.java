import java.util.Random;

public class Adventure {
    private ArenaEvent[] arenaEvents;
    private Trainer trainer1;
    private Trainer trainer2;

    public Adventure(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        arenaEvents = new ArenaEvent[]{new ArenaDuel(trainer1, trainer2),
                new ArenaNeutrel(1, trainer1, trainer2), new ArenaNeutrel(2, trainer1, trainer2)};
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
