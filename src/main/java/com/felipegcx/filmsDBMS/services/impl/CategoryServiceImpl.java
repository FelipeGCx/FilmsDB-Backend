package com.felipegcx.filmsDBMS.services.impl;

import com.felipegcx.filmsDBMS.exceptions.NotFoundException;
import com.felipegcx.filmsDBMS.models.Categories;
import com.felipegcx.filmsDBMS.repositories.CategoryRepository;
import com.felipegcx.filmsDBMS.services.CategoryService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

//*|||||||||||||||||||||*\\
//* CategoryServiceImpl *\\
//*|||||||||||||||||||||*\\

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepo;

  public CategoryServiceImpl(CategoryRepository categoryRepo) {
    this.categoryRepo = categoryRepo;
  }

  @Override
  public Categories saveCategory(Categories category) {
    // parse the category name to capital case
    category.setCategory(StringUtils.capitalize(category.getCategory()));
    // find the last category to set id
    Categories lastCategory = categoryRepo.findLast().get(0);
    Integer id = lastCategory.getId() + 1;
    category.setId(id);
    // save the category
    Categories response = categoryRepo.save(category);
    return response;
  }

  @Override
  public Categories updateCategory(Categories modCategory, Integer id) {
    // find the category
    Categories category = categoryRepo.findById(id).orElse(null);
    if (category == null) {
      throw new NotFoundException("Category not found");
    }
    // set the values with modifications
    category.setCategory(
        (modCategory.getCategory() == null)
            ? category.getCategory()
            : modCategory.getCategory());
    category.setSvg(
        (modCategory.getSvg() == null) ? category.getSvg() : modCategory.getSvg());
    // update the category
    Categories response = categoryRepo.save(category);
    return response;
  }

  @Override
  public String deleteCategory(Integer id) {
    // find the category
    Categories category = categoryRepo.findById(id).orElse(null);
    if (category == null) {
      throw new NotFoundException("Category not found");
    }
    // delete category
    categoryRepo.deleteById(id);
    return "Category deleted";
  }

  @Override
  public Categories getCategoryById(Integer id) {
    // find the category
    Categories category = categoryRepo.findById(id).orElse(null);
    if (category == null) {
      throw new NotFoundException("Category not found");
    }
    // return the category if exist
    return category;
  }

  @Override
  public Categories getCategoryByName(String name) {
    // find the category
    Categories category = categoryRepo.findByName(name);
    return category;
  }

  @Override
  public List<Categories> getCategories() {
    // find the all categories
    List<Categories> response = categoryRepo.findAll();
    return response;
  }
}
