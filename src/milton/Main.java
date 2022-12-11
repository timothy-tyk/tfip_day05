package milton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

  // Run: java -cp classes milton.Main paradise_lost.txt (args start counting after src.Main)
  public static void main(String[] args) {
    String fileName = args[0];

    System.out.printf("Processing %s\n",fileName);

    //Open and read the file, print out each line, close file when done
    File file = new File(fileName);

    try {
      FileReader fr = new FileReader(file);
      BufferedReader bfr = new BufferedReader(fr);
      String line;
      int counter =0;
      int wordCount =0;
      
      //Map -> Abstract Type, HashMap -> Concrete Type
      Map<String,Integer> wordMap = new HashMap<String,Integer>();
      
      while(null!=(line=bfr.readLine())){
        // Read the first 100 lines, print each line
        String[] words = line.replaceAll(",","").trim().split(" ");
        for(int i=0;i<words.length;i++){
          String currentWord = words[i].toLowerCase();
          if(wordMap.containsKey(currentWord)){
            wordMap.put(currentWord, wordMap.get(currentWord)+1);
          }
          else{
            wordMap.put(currentWord, 1);
          }
          // Get or Default method
          // Integer v = wordMap.getOrDefault(currentWord, 0);
          // v++;
          // wordMap.put(currentWord,v);
        }
        wordCount+=words.length;
        
        // System.out.printf("%d: %s\n",counter,line);
        counter++;
        if (counter==100){
          break;
        }
     
      }
      System.out.printf("The word count is %d\n",wordCount);
      System.out.println("-------------------");
      Integer uniqueWordCount =0;
      Integer highestWordCount=0;
      String mostWord="";

      // Initialise words.csv file, print to it
      File wordFile = new File(args[1]);
      FileWriter fw = new FileWriter(wordFile);
      BufferedWriter bfw = new BufferedWriter(fw);
      
      bfw.write("Word,Count\n");
      wordMap.remove("");
      for(String word:wordMap.keySet()){
        if(wordMap.get(word)>highestWordCount){
          mostWord = word;
          highestWordCount = wordMap.get(word);
        }
        uniqueWordCount++;
        bfw.write(String.format("%s, %d\r\n", word, wordMap.get(word)));
        // System.out.printf("%s: %d\n",word,wordMap.get(word));
        }
    
      System.out.println("-------------------");
      System.out.printf("The no. of unique words is: %d\n",uniqueWordCount);

      Set<String> uniqueWords = wordMap.keySet();
      System.out.printf("The highest word is %s and count is: %d\n",mostWord.toUpperCase(),highestWordCount);

      

      
      bfr.close();
      fr.close();
      bfw.flush();
      fw.flush();
      bfw.close();
      fw.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch(IOException e){
      e.printStackTrace();
    }

    
    
    


   

    //close the file when done

    //Write a program to group the books according to their publisher
    // eg Scholastic
    // book1
    // book2
    // Del Ray
    //Book 1
    // Book 2
  }
}
