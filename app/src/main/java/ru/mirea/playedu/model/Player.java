package ru.mirea.playedu.model;

public class Player {

    private int health;
    private int damage;
    private int mistakeCount;

    public Player() {};

    public Player(int health, int damage, int mistakeCount) {
        this.health = health;
        this.damage = damage;
        this.mistakeCount = mistakeCount;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMistakeCount() {
        return mistakeCount;
    }

    public void setMistakeCount(int mistakeCount) {
        this.mistakeCount = mistakeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (health != player.health) return false;
        if (damage != player.damage) return false;
        return mistakeCount == player.mistakeCount;
    }

    @Override
    public int hashCode() {
        int result = health;
        result = 31 * result + damage;
        result = 31 * result + mistakeCount;
        return result;
    }
}
