package pokemons;

public class Jigglypuff extends Pokemon {
    public Jigglypuff() {
        super("Jigglypuff", 34, 4, null, 2, 3);
        Ability ability1 = new Ability(4, true, false, 4);
        Ability ability2 = new Ability(3, true, false, 4);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}
