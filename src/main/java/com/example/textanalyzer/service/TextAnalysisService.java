package com.example.textanalyzer.service;

import com.example.textanalyzer.dto.TextAnalysisRequest;
import com.example.textanalyzer.dto.TextAnalysisResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@Service
public class TextAnalysisService {

    private static final String vowelsRegExp = "[aeiou]";
    private static final String letterRegex = "[a-zA-Z]";
    private static final String nonVowelRegex = "[^aeiou]";


    public TextAnalysisResponse analyseText(TextAnalysisRequest request) {
        String textInput = request.getText();

        if(textInput.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No letters found in the text");
        }

        Map<String, Integer> letterCounts;

        if (Objects.equals(request.getType(), "VOWEL")) {
            letterCounts = countLetters(request.getText(), vowelsRegExp);
        } else {
            Pattern letterPattern = Pattern.compile(letterRegex);
            Matcher letterMatcher = letterPattern.matcher(textInput);
            StringBuilder onlyLettersBuilder = new StringBuilder();
            while (letterMatcher.find()) {
                onlyLettersBuilder.append(letterMatcher.group());
            }
            letterCounts = countLetters(onlyLettersBuilder.toString(), nonVowelRegex);
        }

            log.info("Data analysis finished with :{} results",letterCounts.size());
            return new TextAnalysisResponse(request.getType(), letterCounts);
    }

    private Map<String, Integer> countLetters(String text, String regex) {
        Map<String, Integer> letterCounts = new HashMap<>();
        Pattern vowelsPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher vowelsMatcher = vowelsPattern.matcher(text);
        while (vowelsMatcher.find()) {
            String vowel = vowelsMatcher.group().toUpperCase();
            letterCounts.put(vowel, letterCounts.getOrDefault(vowel, 0) + 1);
        }
        return letterCounts;
    }

}
