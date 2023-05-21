package com.dogpopularityvote.exception;

public class NoSuchDogException extends RuntimeException {

    public NoSuchDogException(String message) {
        super(message);
    }

    public NoSuchDogException() {
        this("존재하지 않는 강아지입니다.");
    }
}
