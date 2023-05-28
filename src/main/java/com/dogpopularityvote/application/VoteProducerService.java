package com.dogpopularityvote.application;

import com.dogpopularityvote.dto.request.DogVoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VoteProducerService {

    private static final String TOPIC = "vote";

    private final KafkaTemplate<String, DogVoteRequest> kafkaTemplate;

    public void sendVote(DogVoteRequest request) {
        kafkaTemplate.send(TOPIC, request);
    }
}
