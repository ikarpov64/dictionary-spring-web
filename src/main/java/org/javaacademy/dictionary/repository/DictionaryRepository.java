package org.javaacademy.dictionary.repository;

import lombok.SneakyThrows;
import org.javaacademy.dictionary.entity.Word;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DictionaryRepository {
    private SortedMap<String, Word> dictMap = new TreeMap<>();

    @SneakyThrows
    public void add(Word word) {
        Thread.sleep(3000);
        dictMap.put(word.getEngWord(), word);
    }

    @SneakyThrows
    public List<Word> getAllWords() {
        Thread.sleep(3000);
        return new ArrayList<>(dictMap.values());
    }

    @SneakyThrows
    public Optional<Word> getWord(String word) {
        Thread.sleep(3000);
        return Optional.ofNullable(dictMap.get(word));
    }

    @SneakyThrows
    public void updateWord(String oldWord, Word newWord) {
        Thread.sleep(3000);
        if (!dictMap.containsKey(oldWord)) {
            throw new RuntimeException("Слово отсутствует в словаре.");
        }
        dictMap.put(oldWord, newWord);
    }

    @SneakyThrows
    public boolean deleteWord(String word) {
        Thread.sleep(3000);
        return dictMap.remove(word.toLowerCase()) != null;
    }
}
