package com.example.textanalyzer.controller;

import com.example.textanalyzer.dto.TextAnalysisRequest;
import com.example.textanalyzer.dto.TextAnalysisResponse;
import com.example.textanalyzer.service.TextAnalysisService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/text-analysis")
public class TextAnalyserController {

    private final TextAnalysisService textAnalysisService;

    public TextAnalyserController(TextAnalysisService textAnalysisService) {
        this.textAnalysisService = textAnalysisService;
    }

    /**
     * Analyse text and gives number of vowels or consonants.
     *
     * @param request the text to analyse
     * @return the ResponseEntity with status 200 (OK) and with body of the analysed text
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<TextAnalysisResponse> analyseText(@Valid @RequestBody TextAnalysisRequest request) {
        log.info("Text analysis with type:{}", request.getType());
        return ResponseEntity.ok(textAnalysisService.analyseText(request));
    }

}
