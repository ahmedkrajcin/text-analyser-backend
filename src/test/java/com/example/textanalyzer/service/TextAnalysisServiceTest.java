package com.example.textanalyzer.service;

import com.example.textanalyzer.dto.TextAnalysisRequest;
import com.example.textanalyzer.dto.TextAnalysisResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TextAnalysisServiceTest {

    private final TextAnalysisService textAnalysisService;

    @Autowired
    TextAnalysisServiceTest(TextAnalysisService textAnalysisService) {
        this.textAnalysisService = textAnalysisService;
    }

    @Test
    void analyseTextWithVowels() {
        TextAnalysisRequest request = new TextAnalysisRequest("VOWEL", "Hello World");
        TextAnalysisResponse response = textAnalysisService.analyseText(request);
        assertNotNull(response);
        assertEquals("VOWEL", response.getType());
        assertTrue(response.getLetterCounts().containsKey("E"));
        assertEquals(1, response.getLetterCounts().get("E"));
    }

    @Test
    void analyseTextWithConsonants() {
        TextAnalysisRequest request = new TextAnalysisRequest("CONSONANT", "Hello World");
        TextAnalysisResponse response = textAnalysisService.analyseText(request);
        assertNotNull(response);
        assertEquals("CONSONANT", response.getType());
        assertTrue(response.getLetterCounts().containsKey("H"));
        assertEquals(1, response.getLetterCounts().get("H"));
        assertTrue(response.getLetterCounts().containsKey("L"));
        assertEquals(3, response.getLetterCounts().get("L"));
        assertTrue(response.getLetterCounts().containsKey("W"));
        assertEquals(1, response.getLetterCounts().get("W"));
        assertTrue(response.getLetterCounts().containsKey("R"));
        assertEquals(1, response.getLetterCounts().get("R"));
        assertTrue(response.getLetterCounts().containsKey("D"));
        assertEquals(1, response.getLetterCounts().get("D"));
    }
    @Test
    void analyseTextWithNoLetters() {
        TextAnalysisRequest request = new TextAnalysisRequest("CONSONANT", "1234567890");
        TextAnalysisResponse response = textAnalysisService.analyseText(request);
        assertNotNull(response);
        assertEquals(0,response.getLetterCounts().size());
    }

    @Test
    void analyseTextWithEmptyInput() {
        TextAnalysisRequest request = new TextAnalysisRequest("VOWEL", "  ");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            textAnalysisService.analyseText(request);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("No letters found in the text", exception.getReason());
    }


}