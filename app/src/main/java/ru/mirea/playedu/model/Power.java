package ru.mirea.playedu.model;

import java.net.URL;

public class Power {

    private int powerId;
    private int userPowerId;
    private String title;
    private String description;
    private boolean bought;
    private int iconId;
    private int priceType;
    private int effectType;
    private int price;


    public Power(int powerId, int userPowerId, String title, String description, boolean bought,
                 int icon, int priceType, int effectType, int price) {
        this.powerId = powerId;
        this.userPowerId = userPowerId;
        this.title = title;
        this.description = description;
        this.bought = bought;
        this.iconId = icon;
        this.priceType = priceType;
        this.effectType = effectType;
        this.price = price;
    }

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    public int getUserPowerId() {
        return userPowerId;
    }

    public void setUserPowerId(int userPowerId) {
        this.userPowerId = userPowerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public int getIcon() {
        return iconId;
    }

    public void setIcon(int icon) {
        this.iconId = icon;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public int getEffectType() {
        return effectType;
    }

    public void setEffectType(int effectType) {
        this.effectType = effectType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Power power = (Power) o;

        if (powerId != power.powerId) return false;
        if (userPowerId != power.userPowerId) return false;
        if (bought != power.bought) return false;
        if (priceType != power.priceType) return false;
        if (price != power.price) return false;
        if (!title.equals(power.title)) return false;
        if (!description.equals(power.description)) return false;
        return iconId == power.iconId;
    }

    @Override
    public int hashCode() {
        int result = powerId;
        result = 31 * result + userPowerId;
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (bought ? 1 : 0);
        result = 31 * result + iconId;
        result = 31 * result + priceType;
        result = 31 * result + price;
        return result;
    }
}
