package com.tenpo.challenge.repository;

import com.tenpo.challenge.entity.jpa.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

  Optional<UserJpaEntity> findByUsername(String username);

  boolean existsByUsername(String username);
}
