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

    @Column(name = "photo_name", nullable = false)
    private String photoName;

    @Column(name = "photo_path", nullable = false)
    private String photoPath;

    @Column(name = "vote_count", nullable = false)
    private int voteCount;

    public Dog(String name, String description, String photoName, String photoPath, int voteCount) {
        this.name = name;
        this.description = description;
        this.photoName = photoName;
        this.photoPath = photoPath;
        this.voteCount = voteCount;
    }
}
