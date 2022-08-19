package com.example.interviewperson.person;

import com.example.interviewperson.person.data.PersonDocument;
import com.example.interviewperson.person.data.PersonMongoRepository;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

/**
 * Created by Peyman Mahdikhani on 8/18/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@DataMongoTest
@ActiveProfiles("test")
public class PersonDataTest {
    @Autowired
    private PersonMongoRepository personMongoRepository;

    @BeforeEach
    void setup(){
        ArrayList<PersonDocument> data = Lists.newArrayList(
                new PersonDocument(null, "peyman", Lists.newArrayList("A", "B")),
                new PersonDocument(null, "setareh", Lists.newArrayList("C", "B")),
                new PersonDocument(null, "kasra", Lists.newArrayList("A", "D"))
        );

        personMongoRepository.saveAll(data).blockLast();
    }

    @AfterEach
    void tearDown(){
        personMongoRepository.deleteAll().block();
    }

    @Test
    void findAll(){
        Flux<PersonDocument> all = personMongoRepository.findAll();
        StepVerifier.create(all).expectNextCount(3).verifyComplete();
    }

    @Test
    void delete(){
        PersonDocument personDocument = personMongoRepository.findAll().next().block();
        personMongoRepository.delete(personDocument).block();
        Flux<PersonDocument> all = personMongoRepository.findAll();
        StepVerifier.create(all).expectNextCount(2).verifyComplete();
    }

    @Test
    void findByNameContaining(){
        Flux<PersonDocument> all = personMongoRepository.findByNameContaining("e");
        StepVerifier.create(all).expectNextCount(2).verifyComplete();
    }
}
