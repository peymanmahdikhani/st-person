package com.example.interviewperson.person;

import com.example.interviewperson.person.data.PersonDocument;
import com.example.interviewperson.person.service.PersonService;
import com.example.interviewperson.person.web.PersonController;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


/**
 * Created by Peyman Mahdikhani on 8/18/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@WebFluxTest(controllers = PersonController.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class PersonWebFluxUnitTest {
    @Autowired
    WebTestClient webClient;

    @MockBean
    private PersonService personService;

    @Test
    void test() {
        when(personService.savePerson(new PersonDocument(Mockito.anyString(), Mockito.anyString(), Mockito.anyList())))
                .thenReturn(Mono.just(new PersonDocument("1", "salam", Lists.newArrayList("A"))));

        webClient
                .post()
                .uri("/person")
                .bodyValue(new PersonDocument(null, "salam", com.google.common.collect.Lists.newArrayList("A", "B")))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(PersonDocument.class)
                .consumeWith(person -> {
                    PersonDocument responseBody = person.getResponseBody();
                    Assertions.assertEquals("1", responseBody.getPersonId());
                });
    }
}
