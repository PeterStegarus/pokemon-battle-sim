package pokemons.factory;

import pokemons.*;

import java.util.Arrays;
import java.util.List;

public class PokemonFactory {
    private static PokemonFactory instance;

    private PokemonFactory() {
    }

    public static PokemonFactory getInstance() {
        if (instance == null)
            instance = new PokemonFactory();
        return instance;
    }

    private PokemonMaker selectPokemonMaker(String pokemon) {   // filter pattern
        List<PokemonMaker> pokemonMakers = Arrays.asList(new Neutrel1Maker(), new Neutrel2Maker(), new PikachuMaker(),
                new BulbasaurMaker(), new CharmanderMaker(), new SquirtleMaker(), new SnorlaxMaker(), new VulpixMaker(),
                new EeveeMaker(), new JigglypuffMaker(), new MeowthMaker(), new PsyduckMaker());

        return pokemonMakers.stream().filter(pokemonMaker -> pokemonMaker.is(pokemon)).findFirst().orElse(null);
    }

    public Pokemon make(String pokemon) {   // factory pattern pentru ca makerul face un pokemon nou
        // strategy pattern pentru ca selecteaza o implementare de maker
        PokemonMaker pokemonMaker = selectPokemonMaker(pokemon);
        return pokemonMaker != null ? pokemonMaker.make() : null;
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

class SquirtleMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Squirtle".equals(pokemon);
    }

    public Pokemon make() {
        return new Squirtle();
    }
}

class SnorlaxMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Snorlax".equals(pokemon);
    }

    public Pokemon make() {
        return new Snorlax();
    }
}

class VulpixMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Vulpix".equals(pokemon);
    }

    public Pokemon make() {
        return new Vulpix();
    }
}

class EeveeMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Eevee".equals(pokemon);
    }

    public Pokemon make() {
        return new Eevee();
    }
}

class JigglypuffMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Jigglypuff".equals(pokemon);
    }

    public Pokemon make() {
        return new Jigglypuff();
    }
}

class MeowthMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Meowth".equals(pokemon);
    }

    public Pokemon make() {
        return new Meowth();
    }
}

class PsyduckMaker implements PokemonMaker {
    public boolean is(String pokemon) {
        return "Psyduck".equals(pokemon);
    }

    public Pokemon make() {
        return new Psyduck();
    }
}