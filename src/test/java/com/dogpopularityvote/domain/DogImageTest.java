package com.dogpopularityvote.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DogImageTest {

    @Test
    void 이미지의_완전한_경로를_만든다() {
        // given
        DogImage image = new DogImage("dog.jpg", "/downloads/");

        // when
        String result = image.makeFullPath();

        // then
        assertThat(result).isEqualTo("/downloads/dog.jpg");
    }
}
