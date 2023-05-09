package com.lucas.jutte.WordCounter.service;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
        assertThatThrownBy(() -> wordFrequencyAnalyzerService.calculateFrequencyForWord(inputString,"Mies"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There are no words to count!");
    }

    @Test
    void calculateFrequencyForWordWordEmpty() {
        //Arrange
        String inputString = "Aap Noot Mies Mies Mies";
        val expected = 0;
        //Act
        val result = wordFrequencyAnalyzerService.calculateFrequencyForWord(inputString,"");
        //Assert
        assertThat(result).isEqualTo(expected);
    }
    //endregion

    //region calculateMostFrequentNWords
    @Test
    void calculateMostFrequentNWords() {

    }
    //endregion
}
