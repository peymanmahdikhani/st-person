package com.example.interviewperson.person.data;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Created by Peyman Mahdikhani on 8/18/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
public interface PersonMongoRepository extends ReactiveMongoRepository<PersonDocument, String> {
    Flux<PersonDocument> findByNameContaining(String name);
}
