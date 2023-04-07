package ru.mirea.playedu.model;

import java.net.URL;

public class Power {

    private int powerId;
    private int userPowerId;
    private String title;
    private String description;
    private boolean status;
    private URL icon;
    private int priceType;
    private int price;


    public Power(int powerId, int userPowerId, String title, String description, boolean status,
                 URL icon, int priceType, int price) {
        this.powerId = powerId;
        this.userPowerId = userPowerId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.icon = icon;
        this.priceType = priceType;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
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
        if (status != power.status) return false;
        if (priceType != power.priceType) return false;
        if (price != power.price) return false;
        if (!title.equals(power.title)) return false;
        if (!description.equals(power.description)) return false;
        return icon.equals(power.icon);
    }

    @Override
    public int hashCode() {
        int result = powerId;
        result = 31 * result + userPowerId;
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + icon.hashCode();
        result = 31 * result + priceType;
        result = 31 * result + price;
        return result;
    }
}
