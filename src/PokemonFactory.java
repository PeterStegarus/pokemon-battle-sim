import pokemons.Neutrel1;
import pokemons.Neutrel2;
import pokemons.Pikachu;
import pokemons.Pokemon;

import java.util.Arrays;
import java.util.List;

public class PokemonFactory {
    private PokemonMaker selectPokemonMaker(String pokemon) {   // filter pattern
        List<PokemonMaker> pokemonMakers = Arrays.asList(new PikachuMaker(), new Neutrel1Maker(), new Neutrel2Maker());

        return pokemonMakers.stream().filter(pokemonMaker -> pokemonMaker.is(pokemon)).findFirst().orElse(null);
    }

    public Pokemon make(String pokemon) {   // factory pattern pentru ca makerul face un pokemon nou
        // strategy pattern pentru ca selecteaza o implementare de maker
        PokemonMaker pokemonMaker = selectPokemonMaker(pokemon);
        return pokemonMaker != null ? pokemonMaker.make() : null;
    }
}

interface PokemonMaker {
    boolean is(String pokemon);

    Pokemon make();
}

class PikachuMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Pikachu".equals(pokemon);
    }

    public Pokemon make() {
        return new Pikachu();
    }
}

class Neutrel1Maker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Neutrel1".equals(pokemon);
    }

    public Pokemon make() {
        return new Neutrel1();
    }
}

class Neutrel2Maker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Neutrel2".equals(pokemon);
    }

    public Pokemon make() {
        return new Neutrel2();
    }
}