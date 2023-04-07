package ru.mirea.playedu.model;

public class Category {

    private int categoryId;
    private String title;

    public Category(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != category.categoryId) return false;
        return title.equals(category.title);
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + title.hashCode();
        return result;
    }
}
