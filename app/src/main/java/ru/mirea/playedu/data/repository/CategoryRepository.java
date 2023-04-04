package ru.mirea.playedu.data.repository;

import java.util.ArrayList;

import ru.mirea.playedu.model.Category;
import ru.mirea.playedu.model.RepositoryResponse;
import ru.mirea.playedu.data.storage.cache.CategoryCacheStorage;

// Репозиторий для хранения пользовательских задач
public class CategoryRepository {

    private final CategoryCacheStorage cacheStorage;

    public CategoryRepository(CategoryCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    // Возвращает категорию с заданным id, если она есть в списке
    // Иначе возвращает null
    public Category getCategoryById(int id) {
        return cacheStorage.getCategoryById(id);
    }

    // Возвращает список всех категорий
    public ArrayList<Category> getCategories() {
        return cacheStorage.getCategories();
    }

    // Добавляет категорию в список
    public RepositoryResponse addCategory(Category category) {
        cacheStorage.addCategory(category);
        return new RepositoryResponse(200, "Success");
    }

    // Заменяет категорию с заданным id на новую
    public RepositoryResponse updateCategory(int categoryId, Category newCategory) {
        if (cacheStorage.updateCategory(categoryId, newCategory))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }

    // Удаляет категорию с заданным id
    public RepositoryResponse deleteCategory(Category category) {
        if (cacheStorage.deleteCategory(category))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }



}
