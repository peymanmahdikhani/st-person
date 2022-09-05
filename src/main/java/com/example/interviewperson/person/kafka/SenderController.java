package com.example.interviewperson.person.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by Peyman Mahdikhani on 9/5/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@RequestMapping("/kafka-sender")
@RestController
@Slf4j
public class SenderController {
    private final KafkaTemplate<String, Object> template;
    private final String topicName;
    private final int messagesPerRequest;
    private CountDownLatch latch;

    public SenderController(
            final KafkaTemplate<String, Object> template,
            @Value("${app.person.topic-name}") final String topicName,
            @Value("${app.person.message-per-request}") final int messagesPerRequest) {
        this.template = template;
        this.topicName = topicName;
        this.messagesPerRequest = messagesPerRequest;
    }

    @GetMapping("/send")
    public String hello() throws Exception {
        latch = new CountDownLatch(messagesPerRequest);
        IntStream.range(0, messagesPerRequest)
                .forEach(i -> {
                    this.template.send(topicName, String.valueOf(i), new PracticalAdvice(i, "A Practical Advice"));
                    latch.countDown();
                        }
                );
        latch.await(60, TimeUnit.SECONDS);
        log.info("All messages received");
        return "Hello Kafka!";
    }
}
