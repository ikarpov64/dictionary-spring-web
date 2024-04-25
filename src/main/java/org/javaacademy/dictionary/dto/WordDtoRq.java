package org.javaacademy.dictionary.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class WordDtoRq {
    @NonNull
    private String engWord;
    @NonNull
    private String rusDescription;
}
