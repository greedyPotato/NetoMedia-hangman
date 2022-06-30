package com.netomedia.exam.hangman.sorter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface ILatterCounter {

    public ArrayList<String> sortDicByLength(int length) throws FileNotFoundException;
    public ArrayList<String> sortDicByPlace(int place,char latter, ArrayList<String> dic);
    public ArrayList<String> sortDicByMissingLatter(char latter, ArrayList<String> dic);
}
