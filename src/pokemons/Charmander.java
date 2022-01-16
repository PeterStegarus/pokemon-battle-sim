package pokemons;

public class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander", 50, 4, null, 3, 2);
        Ability ability1 = new Ability(4, true, false, 4);
        Ability ability2 = new Ability(7, false, false, 6);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
