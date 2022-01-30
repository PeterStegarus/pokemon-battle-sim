package game;

import game.fight.FightCommand;

public interface FighterPokemonController {
    FightCommand giveCommand(FighterPokemon friend, FighterPokemon enemy);
}
