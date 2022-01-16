import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import pokemons.Pokemon;

import java.util.Arrays;

public class Trainer {
    private String name;
    private int age;
    private Pokemon[] pokemons;

    public Trainer(String name, int age, Pokemon[] pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " " + age + " " + Arrays.toString(pokemons);
    }
// antrenorul da comenzi pokemonilor
}
