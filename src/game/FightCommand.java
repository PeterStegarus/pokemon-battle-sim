package game;

import logger.Logger;
import pokemons.Ability;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FightCommand implements Runnable {
    protected final FighterPokemon attacker;
    protected final FighterPokemon target;
    protected static Lock lock = new ReentrantLock();
    protected static Condition startedTurn = lock.newCondition();
    protected static Condition endedTurn = lock.newCondition();

    public FightCommand(FighterPokemon attacker, FighterPokemon target) {
        this.attacker = attacker;
        this.target = target;
    }
}

class AttackFightCommand extends FightCommand {
    public AttackFightCommand(FighterPokemon attacker, FighterPokemon target) {
        super(attacker, target);
    }

    @Override
    public void run() {
        // intai, initializeaza lupta: verifica stunurile si redu cooldownurile la amandoi
        lock.lock();
        boolean skip = attacker.isStunned();
        attacker.startTurn();
        if (skip) {
            Logger.log("\t" + attacker.getName() + " is stunned!\n");
        }

        // ambii pokemoni trebuie sa fi trecut prin etapa de inceperea turului
        while (!target.isFighting()) {
            try {
                startedTurn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        startedTurn.signal();

        if (!skip) {
            boolean result;
            if (attacker.getAttack() != null) {
                result = target.sufferAttack(attacker.getAttack());
            } else {
                result = target.sufferSpecialAttack(attacker.getSpecialAttack());
            }
            String message = result ? "It was effective!\n" : "It wasn't efective.\n";
            Logger.log("\t" + attacker.getName() + " uses attack. " + message);
        }

        // trebuie sa termine amandoi
        attacker.preEndTurn();
        // asteapta-l pe celalalt sa termine, in sensul ori preend ori end
        while (!target.isFinished()) {
            if (!target.isFighting())
                break;
            try {
                endedTurn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endedTurn.signal();
        attacker.endTurn();
        lock.unlock();
    }
}

class AbilityFightCommand extends FightCommand {
    Ability ability;

    public AbilityFightCommand(FighterPokemon attacker, FighterPokemon target, int ability) {
        super(attacker, target);
        this.ability = attacker.getAbilities()[ability];
    }

    @Override
    public void run() {
        // intai, initializeaza lupta: veririca stunurile si redu cooldownurile la amandoi
        lock.lock();
        boolean skip = attacker.isStunned();
        attacker.startTurn();
        if (skip) {
            Logger.log(attacker.getName() + " is stunned.\n");
        } else {
            // dodge-ul trebuie setat dinaintea turei pentru ca este valabil doar pentru tura curenta
            if (ability.isDodge()) {
                attacker.willDodge();
            }
        }

        // ambii pokemoni trebuie sa fi trecut prin etapa de inceperea turului
        while (!target.isFighting()) {
            try {
                startedTurn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        startedTurn.signal();

        if (!skip) {
            // dupa ce sunt amandoi pregatiti pentru o tura noua, doar aplica damage-ul abilitatii (daca e cazul, de dodge se ocupa cel atacat)
            boolean result = target.sufferAbilityDamage(ability.getDamage());
            String message = result ? "It was effective!\n" : "It wasn't efective.\n";
            Logger.log("\t" + attacker.getName() + " uses ability [" + ability.toString() + "]. " + message);
        }

        // trebuie sa termine amandoi
        attacker.preEndTurn();
        // asteapta-l pe celalalt sa termine, in sensul ori preend ori end
        while (!target.isFinished()) {
            if (!target.isFighting())
                break;
            try {
                endedTurn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endedTurn.signal();
        attacker.endTurn();

        if (!skip) {
            // dupa ce s-a terminat tura, aplic stun-ul care va fi valabil pentru tura urmatoare
            if (ability.isStun())
                target.stun();
            ability.resetCooldown();
        }

        lock.unlock();
    }
}