package pokemons.factory;

import pokemons.Pokemon;

interface PokemonMaker {
    boolean is(String pokemon);

    Pokemon make();
}
