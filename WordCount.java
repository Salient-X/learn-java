import java.util.TreeMap;
import java.util.Set;
import java.security.Key;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;


import jdk.nashorn.api.tree.StatementTree;

public class WordCount {
    public static void main(String[] args) {
        List<String> arr = new ArrayList<String>();
        arr.add("yo");
        arr.add("yo");
        arr.add("nah");
        arr.add("bruh");
        arr.add("chill");
        arr.add("dog");
        arr.add("cat");
        arr.add("chill");
        arr.add("yo");
        arr.add("bruh");
        arr.add("chill");
        arr.add("dog");
        arr.add("nah");
        arr.add("lmao");

        WordCount wc = new WordCount();

        wc.countWords(arr);


    }

    public void countWords(List<String> arr) { 
        
        //Initialize a map for first use
        TreeMap<String, Integer> tmap = new TreeMap<>(); 
        
        //Initialize a map for second use
        TreeMap<Integer, List<String>> rmap = new TreeMap<>();
        //Loop through given array
        for (int i = 0; i < arr.size(); i++) {
            // If the map already has that word stored, add one to the value.
            if (tmap.containsKey(arr.get(i))) {
                int val = (int)tmap.get(arr.get(i));
                tmap.replace(arr.get(i), val+1);
            // If the word is new, create a new key and put the value as one to say, "The word ____ has shown up once."
            } else {
                tmap.put(arr.get(i), 1);
            }
        }

        // Using the created map, above, loop through
        for (Map.Entry<String, Integer> entry : tmap.entrySet()) {
            //  
            if (rmap.containsKey(entry.getValue())) {
                rmap.get(entry.getValue()).add(entry.getKey());
            } else {
                List<String> v = new ArrayList<>();
                v.add(entry.getKey());
                rmap.put(entry.getValue(), v); 
            }
        }

        for  (Map.Entry<Integer, List<String>> entry : rmap.descendingMap().entrySet()) {
            Integer v = entry.getKey();
            for (String s : entry.getValue()) {
                System.out.println(v + " : " + s);
            }
        }
        
    }
}