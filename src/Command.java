import pokemons.Ability;
import pokemons.Pokemon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Command implements Runnable {
    protected final Pokemon attacker;
    protected final Pokemon target;
    protected Lock lock = new ReentrantLock();
    protected Condition startedTurn = lock.newCondition();
    protected Condition endedTurn = lock.newCondition();

    public Command(Pokemon attacker, Pokemon target) {
        this.attacker = attacker;
        this.target = target;
    }
}

class AttackCommand extends Command {
    public AttackCommand(Pokemon attacker, Pokemon target) {
        super(attacker, target);
    }

    @Override
    public void run() {
        // intai, initializeaza lupta: veririca stunurile si redu cooldownurile la amandoi
        lock.lock();
        if (attacker.isStunned())
            return;

        attacker.startTurn();
        // ambii pokemoni trebuie sa fi trecut prin etapa de inceperea turului
        while (!attacker.isFighting() || !target.isFighting()) {
            try {
                startedTurn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        startedTurn.signal();

        if (attacker.getAttack() != null) {
            target.sufferAttack(attacker.getAttack());
        } else {
            target.sufferSpecialAttack(attacker.getSpecialAttack());
        }

        attacker.endTurn();
        endedTurn.signal();

        lock.unlock();
    }
}

class AbilityCommand extends Command {
    Ability ability;

    public AbilityCommand(Pokemon attacker, Pokemon target, int ability) {
        super(attacker, target);
        this.ability = attacker.getAbilities()[ability];
    }

    @Override
    public void run() {
        // intai, initializeaza lupta: veririca stunurile si redu cooldownurile la amandoi
        lock.lock();
        boolean skip = attacker.isStunned();
        attacker.startTurn();
        if (skip)
            return;

        // dodge-ul trebuie setat dinaintea turei pentru ca este valabil doar pentru tura curenta
        if (ability.isDodge())
            attacker.willDodge();

        // ambii pokemoni trebuie sa fi trecut prin etapa de inceperea turului
        while (!attacker.isFighting() || !target.isFighting()) {
            try {
                startedTurn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        startedTurn.signal();

        // dupa ce sunt amandoi pregatiti pentru o tura noua, doar aplica damage-ul abilitatii (daca e cazul, de dodge se ocupa cel atacat)
        target.sufferAbilityDamage(ability.getDamage());

        attacker.endTurn();
        while (attacker.isFighting() || target.isFighting()) {
            try {
                endedTurn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endedTurn.signal();

        // dupa ce s-a terminat tura, aplic stun-ul care va fi valabil pentru tura urmatoare
        if (ability.isStun())
            target.stun();

        lock.unlock();
    }
}