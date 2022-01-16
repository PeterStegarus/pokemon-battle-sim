package pokemons;

import java.util.Arrays;
import java.util.List;

public class PokemonFactory {
    private PokemonMaker selectPokemonMaker(String pokemon) {   // filter pattern
        List<PokemonMaker> pokemonMakers = Arrays.asList(new Neutrel1Maker(), new Neutrel2Maker(), new PikachuMaker(),
                new BulbasaurMaker(), new CharmanderMaker());

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

class PikachuMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Pikachu".equals(pokemon);
    }

    public Pokemon make() {
        return new Pikachu();
    }
}


class BulbasaurMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Bulbasaur".equals(pokemon);
    }

    public Pokemon make() {
        return new Bulbasaur();
    }
}


class CharmanderMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Charmander".equals(pokemon);
    }

    public Pokemon make() {
        return new Charmander();
    }
}
