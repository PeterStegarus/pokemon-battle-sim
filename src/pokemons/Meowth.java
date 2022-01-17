package pokemons;

public class Meowth extends Pokemon {
    public Meowth() {
        super("Meowth", 41, 3, null, 4, 2);
        Ability ability1 = new Ability(5, false, true, 4);
        Ability ability2 = new Ability(1, false, true, 3);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
