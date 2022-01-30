package game;

import game.fight.FightCommand;

public interface FighterPokemonController {
    public FightCommand giveCommand(FighterPokemon friend, FighterPokemon enemy);
}
