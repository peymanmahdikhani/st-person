package com.example.interviewperson.person.web;

import com.example.interviewperson.person.data.PersonDocument;
import com.example.interviewperson.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> getPerson(@PathVariable("personId") Long personId) {
        return Mono.just("salam");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Integer> getPerson() {
        return Flux.just(1, 2, 3);
    }

    @GetMapping(value = "/personStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Long> getPersonStream() {
        return Flux.interval(Duration.ofSeconds(1));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonDocument> savePerson(@RequestBody @Valid PersonDocument personDocument){
        return personService.savePerson(personDocument);
    }
}
