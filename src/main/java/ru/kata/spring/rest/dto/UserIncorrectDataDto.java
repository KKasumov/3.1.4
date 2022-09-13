package ru.kata.spring.rest.dto;

import lombok.Data;

@Data
public class UserIncorrectDataDto {
    private String info;

    public UserIncorrectDataDto() {
    }

    public UserIncorrectDataDto(String info) {
        this.info = info;
    }
}
