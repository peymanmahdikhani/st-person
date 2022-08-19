package com.example.interviewperson.person.service;

import com.example.interviewperson.person.data.PersonDocument;
import reactor.core.publisher.Mono;

/**
 * Created by Peyman Mahdikhani on 8/18/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
public interface PersonService {
    Mono<PersonDocument> savePerson(PersonDocument personDocument);
}
