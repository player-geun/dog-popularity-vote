package com.dogpopularityvote.exception;

public class NoSuchDogImageException extends RuntimeException {

    public NoSuchDogImageException(String message) {
        super(message);
    }

    public NoSuchDogImageException() {
        this("존재하지 않는 강아지 이미지입니다.");
    }
}
