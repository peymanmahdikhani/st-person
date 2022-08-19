package com.example.interviewperson.person.service;

import com.example.interviewperson.person.data.PersonDocument;
import com.example.interviewperson.person.data.PersonMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by Peyman Mahdikhani on 8/18/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonMongoRepository personMongoRepository;

    @Override
    public Mono<PersonDocument> savePerson(PersonDocument personDocument) {
        return personMongoRepository.save(personDocument);
    }
}
