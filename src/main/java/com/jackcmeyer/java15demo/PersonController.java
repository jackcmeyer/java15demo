package com.jackcmeyer.java15demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<Person> post(@Validated @RequestBody PersonRequest personRequest) {
        Person p = personRepository.save(new Person(personRequest.name(), personRequest.occupation()));
        return ResponseEntity.created(URI.create("http://localhost:8080/person/" + p.getId())).body(p);
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable Long id) {
        return personRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
