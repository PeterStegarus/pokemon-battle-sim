public abstract class ArenaEvent {
    Trainer trainer1;
    Trainer trainer2;

    public ArenaEvent(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
    }

    public abstract void fight();
}
