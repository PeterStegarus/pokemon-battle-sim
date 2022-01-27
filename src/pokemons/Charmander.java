package pokemons;

public class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander", 50, 4, null, 3, 2,
                new Ability[]{new Ability(4, true, false, 4),
                        new Ability(7, false, false, 6)});
    }
}
