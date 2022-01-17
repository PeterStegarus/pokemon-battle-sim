package pokemons;

public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", 60, null, 3, 5, 5);
        Ability ability1 = new Ability(4, false, false, 3);
        Ability ability2 = new Ability(2, true, false, 2);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
