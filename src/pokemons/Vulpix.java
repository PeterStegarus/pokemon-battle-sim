package pokemons;

public class Vulpix extends Pokemon {
    public Vulpix() {
        super("Vulpix", 36, 5, null, 2, 4);
        Ability ability1 = new Ability(8, true, false, 6);
        Ability ability2 = new Ability(2, false, true, 7);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
