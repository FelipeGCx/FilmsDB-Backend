package com.felipegcx.filmsDBMS.services;

import java.util.List;
import com.felipegcx.filmsDBMS.models.Categories;

//*|||||||||||||||||*\\
//* CategoryService *\\
//*|||||||||||||||||*\\

public interface CategoryService {
  public Categories saveCategory(Categories category);

  public Categories updateCategory(Categories modCategory, Integer id);

  public String deleteCategory(Integer id);

  public Categories getCategoryById(Integer id);

  public Categories getCategoryByName(String name);

  public List<Categories> getCategories();
}
