package com.example.textanalyzer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextAnalysisRequest {

    @NotEmpty(message = "The type is required.")
    @Pattern(regexp = "VOWEL|CONSONANT", message = "Type should be VOWEL or CONSONANT")
    private String type;

    @NotEmpty(message = "The text is required.")
    private String text;

}
