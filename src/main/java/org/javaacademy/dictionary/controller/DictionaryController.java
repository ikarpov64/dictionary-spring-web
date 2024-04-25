package org.javaacademy.dictionary.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionary.dto.PageDto;
import org.javaacademy.dictionary.dto.WordDtoRq;
import org.javaacademy.dictionary.dto.WordDtoRs;
import org.javaacademy.dictionary.service.DictionaryService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/dict")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "getWords")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @GetMapping
    public List<WordDtoRs> getAllWords() {
        return dictionaryService.getAllWords();
    }

    @GetMapping("{word}")
    WordDtoRs getWord(@PathVariable String word) {
        return dictionaryService.getWord(word);
    }

    @PostMapping
    public ResponseEntity<WordDtoRs> addWord(@RequestBody WordDtoRq body) {
        return ResponseEntity.status(CREATED).body(dictionaryService.addWord(body));
    }

    @PutMapping("/{word}")
    public ResponseEntity<?> updateWord(@PathVariable String word, @RequestBody WordDtoRq wordDtoRq) {
        dictionaryService.updateWord(word, wordDtoRq);
        return ResponseEntity.status(ACCEPTED).build();
    }

    @DeleteMapping("/{word}")
    public ResponseEntity<?> deleteWord(@PathVariable String word) {
        boolean result = dictionaryService.deleteWord(word);
        return result
                ? ResponseEntity.status(ACCEPTED).build()
                : ResponseEntity.status(NOT_FOUND).build();
    }

    @GetMapping("/words")
    @Cacheable(cacheNames = "getWords")
    public PageDto<List<WordDtoRs>> getWords(@RequestParam Integer startWord,
                                             @RequestParam Integer pageSize) {
        return dictionaryService.getWords(startWord, pageSize);
    }
}
