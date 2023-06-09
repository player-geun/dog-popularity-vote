package com.dogpopularityvote.application;

import com.dogpopularityvote.domain.Dog;
import com.dogpopularityvote.domain.DogImage;
import com.dogpopularityvote.domain.DogRepository;
import com.dogpopularityvote.dto.response.DogDetailResponse;
import com.dogpopularityvote.dto.response.DogInfiniteScrollResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DogServiceTest {

    @Autowired
    private DogService dogService;

    @Autowired
    private DogRepository dogRepository;

    @BeforeEach
    void setUp() {
        dogRepository.save(
                new Dog("코코", "코코입니다.", new DogImage("coco", "/downloads/"), 21));
    }

    @Test
    void 모든_강아지를_페이지_단위로_조회한다() {
        // given
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "updatedAt");

        // when
        DogInfiniteScrollResponse response = dogService.findAllWithPage(pageable);

        // then
        assertThat(response.getData().get(0).getName()).isEqualTo("코코");
    }

    @Test
    void 강아지의_상세정보를_조회한다() {
        // given
        Long dogId = 21L;

        // when
        DogDetailResponse response = dogService.findDetailById(dogId);

        // then
        assertThat(response.getId()).isEqualTo(dogId);
    }
}
