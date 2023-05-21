package com.dogpopularityvote.application;

import com.dogpopularityvote.domain.Dog;
import com.dogpopularityvote.domain.DogRepository;
import com.dogpopularityvote.dto.request.DogVoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DogConsumerService {

    private final DogRepository dogRepository;

    @Transactional
    @KafkaListener(topics = "vote", groupId = "manager")
    public void receiveVote(DogVoteRequest request) {
        Dog dog = dogRepository.getById(request.getDogId());
        dog.doVote(request.getVote());
    }
}
