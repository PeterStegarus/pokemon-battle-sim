package pokemons;

public class Eevee extends Pokemon {
    public Eevee() {
        super("Eevee", 39, null, 4, 3, 3);
        Ability ability1 = new Ability(5, false, false, 3);
        Ability ability2 = new Ability(3, true, false, 3);
        super.setAbilities(new Ability[]{ability1, ability2});
    }
}