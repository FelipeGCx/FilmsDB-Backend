package com.felipegcx.filmsDBMS.security.repositories;

import com.felipegcx.filmsDBMS.security.models.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

//*||||||||||||||||*\\
//* UserRepository *\\
//*||||||||||||||||*\\

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  User getByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
