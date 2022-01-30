package game.fight;

import game.FighterPokemon;
import logger.Logger;

public class AttackFightCommand extends FightCommand {
    public AttackFightCommand(FighterPokemon attacker, FighterPokemon target) {
        super(attacker, target);
    }

    @Override
    public void run() {
        lock.lock();
        String pokeInfo = "\t" + attacker.getName() + " (" + attacker.getHp() + ")";
        boolean skip = initTurn();

        waitInit();

        if (!skip) {
            // attack when both pokemons enter the attack phase
            boolean result;
            if (attacker.getAttack() != null) {
                result = target.sufferAttack(attacker.getAttack());
            } else {
                result = target.sufferSpecialAttack(attacker.getSpecialAttack());
            }
            String message = result ? "It was effective!\n" : "It wasn't efective.\n";
            Logger.log(pokeInfo + " uses attack. " + message);
        }

        endTurn();
        lock.unlock();
    }
}
