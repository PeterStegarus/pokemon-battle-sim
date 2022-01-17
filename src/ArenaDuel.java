import pokemons.Pokemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArenaDuel extends ArenaEvent {
    public ArenaDuel(Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2) {
        super(trainer1, pokemon1, trainer2, pokemon2);
    }

    private void execTurn(Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2, int i) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(trainer1.giveCommand(pokemon1, pokemon2));
        executor.execute(trainer2.giveCommand(pokemon2, pokemon1));
        executor.shutdown();

        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("######################################### oopsie ##############3#################################");
                System.exit(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[" + i + "] Turn " + i + " result: " + pokemon1.getName() + "_" + pokemon1.getHp() + " " + pokemon2.getName() + "_" + pokemon2.getHp());
    }

    // returns true if neutrel loses, false otherwise
    private String duel(Trainer trainer1, Pokemon pokemon1, Trainer trainer2, Pokemon pokemon2) {
        System.out.println("------------------\n" + trainer1.getName() + ": " + pokemon1.toString());
        System.out.println("vs");
        System.out.println(trainer2.getName() + ": " + pokemon2.toString());
        System.out.println("------Fight!------\n");
        int i = 0;

        while (!pokemon1.isDefeated() && !pokemon2.isDefeated()) {
            execTurn(trainer1, pokemon1, trainer2, pokemon2, i);
            i++;
        }

        String winner;

        if (pokemon1.isDefeated()) {
            winner = pokemon2.toString();
            pokemon2.reset();
            pokemon2.evolve();
        } else {
            winner = pokemon1.toString();
            pokemon1.reset();
            pokemon1.evolve();
        }

        return winner;
    }

    public void fight() {
        System.out.println("Winner:" + duel(trainer1, pokemon1, trainer2, pokemon2));

        System.out.println(pokemon1.toString());
        System.out.println(pokemon2.toString());
//        System.out.println(trainer1.toString());
//        System.out.println(trainer2.toString());
    }
}
