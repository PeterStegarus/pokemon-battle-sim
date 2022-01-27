package game;

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
        System.out.println("<attack> " + attacker.getName() + " attacks " + target.getName());
        // intai, initializeaza lupta: veririca stunurile si redu cooldownurile la amandoi
        lock.lock();
        boolean skip = attacker.isStunned();
        attacker.startTurn();
        if (skip) {
            System.out.println(attacker.getName() + " is stunned, skip!");
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
            System.out.println("\t1" + attacker.getName() + " started turn");
            if (attacker.getAttack() != null) {
                target.sufferAttack(attacker.getAttack());
            } else {
                target.sufferSpecialAttack(attacker.getSpecialAttack());
            }
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
        if (!skip)
            System.out.println("\t3" + attacker.getName() + " ended turn");
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
        System.out.println("<ability " + this.ability.toString() + "> " + attacker.getName() + " attacks " + target.getName());
        // intai, initializeaza lupta: veririca stunurile si redu cooldownurile la amandoi
        lock.lock();
        boolean skip = attacker.isStunned();
        attacker.startTurn();
        if (skip) {
            System.out.println(attacker.getName() + " is stunned, skip!");
        } else {
            // dodge-ul trebuie setat dinaintea turei pentru ca este valabil doar pentru tura curenta
            if (ability.isDodge()) {
                System.out.println("\t" + attacker.getName() + " uses dodge ability!");
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
            System.out.println("\t1" + attacker.getName() + " started turn");
            // dupa ce sunt amandoi pregatiti pentru o tura noua, doar aplica damage-ul abilitatii (daca e cazul, de dodge se ocupa cel atacat)
            target.sufferAbilityDamage(ability.getDamage());
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
            System.out.println("\t3" + attacker.getName() + " ended turn");
            // dupa ce s-a terminat tura, aplic stun-ul care va fi valabil pentru tura urmatoare
            if (ability.isStun())
                target.stun();
            ability.resetCooldown();
        }

        lock.unlock();
    }
}