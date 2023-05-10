package com.lucas.jutte.WordCounter.controller;

import com.lucas.jutte.WordCounter.service.WordFrequencyAnalyzerService;
import com.lucas.jutte.WordCounter.supplied.WordFrequency;
import com.lucas.jutte.WordCounter.supplied.WordFrequencyAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/word-counter")
public class WordFrequencyAnalyzerController {
    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Autowired
    public WordFrequencyAnalyzerController(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }

    @GetMapping("/highest-frequency")
    public ResponseEntity<String> calculateHighestFrequency(@RequestParam("text") String text) {
        try {
            int highestFrequency = wordFrequencyAnalyzer.calculateHighestFrequency(text);
            return ResponseEntity.ok(String.valueOf(highestFrequency));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/frequency-for-word")
    public ResponseEntity<String> calculateFrequencyForWord(@RequestParam("text") String text,
                                                            @RequestParam("word") String word) {
        try {
            int frequencyForWord = wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
            return ResponseEntity.ok(String.valueOf(frequencyForWord));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/most-frequent-words")
    public ResponseEntity<List<WordFrequency>> calculateMostFrequentNWords(@RequestParam("text") String text, @RequestParam("n") int n) {
        try {
            List<WordFrequency> wordFrequencies = wordFrequencyAnalyzer.calculateMostFrequentNWords(text, n);
            return ResponseEntity.ok(wordFrequencies);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
