package com.felipegcx.filmsDBMS.repositories;

import java.util.List;
import com.felipegcx.filmsDBMS.models.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

//*||||||||||||||||||||*\\
//* CategoryRepository *\\
//*||||||||||||||||||||*\\

public interface CategoryRepository
  extends MongoRepository<Categories, Integer> {
  @Query(value = "{'category': {$regex : ?0, $options: 'i'}}")
  Categories findByName(String category);

  @Query(value = "{}", sort = "{id: -1}")
  List<Categories> findLast();
}
