package pokemons;

public class Jigglypuff extends Pokemon {
    public Jigglypuff() {
        super("Jigglypuff", 34, 4, null, 2, 3,
                new Ability[]{new Ability(4, true, false, 4),
                        new Ability(3, true, false, 4)});
    }
}
