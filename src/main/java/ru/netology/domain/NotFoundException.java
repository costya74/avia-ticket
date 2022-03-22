package ru.netology.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class NotFoundException extends RuntimeException {
    public NotFoundException(String s) {
        super(s);
    }
}
