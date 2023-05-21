package com.dogpopularityvote.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void 강아지를_생성한다() {
        // given & when & then
        assertDoesNotThrow(() ->
                new Dog("코코", "코코입니다.", "coco", "/downloads/", 1));
    }
}
