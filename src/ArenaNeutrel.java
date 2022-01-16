import pokemons.Pokemon;
import pokemons.PokemonFactory;

public class ArenaNeutrel extends ArenaEvent {
    private Pokemon neutrel;

    public ArenaNeutrel(int neutrel, Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2) {
        super(trainer1, pokemon1, trainer2, pokemon2);
        this.neutrel = new PokemonFactory().make("Neutrel" + neutrel);
    }

    // returns true if neutrel loses, false otherwise
    private boolean neutrelFight(Trainer trainer, Pokemon pokemon) {

        return true;
    }

    public void fight() {
        Pokemon t1Pokemon = trainer1.choosePokemon();
        if (neutrelFight(trainer1, t1Pokemon))
            t1Pokemon.evolve();

        Pokemon t2Pokemon = trainer2.choosePokemon();
        if (neutrelFight(trainer2, t2Pokemon))
            t2Pokemon.evolve();
    }
}