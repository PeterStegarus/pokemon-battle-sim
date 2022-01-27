package pokemons;

public class Vulpix extends Pokemon {
    public Vulpix() {
        super("Vulpix", 36, 5, null, 2, 4,
                new Ability[]{new Ability(8, true, false, 6),
                        new Ability(2, false, true, 7)});
    }
}
