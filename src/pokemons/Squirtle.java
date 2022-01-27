package pokemons;

public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", 60, null, 3, 5, 5,
                new Ability[]{new Ability(4, false, false, 3),
                        new Ability(2, true, false, 2)});
    }
}
