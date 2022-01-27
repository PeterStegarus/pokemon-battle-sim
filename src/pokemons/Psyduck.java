package pokemons;

public class Psyduck extends Pokemon {
    public Psyduck() {
        super("Psyduck", 43, 3, null, 3, 3,
                new Ability[]{new Ability(2, false, false, 4),
                        new Ability(2, true, false, 5)});
    }
}
