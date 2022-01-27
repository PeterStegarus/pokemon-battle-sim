package pokemons;

public class PokemonBuilder {
    private final String name;
    private int hp;
    private Integer attack = null;
    private Integer specialAttack = null;
    private int defense;
    private int specialDefense;
    private Ability[] abilities;

    public PokemonBuilder(String name) {
        this.name = name;
    }

    public PokemonBuilder hp(int hp) {
        this.hp = hp;
        return this;
    }

    public PokemonBuilder attack(Integer attack) {
        this.attack = attack;
        return this;
    }

    public PokemonBuilder specialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
        return this;
    }

    public PokemonBuilder defense(int defense) {
        this.defense = defense;
        return this;
    }

    public PokemonBuilder specialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
        return this;
    }

    public Pokemon build() {
        Pokemon pokemon = new Pokemon(name, hp, attack, specialAttack, defense, specialDefense, abilities);
        return pokemon;
    }
}
