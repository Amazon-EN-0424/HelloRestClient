package com.scorsaro.hellorc.controller;

import com.scorsaro.hellorc.model.WordDefinition;
import com.scorsaro.hellorc.service.DictionaryService;
import com.scorsaro.hellorc.exception.DictionaryApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/define/{word}")
    public ResponseEntity<List<WordDefinition>> getDefinition(@PathVariable String word) {
        List<WordDefinition> definitions = dictionaryService.getWordDefinition(word);
        return ResponseEntity.ok(definitions);
    }

    @ExceptionHandler(DictionaryApiException.class)
    public ResponseEntity<String> handleDictionaryApiException(DictionaryApiException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    @GetMapping("/status")
    public ResponseEntity<String> getApiStatus() {
        String status = dictionaryService.getApiStatus();
        return ResponseEntity.ok("Dictionary API Status: " + status);
    }
}