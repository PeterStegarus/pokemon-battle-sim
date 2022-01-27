package pokemons;

public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", 42, null, 5, 3, 1,
                new Ability[]{new Ability(6, false, false, 4),
                        new Ability(5, false, false, 3)});
    }
}
