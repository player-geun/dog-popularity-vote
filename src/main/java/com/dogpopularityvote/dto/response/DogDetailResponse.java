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
    private String photoName;
    private String photoPath;
    private int voteCount;

    public DogDetailResponse(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.photoName = dog.getImage().getName();
        this.photoPath = dog.getImage().getPath();
        this.voteCount = dog.getTotalVoteCount();
    }
}
