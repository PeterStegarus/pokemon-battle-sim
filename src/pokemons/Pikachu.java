package pokemons;

public class Pikachu extends Pokemon {
    public Pikachu() {
        super("Pikachu", 35, null, 4, 2, 3,
                new Ability[]{new Ability(6, false, false, 4),
                        new Ability(4, true, true, 5)});

    }
}
