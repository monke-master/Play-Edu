package ru.mirea.playedu.data.repository;

import android.util.Log;

import java.util.ArrayList;

import ru.mirea.playedu.model.Category;
import ru.mirea.playedu.model.RepositoryResponse;
import ru.mirea.playedu.data.storage.cache.CategoryCacheStorage;

public class CategoryRepository {

    private final CategoryCacheStorage cacheStorage;

    public CategoryRepository(CategoryCacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    public ArrayList<Category> getCategories() {
        return cacheStorage.getCategories();
    }

    public ArrayList<String> getCategoriesTitles() {
        ArrayList<String> categoriesTitles = new ArrayList<String>();
        for (Category category : getCategories()) {
            if (!categoriesTitles.contains(category.getTitle())) categoriesTitles.add(category.getTitle());
        }
        return categoriesTitles;
    }

    public RepositoryResponse updateCategory(int categoryId, Category newCategory) {
        if (cacheStorage.updateCategory(categoryId, newCategory))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }

    public RepositoryResponse deleteCategory(Category category) {
        if (cacheStorage.deleteCategory(category))
            return new RepositoryResponse(200, "Success");
        else
            return new RepositoryResponse(404, "Not found");
    }

    public RepositoryResponse addCategory(Category category) {
        cacheStorage.addCategory(category);
        return new RepositoryResponse(200, "Success");
    }
}
