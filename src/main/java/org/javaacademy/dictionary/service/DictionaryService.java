package org.javaacademy.dictionary.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionary.dto.PageDto;
import org.javaacademy.dictionary.dto.WordDtoRq;
import org.javaacademy.dictionary.dto.WordDtoRs;
import org.javaacademy.dictionary.entity.Word;
import org.javaacademy.dictionary.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public List<WordDtoRs> getAllWords() {
        return dictionaryRepository.getAllWords().stream()
                .map(this::convertToDtoRs)
                .toList();
    }

    public WordDtoRs addWord(WordDtoRq wordDtoRq) {
        Word newWord = convertToEntity(wordDtoRq);
        dictionaryRepository.add(newWord);
        return convertToDtoRs(newWord);
    }

    public boolean deleteWord(String word) {
        return dictionaryRepository.deleteWord(word);
    }

    private Word convertToEntity(WordDtoRq wordDtoRq) {
        return new Word(wordDtoRq.getEngWord().toLowerCase(), wordDtoRq.getRusDescription());
    }

    private WordDtoRs convertToDtoRs(Word word) {
        return new WordDtoRs(word.getEngWord(), word.getRusDescription());
    }

    public PageDto<List<WordDtoRs>> getWords(Integer startWord, Integer pageSize) {
        List<Word> allWords = dictionaryRepository.getAllWords();
        List<WordDtoRs> wordDtoRs = allWords.stream()
                .skip(startWord)
                .limit(pageSize)
                .map(e -> new WordDtoRs(e.getEngWord(), e.getRusDescription()))
                .toList();
        return new PageDto<>(wordDtoRs.size(),
                allWords.size(),
                startWord,
                startWord + pageSize,
                wordDtoRs);
    }

    public WordDtoRs getWord(String word) {
        return dictionaryRepository.getWord(word.toLowerCase())
                .map(this::convertToDtoRs)
                .orElseThrow();
    }

    public void updateWord(String word, WordDtoRq wordDtoRq) {
        dictionaryRepository.updateWord(word, convertToEntity(wordDtoRq));
    }
}
