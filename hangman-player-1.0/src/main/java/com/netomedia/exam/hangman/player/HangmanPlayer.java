package com.netomedia.exam.hangman.player;

import com.netomedia.exam.hangman.model.ServerResponse;
import com.netomedia.exam.hangman.server.HangmanServer;
import com.netomedia.exam.hangman.sorter.LattersSorter;


import java.util.ArrayList;

import java.util.LinkedHashMap;

public class HangmanPlayer {

    private static HangmanServer server = new HangmanServer();

    /**
     * the idea is to sort the dictionary by the size of the word
     * then checking the most common latter in the dic
     * try to guess that latter
     * if correct: sort the dic by the charAt of the latter in the word
     * if not: remove the words that has that latter
     *
     */
    public static void main(String[] args) throws Exception {
        LattersSorter lc = new LattersSorter();
        ArrayList<String> words;
        int max = 0;
        char maxLatteer;
        LinkedHashMap<Character,Integer> lattersCounter = new LinkedHashMap<>();


       ServerResponse play =  server.startNewGame();



        //transfer words to list by game length
        words = lc.sortDicByLength(play.getHangman().length());

        //mapping latters with number of shows in sorted dic
        //create latters hash map
        for(String word : words){
            int index = 0;
            while(index < word.length()){
                if(!lattersCounter.containsKey(word.charAt(index))){
                    lattersCounter.put(word.charAt(index),1);
                }else
                    lattersCounter.put(word.charAt(index), lattersCounter.get(word.charAt(index))+1);
                index++;
            }
        }

        while(!play.isGameEnded()){
            ServerResponse se =server.guess("most common latter in dic");
            if(se.isGameEnded())
                break;

            if(se.getCorrect()){

                lc.sortDicByPlace("sort with correct position")//and if its couple of places we loop it
            }else{
                lc.sortDicByMissingLatter("latter we tried and failed with")
            }


        }


    }
}
