package ru.mirea.playedu.model;

import java.util.Calendar;

public class UserTaskFilter {

    private String filteredCategoryTitle;
    private Calendar filteredDate;
    private Integer filteredColor;

    public UserTaskFilter() {
        this.filteredColor = null;
        this.filteredDate = null;
        this.filteredCategoryTitle = null;
    }

    public String getFilteredCategory() {
        return filteredCategoryTitle;
    }

    public void setFilteredCategory(String filteredCategory) {
        this.filteredCategoryTitle = filteredCategory;
    }

    public Calendar getFilteredDate() {
        return filteredDate;
    }

    public void setFilteredDate(Calendar filteredDate) {
        this.filteredDate = filteredDate;
    }

    public Integer getFilteredColor() {
        return filteredColor;
    }

    public void setFilteredColor(Integer filteredColor) {
        this.filteredColor = filteredColor;
    }
}
