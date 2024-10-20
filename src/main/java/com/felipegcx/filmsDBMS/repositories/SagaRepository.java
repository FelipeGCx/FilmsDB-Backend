package com.felipegcx.filmsDBMS.repositories;

import java.util.List;
import com.felipegcx.filmsDBMS.models.Saga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

//*||||||||||||||||*\\
//* SagaRepository *\\
//*||||||||||||||||*\\

public interface SagaRepository extends MongoRepository<Saga, Integer> {
  @Query(value = "{'saga': {$regex : ?0, $options: 'i'}}")
  Saga findByName(String saga);

  @Query(value = "{}", sort = "{id: -1}")
  List<Saga> findLast();
}
