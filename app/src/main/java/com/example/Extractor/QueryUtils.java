package com.example.Extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class QueryUtils {

    private QueryUtils() {
    }

    public static List<Words> fetchWords(String url) {
        String text = "";

        try {
            //extract text from a web page with given url
            Document doc = Jsoup.connect(url).get();
            text = doc.text();
            //text = "";
            //text = "this is just for testing' purposes, let's see what; will; happen\" when we try to run that. and i hope this will work.";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countWords(text);
    }

    private static List<Words> countWords(String text) {

        List<String> strings = new ArrayList<>();
        List<Words> words = new ArrayList<>();
        Words word;
        Map<String, Integer> mapOfWords = new HashMap<>();

        if (!text.equals("")) {
            //this just to extract all words from the text
            for (String val : text.split(" ")) {
                    if (val.contains("'") && (!val.endsWith("'") || val.startsWith("'"))) {
                        strings.add(val);
                    } else {
                        val = val.replaceAll("[^a-zA-Z0-9-]", "");
                        if (val.equals("")) continue;
                        strings.add(val);
                    }
            }

            //this to count the occurrence of each word in strings list
            for (String string : strings) {
                Integer numOfOccurrence = mapOfWords.get(string);
                mapOfWords.put(string, (numOfOccurrence == null) ? 1 : numOfOccurrence + 1);
            }

            //iterate on every element in the map and add them to the words list
            for (Map.Entry<String, Integer> val : mapOfWords.entrySet()) {
                word = new Words();
                word.setWord(val.getKey());
                word.setWordCounter(val.getValue());
                words.add(word);
                // Log.d("word in this element", val.getKey());
            }
            return words;
        } else
            return null;

    }
}
