package com.dogpopularityvote.dto.response;

import com.dogpopularityvote.domain.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DogResponse implements Serializable {

    private Long id;
    private String name;
    private int voteCount;

    public DogResponse(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.voteCount = dog.getVoteCount();
    }
}
