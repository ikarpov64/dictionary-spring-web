package org.javaacademy.dictionary.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class WordDtoRs {
    @NonNull
    private String engWord;
    @NonNull
    private String rusDescription;
}
