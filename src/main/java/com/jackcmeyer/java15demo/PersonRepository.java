package com.jackcmeyer.java15demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("""
           FROM Person p
           WHERE p.id = :id
           """)
    Optional<Person> findById(Long id);
}
