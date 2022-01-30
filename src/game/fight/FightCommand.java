package game.fight;

import game.FighterPokemon;
import logger.Logger;

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

    // init turn: reduce cooldowns check if attacker should be skipped (stunned)
    protected boolean initTurn() {
        boolean skip = attacker.isStunned();
        attacker.startTurn();
        if (skip) {
            Logger.log("\t" + attacker.getName() + " is stunned!\n");
        }

        return skip;
    }

    // both pokemons should have passed the init phase of current turn before going to the fight phase
    protected void waitInit() {
        while (!target.isFighting()) {  // wait for the other to finish init
            try {
                startedTurn.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        startedTurn.signal();
    }

    // both pokemons should end their fight phase before going into the end turn phase
    protected void endTurn() {
        attacker.preEndTurn();
        while (!target.isFinished()) {  // wait for the other to finish fight
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
    }

    public abstract void run();
}

