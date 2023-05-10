package com.lucas.jutte.WordCounter.service;

import com.lucas.jutte.WordCounter.model.Word;
import com.lucas.jutte.WordCounter.supplied.WordFrequency;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class WordFrequencyAnalyzerServiceTest {
    private WordFrequencyAnalyzerService wordFrequencyAnalyzerService;

    @BeforeEach
    void beforeEach() {
        wordFrequencyAnalyzerService = new WordFrequencyAnalyzerService();
    }

    //region calculateHighestFrequency
    @Test
    void calculateHighestFrequency() {
        //Arrange
        val expected = 3;
        val inputString = "Aap Noot Mies Mies Mies";
        //Act
        val result = wordFrequencyAnalyzerService.calculateHighestFrequency(inputString);
        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateHighestFrequencyEmptyString() {
        //Arrange
        val inputString = "";
        //Act & Assert
        assertThatThrownBy(() -> wordFrequencyAnalyzerService.calculateHighestFrequency(inputString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are no words to count!");
    }

    @Test
    void calculateHighestFrequencyNull() {
        //Arrange
        String inputString = null;
        //Act & Assert
        assertThatThrownBy(() -> wordFrequencyAnalyzerService.calculateHighestFrequency(inputString))
                .isInstanceOf(NullPointerException.class);
    }
    //endregion

    //region calculateHighestFrequencyForWord
    @Test
    void calculateFrequencyForWord() {
        //Arrange
        val expected = 3;
        val inputString = "Aap Noot Mies Mies Mies";
        //Act
        val result = wordFrequencyAnalyzerService.calculateFrequencyForWord(inputString, "Mies");
        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateFrequencyForWordEmpty() {
        //Arrange
        val inputString = "";
        //Act & Assert
        assertThatThrownBy(() -> wordFrequencyAnalyzerService.calculateFrequencyForWord(inputString, "Mies"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are no words to count!");
    }

    @Test
    void calculateFrequencyForWordWordEmpty() {
        //Arrange
        String inputString = "Aap Noot Mies Mies Mies";
        val expected = 0;
        //Act
        val result = wordFrequencyAnalyzerService.calculateFrequencyForWord(inputString, "");
        //Assert
        assertThat(result).isEqualTo(expected);
    }
    //endregion

    //region calculateMostFrequentNWords
    @Test
    void calculateMostFrequentNWords() {
        //Arrange
        String inputString = "The sun shines over the lake";
        List<WordFrequency> expectedList = List.of(
                new Word("The".toLowerCase(), 2),
                new Word("Lake".toLowerCase(), 1),
                new Word("Over".toLowerCase(), 1));
        //Act
        val result = wordFrequencyAnalyzerService.calculateMostFrequentNWords(inputString,3);
        //Assert
        assertThat(result).containsExactlyElementsOf(expectedList);
    }
//endregion
}
