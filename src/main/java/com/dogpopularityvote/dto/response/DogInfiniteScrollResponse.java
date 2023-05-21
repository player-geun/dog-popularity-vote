package com.dogpopularityvote.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DogInfiniteScrollResponse implements Serializable {

    private List<DogResponse> data;
    private Boolean hasNext;
    private Integer pageNumber;
}
