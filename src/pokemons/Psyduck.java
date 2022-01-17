package pokemons;

public class Psyduck extends Pokemon {
    public Psyduck() {
        super("Psyduck", 43, 3, null, 3, 3);
        Ability ability1 = new Ability(2, false, false, 4);
        Ability ability2 = new Ability(2, true, false, 5);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
