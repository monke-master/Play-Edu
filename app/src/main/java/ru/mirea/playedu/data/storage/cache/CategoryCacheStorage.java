package ru.mirea.playedu.data.storage.cache;

import java.util.ArrayList;

import ru.mirea.playedu.model.Category;
// Класс для хранения категорий в кэше
// Реализует паттерн Singleton
public class CategoryCacheStorage {

    // Список категорий
    private ArrayList<Category> categories;

    private static CategoryCacheStorage instance = null;

    private CategoryCacheStorage() {
        categories = new ArrayList<>();
    }

    private static CategoryCacheStorage getInstance() {
        if (instance == null)
            instance = new CategoryCacheStorage();

        return instance;
    }

    // Получение всех категорий
    public ArrayList<Category> getCategories() {
        return categories;
    }

    // Добавление категории
    public void addCategory(Category category) {
        categories.add(category);
    }

    // Редактирование категории
    public boolean updateCategory(int categoryId, Category newCategory) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCategoryId() == categoryId) {
                categories.set(i, newCategory);
                return true;
            }
        }
        return false;
    }

    // Удаление категории
    public boolean deleteCategory(Category category) {
        return categories.remove(category);
    }

}
