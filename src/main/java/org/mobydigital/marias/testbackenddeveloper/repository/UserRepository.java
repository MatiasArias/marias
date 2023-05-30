package org.mobydigital.marias.testbackenddeveloper.repository;

import org.mobydigital.marias.testbackenddeveloper.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findOneByUsername(String username);
}
