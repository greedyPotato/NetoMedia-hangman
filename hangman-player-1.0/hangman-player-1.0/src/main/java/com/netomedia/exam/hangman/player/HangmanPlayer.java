package com.netomedia.exam.hangman.player;

import com.netomedia.exam.hangman.model.ServerResponse;
import com.netomedia.exam.hangman.server.HangmanServer;
import com.netomedia.exam.hangman.sorter.LattersSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HangmanPlayer {

    private static HangmanServer server = new HangmanServer();
    private static HashMap<Character, Integer> charCountMap
            = new HashMap<Character, Integer>();

    public static void main(String[] args) throws Exception {
        ArrayList<Character> usedLatters = new ArrayList<>();
        char bestLatterToUse = '!';
        int max = 0;
        int place = 0;
        String preToken = "";

        LattersSorter dicSort = new LattersSorter();

        ServerResponse play = server.startNewGame();
        preToken = play.getToken();

            //sort dic with the word length
            dicSort.sortDicByLength(play.getHangman().length());

            while(!play.isGameEnded()){

            //count lattes in new dic
            for (String words : dicSort.getWords()) {
                characterCount(words, charCountMap, usedLatters);
            }
            //find best latter
            for (Map.Entry entry : charCountMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                if((int)entry.getValue() > max){
                    max = (int)entry.getValue();
                    bestLatterToUse = (char)entry.getKey();
                }
            }

            //ipus hashmap
                //guess with best latter according to new dic
                ServerResponse guess = server.guess(preToken,Character.toString(bestLatterToUse));
                preToken = guess.getToken()
                charCountMap.clear();
                usedLatters.add(bestLatterToUse);
                max=0;




            //success- sort dic with new latter and new position
                if(server.guess(preToken,Character.toString(bestLatterToUse)).isCorrect()){

                   for(int i = 0; i< server.guess(preToken,Character.toString(bestLatterToUse)).getHangman().length(); i++){
                       if( server.guess(preToken,Character.toString(bestLatterToUse)).getHangman().charAt(i) == bestLatterToUse){
                           place = i;
                           break;
                       }

                   }

                    dicSort.sortDicByPlace(place,bestLatterToUse, dicSort.getWords());
                    for (String w : dicSort.getWords()){
                        System.out.println(w);
                    }


                }else
                     //failure - sort dic without words with that latter
                     dicSort.sortDicByMissingLatter(Character.toString(bestLatterToUse),dicSort.getWords());

                preToken = server.guess(preToken,Character.toString(bestLatterToUse)).getToken();
              }


        }
    static void characterCount(String inputString, HashMap<Character, Integer> charCountMap, ArrayList<Character> usedLatters) {

        // Converting given string to char array

        char[] strArray = inputString.toCharArray();

        // checking each char of strArray
        for (char c : strArray) {
            if(!usedLatters.contains(c)) {
                if (charCountMap.containsKey(c)) {

                    // If char is present in charCountMap,
                    // incrementing it's count by 1
                    charCountMap.put(c, charCountMap.get(c) + 1);
                } else {

                    // If char is not present in charCountMap,
                    // putting this char to charCountMap with 1 as it's value
                    charCountMap.put(c, 1);
                }
            }
        }

        // Printing the charCountMap
        //for (Map.Entry entry : charCountMap.entrySet()) {
        //  System.out.println(entry.getKey() + " " + entry.getValue());
        // }
    }
    }

