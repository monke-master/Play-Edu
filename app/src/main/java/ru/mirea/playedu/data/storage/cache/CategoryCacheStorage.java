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

    // Возвращает экземпляр класса
    // Если экземпляра нет, создает новый
    public static CategoryCacheStorage getInstance() {
        if (instance == null)
            instance = new CategoryCacheStorage();

        return instance;
    }

    // Возвращает категорию с заданным id, если она есть в списке
    // Иначе возвращает null
    public Category getCategoryById(int id) {
        for (Category category: categories) {
            if (category.getCategoryId() == id) {
                return category;
            }
        }
        return null;
    }

    // Возвращает список всех категорий
    public ArrayList<Category> getCategories() {
        return categories;
    }

    // Добавляет категорию в список
    public void addCategory(Category category) {
        categories.add(category);
    }

    // Заменяет категорию с заданным id на новую
    public boolean updateCategory(int categoryId, Category newCategory) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCategoryId() == categoryId) {
                categories.set(i, newCategory);
                return true;
            }
        }
        return false;
    }

    // Удаляет категорию с заданным id
    public boolean deleteCategory(Category category) {
        return categories.remove(category);
    }

}
