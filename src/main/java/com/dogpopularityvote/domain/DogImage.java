package com.dogpopularityvote.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Embeddable
public class DogImage {

    @Column(name = "image_name", nullable = false)
    private String name;

    @Column(name = "image_path", nullable = false)
    private String path;

    public String makeFullPath() {
        return path + name;
    }
}
