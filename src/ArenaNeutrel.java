import pokemons.Pokemon;
import pokemons.PokemonFactory;

public class ArenaNeutrel extends ArenaEvent {
    private Pokemon neutrel;

    public ArenaNeutrel(int neutrel, Trainer trainer1, Trainer trainer2) {
        super(trainer1, trainer2);
        this.neutrel = new PokemonFactory().make("Neutrel" + neutrel);
    }

    public void fight() {
    }
}
