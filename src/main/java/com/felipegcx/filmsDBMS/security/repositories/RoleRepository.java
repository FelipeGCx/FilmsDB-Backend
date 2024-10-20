package com.felipegcx.filmsDBMS.security.repositories;

import com.felipegcx.filmsDBMS.security.models.ERole;
import com.felipegcx.filmsDBMS.security.models.Role;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

//*||||||||||||||||*\\
//* RoleRepository *\\
//*||||||||||||||||*\\

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
