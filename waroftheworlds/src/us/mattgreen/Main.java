package us.mattgreen;

//import statements
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Main {
    
    //Import book & create new hash map
    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<String, Integer>();
    
    //Start new instance of main
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String line;
        String[] words;
        while ((line = indata.fileReadLine()) != null) {
            //Remove anything that's not a letter or space
            line = line.replaceAll("\\--", " ");
            line = line.replaceAll("\\-", " ");
            line = line.replaceAll("[^a-zA-Z ]","")
                    .toLowerCase().trim();
           
            //Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }
            
            //Split string over one or more spaces
            words = line.split(" +");
            
            //Look for each word in the map
            for (String word : words) {
                //This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                }
                else {
                    //Increment count using word as key
                    map.put(word, map.get(word) + 1);
                }

            } 
        }
        
        System.out.println("\"War Of The Worlds\" book reader\n-------------------------------\n"
                + "First Displayed: Top ten most-used words in the book\n"
                + "Second Displayed: Words only used once in the book\n");
        //New map for most-used words
            Map<String, Integer> newMap = sortByValue(map); 
            int a = 0;
           
            //Only display 10 items of most-used words
                for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
                    if(a < 10){
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    }
                    a++;
                }
                System.out.println("\n");
            
          
                int count = 0;
                count = newMap.entrySet().stream().filter((entry) -> (entry.getValue() == 1)).map((entry) -> {
                    System.out.println(entry.getKey());
            return entry;
            
        //New class instance of FunctionImp1 **(see lines 100-111) to find words only used once
        }).map(new FunctionImpl()).reduce(count, Integer::sum);
            System.out.println("\nThere are " + count + " words that are used only once.\n");
            System.out.println("\n Scroll to top of output to view other part\n");
        } 

   
    //Sort hash map by value
    private Map<String, Integer> sortByValue(Map<String, Integer> map) {
          
        //Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList< >(map.entrySet()); 
  
        //Sort the list 
        Collections.sort(list, (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> (o2.getValue()).compareTo(o1.getValue())); 
          
        //Put data from sorted list to hashmap  
        HashMap<String, Integer> secondary = new LinkedHashMap<>(); 
        list.forEach((aa) -> {
            secondary.put(aa.getKey(), aa.getValue());
        }); 
        return secondary; 
    }

    //Create new class which finds words only used once in book **
    private static class FunctionImpl implements Function<Map.Entry<String, Integer>, Integer> {

        public FunctionImpl() {
        }

        @Override
        public Integer apply(Map.Entry<String, Integer> _item) {
            return 1;
        }
    }
}
