package pokemons;

public class Meowth extends Pokemon {
    public Meowth() {
        super("Meowth", 41, 3, null, 4, 2,
                new Ability[]{new Ability(5, false, true, 4),
                        new Ability(1, false, true, 3)});
    }
}
