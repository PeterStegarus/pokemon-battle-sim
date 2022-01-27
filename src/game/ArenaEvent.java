package game;

public abstract class ArenaEvent {
    protected Trainer trainer1;
    protected FighterPokemon pokemon1;
    protected Trainer trainer2;
    protected FighterPokemon pokemon2;

    public ArenaEvent(Trainer trainer1, FighterPokemon pokemon1, Trainer trainer2, FighterPokemon pokemon2) {
        this.trainer1 = trainer1;
        this.pokemon1 = pokemon1;
        this.trainer2 = trainer2;
        this.pokemon2 = pokemon2;
    }

    public abstract int fight();
}
