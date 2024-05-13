package com.example.textanalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
@Data
@AllArgsConstructor
public class TextAnalysisResponse {
    private String type;
    private Map<String, Integer> letterCounts;

}
