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

    public void reduceCooldown() {
        if (cooldown > 0)
            cooldown--;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isStun() {
        return stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public int getCooldown() {
        return cooldown;
    }

    public String toString() {
        return damage + " " + stun + " " + dodge + " " + cooldown;
    }
}
