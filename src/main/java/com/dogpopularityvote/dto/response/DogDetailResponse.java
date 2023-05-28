package com.dogpopularityvote.dto.response;

import com.dogpopularityvote.domain.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DogDetailResponse implements Serializable {

    private Long id;
    private String name;

    private String description;
    private int voteCount;

    public DogDetailResponse(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.description = dog.getDescription();
        this.voteCount = dog.getTotalVoteCount();
    }
}
