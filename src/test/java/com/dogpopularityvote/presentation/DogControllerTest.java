package com.dogpopularityvote.presentation;

import com.dogpopularityvote.application.VoteProducerService;
import com.dogpopularityvote.application.DogService;
import com.dogpopularityvote.dto.request.DogVoteRequest;
import com.dogpopularityvote.dto.response.DogDetailResponse;
import com.dogpopularityvote.dto.response.DogInfiniteScrollResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogController.class)
class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogService dogService;

    @Test
    void 모든_강아지를_페이지_단위로_조회한다() throws Exception {
        // given
        given(dogService.findAllWithPage(any(Pageable.class)))
                .willReturn(new DogInfiniteScrollResponse());

        // when & then
        mockMvc.perform(get("/api/dogs")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void 강아지의_상세정보를_조회한다() throws Exception {
        // given
        Long dogId = 1L;

        given(dogService.findDetailById(any()))
                .willReturn(new DogDetailResponse());

        // when & then
        mockMvc.perform(get("/api/dogs/{dogId}/details", dogId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
