package pokemons;

public class Ability {
    private int damage;
    private boolean stun;
    private boolean dodge;
    private int cooldown;
    final private int baseCooldown;

    public Ability(int damage, boolean stun, boolean dodge, int cooldown) {
        this.damage = damage;
        this.stun = stun;
        this.dodge = dodge;
        this.cooldown = 0;
        this.baseCooldown = cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void resetCooldown() {
        cooldown = baseCooldown;
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
        String stunStr = stun ? "Stun " : "";
        String dodgeStr = dodge ? "Dodge " : "";
        return "{Damage: " + damage + " " + stunStr + dodgeStr + "Cooldown: " + cooldown + "}";
    }
}
