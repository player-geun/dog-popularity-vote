package com.dogpopularityvote.domain;

import com.dogpopularityvote.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Dog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Embedded
    private DogImage image;

    @Column(name = "vote_count", nullable = false)
    private int totalVoteCount;

    public Dog(String name, String description, DogImage image, int totalVoteCount) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.totalVoteCount = totalVoteCount;
    }

    public void vote(Vote vote, boolean voted) {
        if (vote.isDuplicated(voted)) {
            return;
        }

        vote.changeVoted(voted);
        if (voted) {
            totalVoteCount++;
            return;
        }
        totalVoteCount--;
    }
}
