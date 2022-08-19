package com.example.interviewperson.person;

import com.example.interviewperson.person.data.PersonDocument;
import com.example.interviewperson.person.service.PersonService;
import com.example.interviewperson.person.web.PersonController;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;


/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@WebFluxTest(controllers = PersonController.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class PersonWebFluxTest {
    @Autowired
    WebTestClient webClient;

    @MockBean
    private PersonService personService;


    @Test
    void person() {
        webClient
                .get()
                .uri("/person")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Integer.class)
                .hasSize(3);

        Flux<Integer> flux = webClient
                .get()
                .uri("/person")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(flux)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

    @Test
    void personStream() {
        Flux<Long> flux = webClient
                .get()
                .uri("/person/personStream")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(Long.class)
                .getResponseBody();


        StepVerifier.create(flux)
                .expectNext(0L, 1L, 2L)
                .thenCancel().verify();
    }


    @Test
    void savePerson() {
        PersonDocument body = new PersonDocument(null, "salam", Lists.newArrayList("A", "B"));

        when(personService.savePerson(Mockito.isA(PersonDocument.class)))
                .thenReturn(Mono.just(new PersonDocument("1", "salam", Lists.newArrayList("A"))));

        webClient
                .post()
                .uri("/person")
                .bodyValue(body)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(PersonDocument.class)
                .consumeWith(person -> {
                    PersonDocument responseBody = person.getResponseBody();
                    Assertions.assertNotNull(responseBody.getPersonId());
                });
    }
}
