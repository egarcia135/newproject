package io.eduardogarcia.ppmtool.repositories;

import io.eduardogarcia.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User getById(Long id);


}
