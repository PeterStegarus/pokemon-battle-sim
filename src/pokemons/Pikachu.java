package pokemons;

public class Pikachu extends Pokemon {
    public Pikachu() {
        super("Pikachu", 35, 0, 4, 2, 3);
        Ability ability1 = new Ability(6, false, false, 4);
        Ability ability2 = new Ability(4, true, true, 5);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
