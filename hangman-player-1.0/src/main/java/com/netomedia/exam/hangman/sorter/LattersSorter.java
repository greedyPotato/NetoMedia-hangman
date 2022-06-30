package com.netomedia.exam.hangman.sorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LattersSorter implements ILatterCounter{

    @Override
    public ArrayList<String> sortDicByLength(int length) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/main/resources/dictionary.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            if(s.next().length() == length)//not working for some reason
                list.add(s.next());
        }
        s.close();
        return list;
    }

    @Override
    public ArrayList<String> sortDicByPlace(int place, char latter, ArrayList<String> dic) {

        for(String word : dic){
            if(word.charAt(place) == latter){
                dic.remove(word);
            }
        }
        return dic;

    }

    @Override
    public ArrayList<String> sortDicByMissingLatter(char latter, ArrayList<String> dic) {

        for(String word:dic){
            if(dic.contains(latter)){
                dic.remove(word);
            }
        }
        return dic;
    }
}
