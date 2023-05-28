package com.dogpopularityvote.application;

import com.dogpopularityvote.domain.Dog;
import com.dogpopularityvote.domain.DogImage;
import com.dogpopularityvote.domain.DogRepository;
import com.dogpopularityvote.dto.request.DogVoteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.assertj.core.api.Assertions.assertThat;

@EmbeddedKafka
@SpringBootTest
class VoteProducerServiceTest {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private VoteProducerService voteProducerService;

    @BeforeEach
    void setUp() {
        dogRepository.save(
                new Dog("코코", "코코입니다.", new DogImage("coco", "/downloads/"), 21));
    }

    @Test
    void 투표를_한다() throws Exception {
        // given
        Long dogId = 21L;
        DogVoteRequest request = new DogVoteRequest(dogId, true, "0:0:0:0:0:0:0:1");

        // when
        voteProducerService.sendVote(request);
        Thread.sleep(10000);

        // then
        assertThat(dogRepository.getById(dogId).getTotalVoteCount()).isEqualTo(22);
    }
}
