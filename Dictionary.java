import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//Ameen Popoola-Name
//20231717-ID

public class Dictionary {

    private ArrayList<String> dictionary;

    public Dictionary() {
        this.dictionary = new ArrayList<String>();
        loadWordsFromFile("BasicEnglish.txt");
    }

    public Dictionary(String filename) {
        this.dictionary = new ArrayList<String>();
        loadWordsFromFile(filename);
    }

    public void include(String newWord) {
        newWord= newWord.toLowerCase();

        int index = Collections.binarySearch(this.dictionary,newWord);
        if(index < 0) {
            index = -(index+1);
            this.dictionary.add(index,newWord);
        }
    }

    public int size(){
        return dictionary.size();
    }

    public boolean spellCheck(String aWord){
        int i = 0;
        for(i=0;i<dictionary.size();i++){
            if(aWord.equals(dictionary.get(i))){
                return true;
            } else{
                return false;
            }
        }   return true||false;
    }

    public void loadWordsFromFile(String filename ){
        String word ="";
        try {
            File file = new File(filename);
            Scanner insert = new Scanner(file);
            while(insert.hasNext()){
                word = insert.next();
                include(word);
            }
        } catch (IOException e) {
            System.out.println("Mistake");
        }
    }

    public ArrayList<String> predict(String portion){
        ArrayList<String> prediction = new ArrayList<String>();
        portion.toLowerCase();
        for(int i=0;i<dictionary.size();i++){
            String word = dictionary.get(i);
            if(word.startsWith(portion)){
                prediction.add(word);
            }
        }   return prediction;
    } 

    public void display(int wordsPerLine){
        for(int i=0;i<dictionary.size();i++){
            if(i < wordsPerLine){
                System.out.print(dictionary.get(i) + " ");
            } else if(i >= wordsPerLine){
                System.out.println(dictionary.get(i));
            }
        }
    }

    public ArrayList<String> NRandomlyChosenWords(int n){
        ArrayList<String> chosenWords = new ArrayList<String>();
        if(n <= dictionary.size()){
            for(int i=0;i<n;i++){
                int pos = (int)(Math.random()*dictionary.size());
                chosenWords.add(dictionary.get(pos));
            }
            Collections.sort(chosenWords , Collections.reverseOrder());
        } else if( n > dictionary.size()){
            for(int i=0;i < dictionary.size();i++){
                chosenWords.add(dictionary.get(i));
                Collections.sort(chosenWords,Collections.reverseOrder());
            }
        }    return chosenWords;
    }

    public ArrayList<String> randomlyChooseNWordsWithLengthsBetween(int n ,int shortest , int longest){
        ArrayList<String> wordsBetweenLength = new ArrayList<String>();
        if(n <= dictionary.size()){
            for(int i=0;i<n;i++){
                int pos = (int)(Math.random()*dictionary.size());
                if(shortest <= dictionary.get(pos).length() || dictionary.get(pos).length() <= longest ){
                    wordsBetweenLength.add(dictionary.get(pos));
                }
            }
            Collections.sort(wordsBetweenLength , Collections.reverseOrder());
        } else if(n > dictionary.size()){
            for(int i=0;i<dictionary.size();i++)
                if(shortest <= dictionary.get(i).length() || dictionary.get(i).length() <= longest){
                    wordsBetweenLength.add(dictionary.get(i));
                    Collections.sort(wordsBetweenLength , Collections.reverseOrder());
                }
        }   return wordsBetweenLength;
    }    
}
