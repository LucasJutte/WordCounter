package com.lucas.jutte.WordCounter.service;

import com.lucas.jutte.WordCounter.supplied.WordFrequency;
import com.lucas.jutte.WordCounter.supplied.WordFrequencyAnalyzer;
import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WordFrequencyAnalyzerService implements WordFrequencyAnalyzer {

    @Override
    public int calculateHighestFrequency(String text) {
        val frequencyMap = convertToFrequencyMap(text);
        val entryWithHighestFrequency = Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue());
        return entryWithHighestFrequency.getValue();
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        if(text.isEmpty()){
            throw new IllegalArgumentException("There are no words to count!");
        }
        Map<String, Integer> frequencyMap = convertToFrequencyMap(text);
        return frequencyMap.getOrDefault(word.toLowerCase(), 0);
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        return null;
    }

    private Map<String, Integer> convertToFrequencyMap(@NonNull final String text) {
        if(text.isEmpty()){
            throw new IllegalArgumentException("There are no words to count!");
        }
        val words = text.toLowerCase().split("\\W+");
        return Arrays.stream(words)
                .collect(Collectors.toMap(
                        word -> word, //Key
                        word -> 1, //Initial value
                        Integer::sum //Operation in case of duplicate
                ));
    }

}
