package game.fight;

import game.FighterPokemon;
import logger.Logger;
import pokemons.Ability;

public class AbilityFightCommand extends FightCommand {
    Ability ability;

    public AbilityFightCommand(FighterPokemon attacker, FighterPokemon target, int ability) {
        super(attacker, target);
        this.ability = attacker.getAbilities()[ability];
    }

    @Override
    public void run() {
        lock.lock();
        String pokeInfo = "\t" + attacker.getName() + " (" + attacker.getHp() + ")";
        boolean skip = initTurn();
        if (!skip) {
            // dodge must be set during the init phase, before fighting, because it applies for the current turn.
            // dodge is then handled by FighterPokemon class
            if (ability.isDodge()) {
                attacker.willDodge();
            }
        }

        waitInit();

        if (!skip) {
            // when both pokemons enter the attack phase, only apply the damage of the ability
            boolean result = target.sufferAbilityDamage(ability.getDamage());
            String message = result ? "It was effective!\n" : "It wasn't efective.\n";
            Logger.log(pokeInfo + " uses ability [" + ability.toString() + "]. " + message);
        }

        endTurn();

        if (!skip) {
            // when turn's finished, apply stun to target, because it apples for the next turn
            if (ability.isStun())
                target.stun();
            ability.resetCooldown();
        }

        lock.unlock();
    }
}
