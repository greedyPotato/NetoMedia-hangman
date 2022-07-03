package com.netomedia.exam.hangman.sorter;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public interface ILatterCounter {

    public void sortDicByLength(int length) throws FileNotFoundException;
    public void sortDicByPlace(int place,char latter, ArrayList<String> dic);
    public void sortDicByMissingLatter(String latter, ArrayList<String> dic);
}
