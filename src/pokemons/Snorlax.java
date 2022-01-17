package pokemons;

public class Snorlax extends Pokemon {
    public Snorlax() {
        super("Snorlax", 62, 3, null, 6, 4);
        Ability ability1 = new Ability(4, true, false, 5);
        Ability ability2 = new Ability(0, false, true, 5);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
