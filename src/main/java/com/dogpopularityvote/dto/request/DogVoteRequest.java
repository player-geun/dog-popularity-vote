package com.dogpopularityvote.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DogVoteRequest {

    private Long dogId;
    private int vote;
}
