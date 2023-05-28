package com.dogpopularityvote.application;

import com.dogpopularityvote.domain.Dog;
import com.dogpopularityvote.domain.DogRepository;
import com.dogpopularityvote.domain.Vote;
import com.dogpopularityvote.domain.VoteRepository;
import com.dogpopularityvote.dto.request.DogVoteRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class VoteConsumerService {

    private final VoteRepository voteRepository;
    private final DogRepository dogRepository;

    @Transactional
    @KafkaListener(topics = "vote", groupId = "manager")
    public void receiveVote(DogVoteRequest request) {
        Dog dog = dogRepository.getById(request.getDogId());
        Optional<Vote> vote = voteRepository.findByDogIdAndClientIp(request.getDogId(), request.getClientIp());

        if (vote.isEmpty()) {
            Vote savedVote = voteRepository.save(new Vote(dog, false, request.getClientIp()));
            dog.vote(savedVote, request.isVoted());
            return;
        }

        dog.vote(vote.get(), request.isVoted());
    }
}
