package com.netomedia.exam.hangman.sorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LattersSorter implements ILatterCounter{
    private ArrayList<String> words;

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    @Override
    public void sortDicByLength(int length) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/main/resources/dictionary.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
                list.add(s.next());
        }
        List<String> result = list.stream().filter(word -> word.length() == length)
                .collect(Collectors.toList());
        s.close();
        this.words = (ArrayList<String>) result;
    }

    @Override
    public void sortDicByPlace(int place, char latter, ArrayList<String> dic) {

        List<String> result = dic.stream().filter(word -> word.indexOf(place) == latter)
                .collect(Collectors.toList());
        this.words = (ArrayList<String>) result;

    }

    @Override
    public void sortDicByMissingLatter(String latter, ArrayList<String> dic) {


        List<String> result = dic.stream().filter(word -> !word.contains(latter))
                .collect(Collectors.toList());
        this.words = (ArrayList<String>) result;
    }
}
