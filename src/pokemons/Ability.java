package pokemons;

public class Ability {
    private int damage;
    private boolean stun;
    private boolean dodge;
    private int cooldown;

    public Ability(int damage, boolean stun, boolean dodge, int cooldown) {
        this.damage = damage;
        this.stun = stun;
        this.dodge = dodge;
        this.cooldown = cooldown;
    }
}
