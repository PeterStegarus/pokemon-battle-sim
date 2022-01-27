package pokemons;

public class Eevee extends Pokemon {
    public Eevee() {
        super("Eevee", 39, null, 4, 3, 3,
                new Ability[]{new Ability(5, false, false, 3),
                        new Ability(3, true, false, 3)});
    }
}