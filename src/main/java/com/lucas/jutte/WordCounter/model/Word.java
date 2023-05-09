package com.lucas.jutte.WordCounter.model;

import com.lucas.jutte.WordCounter.supplied.WordFrequency;
import lombok.Data;

@Data
public class Word implements WordFrequency {
    private final String Word;
    private final int Frequency;
}
