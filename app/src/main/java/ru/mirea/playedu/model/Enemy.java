package ru.mirea.playedu.model;

import java.net.URL;

public class Enemy {

    private int enemyId;
    private int health;
    private int damage;
    private String name;
    private URL image;
    private int imageId;
    private int attackPhaseSpeed;
    private int defensePhaseSpeed;
    private int defencePhaseSpread;
    private int price;
    private int phaseTime;

    public Enemy() {

    }

    public Enemy(int id, int health, int damage, String name, URL image, int attackPhaseSpeed,
                 int defensePhaseSpeed, int defencePhaseSpread, int price, int phaseTime) {
        this.enemyId = id;
        this.health = health;
        this.damage = damage;
        this.name = name;
        this.image = image;
        this.attackPhaseSpeed = attackPhaseSpeed;
        this.defensePhaseSpeed = defensePhaseSpeed;
        this.defencePhaseSpread = defencePhaseSpread;
        this.price = price;
        this.phaseTime = phaseTime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public int getAttackPhaseSpeed() {
        return attackPhaseSpeed;
    }

    public void setAttackPhaseSpeed(int attackPhaseSpeed) {
        this.attackPhaseSpeed = attackPhaseSpeed;
    }

    public int getDefensePhaseSpeed() {
        return defensePhaseSpeed;
    }

    public void setDefensePhaseSpeed(int defensePhaseSpeed) {
        this.defensePhaseSpeed = defensePhaseSpeed;
    }

    public int getDefencePhaseSpread() {
        return defencePhaseSpread;
    }

    public void setDefencePhaseSpread(int defencePhaseSpread) {
        this.defencePhaseSpread = defencePhaseSpread;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPhaseTime() {
        return phaseTime;
    }

    public void setPhaseTime(int phaseTime) {
        this.phaseTime = phaseTime;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enemy enemy = (Enemy) o;

        if (enemyId != enemy.enemyId) return false;
        if (health != enemy.health) return false;
        if (damage != enemy.damage) return false;
        if (attackPhaseSpeed != enemy.attackPhaseSpeed) return false;
        if (defensePhaseSpeed != enemy.defensePhaseSpeed) return false;
        if (defencePhaseSpread != enemy.defencePhaseSpread) return false;
        if (price != enemy.price) return false;
        if (phaseTime != enemy.phaseTime) return false;
        if (!name.equals(enemy.name)) return false;
        return image.equals(enemy.image);
    }

    @Override
    public int hashCode() {
        int result = enemyId;
        result = 31 * result + health;
        result = 31 * result + damage;
        result = 31 * result + name.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + attackPhaseSpeed;
        result = 31 * result + defensePhaseSpeed;
        result = 31 * result + defencePhaseSpread;
        result = 31 * result + price;
        result = 31 * result + phaseTime;
        return result;
    }
}
