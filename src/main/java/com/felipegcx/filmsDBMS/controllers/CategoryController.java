package com.felipegcx.filmsDBMS.controllers;

import com.felipegcx.filmsDBMS.models.Categories;
import com.felipegcx.filmsDBMS.services.CategoryService;
import com.felipegcx.filmsDBMS.services.FormatingService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//*||||||||||||||||||||*\\
//* CategoryController *\\
//*||||||||||||||||||||*\\

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

  private final CategoryService categoryService;
  private final FormatingService formatService;

  public CategoryController(
    CategoryService categoryService,
    FormatingService formatService
  ) {
    this.categoryService = categoryService;
    this.formatService = formatService;
  }

  @PostMapping("/create/category")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> newCategory(@RequestBody Categories category) {
    Categories result = categoryService.saveCategory(category);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/category/id={id}")
  ResponseEntity<?> getCategory(@PathVariable Integer id) {
    Categories result = categoryService.getCategoryById(id);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/category/name={name}")
  ResponseEntity<?> getCategoryName(@PathVariable String name) {
    Categories result = categoryService.getCategoryByName(name);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/categories")
  ResponseEntity<?> getCategories() {
    List<Categories> results = categoryService.getCategories();
    Map<String, Object> response = formatService.formatData(results);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/update/category/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> updateCategory(
    @RequestBody Categories modCategory,
    @PathVariable Integer id
  ) {
    Categories result = categoryService.updateCategory(modCategory, id);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/delete/category/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
    String result = categoryService.deleteCategory(id);
    Map<String, Object> response = formatService.formatMessageResponse(
      result,
      false
    );
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
