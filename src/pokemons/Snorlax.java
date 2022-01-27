package pokemons;

public class Snorlax extends Pokemon {
    public Snorlax() {
        super("Snorlax", 62, 3, null, 6, 4,
                new Ability[]{new Ability(4, true, false, 5),
                        new Ability(0, false, true, 5)});
    }
}
