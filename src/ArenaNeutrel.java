import pokemons.Pokemon;
import pokemons.PokemonFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArenaNeutrel extends ArenaEvent {
    private Pokemon neutrel;

    public ArenaNeutrel(int neutrel, Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2) {
        super(trainer1, pokemon1, trainer2, pokemon2);
        this.neutrel = new PokemonFactory().make("Neutrel" + neutrel);
    }

    // returns true if neutrel loses, false otherwise
    private boolean neutrelFight(Trainer trainer, Pokemon pokemon) {
        System.out.println("lupta " + trainer.getName() + " cu " + pokemon.toString() + " impotriva " + neutrel.toString());
        int i = 0;

        while (!pokemon.isDefeated() && !neutrel.isDefeated()) {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            executor.execute(new AttackCommand(neutrel, pokemon));
            executor.execute(trainer.giveCommand(pokemon, neutrel));
            executor.shutdown();

            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS))
                    System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("[" + i + "] Tura " + i + ": " + pokemon.getName() + "_" + pokemon.getHp() + " " + neutrel.getName() + "_" + neutrel.getHp());
            i++;
        }

        boolean result = !pokemon.isDefeated();
        pokemon.reset();
        return result;
    }

    public void fight() {
        if (neutrelFight(trainer1, pokemon1))
            pokemon1.evolve();

        if (neutrelFight(trainer2, pokemon2))
            pokemon2.evolve();
    }
}