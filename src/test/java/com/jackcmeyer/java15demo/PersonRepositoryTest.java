package com.jackcmeyer.java15demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void findByIdShouldFindThePerson() {
        Person person = personRepository.save(new Person("test1", "Software Engineer"));

        Optional<Person> maybePerson = personRepository.findById(person.getId());

        assertThat(maybePerson).isPresent();
        assertThat(maybePerson.get().getId()).isEqualTo(person.getId());
        assertThat(maybePerson.get().getName()).isEqualTo(person.getName());
        assertThat(maybePerson.get().getOccupation()).isEqualTo(person.getOccupation());
    }
}
