package pokemons;

public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", 42, 0, 5, 3, 1);
        Ability ability1 = new Ability(6, false, false, 4);
        Ability ability2 = new Ability(5, false, false, 3);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
