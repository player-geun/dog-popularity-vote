package com.dogpopularityvote.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dog dog;

    private boolean voted;

    @Column(nullable = false)
    private String clientIp;

    public Vote(Dog dog, boolean voted, String clientIp) {
        this.dog = dog;
        this.voted = voted;
        this.clientIp = clientIp;
    }

    public void changeVoted(boolean voted) {
        this.voted = voted;
    }

    public boolean isDuplicated(boolean voted) {
        return this.voted == voted;
    }
}
