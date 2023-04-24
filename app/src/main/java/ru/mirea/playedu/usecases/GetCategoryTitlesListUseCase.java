package ru.mirea.playedu.usecases;

import java.util.ArrayList;

import ru.mirea.playedu.data.repository.CategoryRepository;
import ru.mirea.playedu.model.Category;

// Получение списка категорий
public class GetCategoryTitlesListUseCase {


    private CategoryRepository categoryRepository;

    public GetCategoryTitlesListUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ArrayList<String> execute() {
        ArrayList<Category> categories = categoryRepository.getCategories();
        ArrayList<String> titles = new ArrayList<>();
        for (Category category: categories)
            titles.add(category.getTitle());
        return titles;
    }
}
