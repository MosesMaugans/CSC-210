import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PA3Main {
    public static ArrayList<String> fileToDict(Scanner file) {
        ArrayList<String> dict = new ArrayList<String>();
        while (file.hasNextLine()) {
            dict.add(file.nextLine().trim());
        }
        return dict;
    }

    public static void printArray(ArrayList<String> dict) {
        if (dict.isEmpty()) {
        } else {
            System.out.print(dict.get(0) + ", ");
            dict.remove(0);
            printArray(dict);
        }
    }

    public static ArrayList<String> findWords(ArrayList<String> dict,
            ArrayList<String> wordlist, LetterInventory word) {
        if (dict.isEmpty()) {
            return wordlist;
        } else if (word.contains(dict.get(0))) {
            wordlist.add(dict.get(0));
            dict.remove(0);
            return findWords(dict, wordlist, word);
        } else {
            dict.remove(0);
            return findWords(dict, wordlist, word);
        }
    }

    public static ArrayList<String> findAnagrams(ArrayList<String> wordlist, ArrayList<String> anagrams, LetterInventory word){
        if (word.isEmpty()) {
            return anagrams;
        }else {
            for(int i = 0; i < wordlist.size(); i++) {
                if(word.contains(wordlist.get(i))) {
                    anagrams.add(wordlist.get(i));
                    word.subtract(wordlist.get(i));
                    return findAnagrams(wordlist, anagrams, word);
                } else {
                    return findAnagrams(wordlist, anagrams, word);
                }
            }
        }
    }

    public static void main(String[] args) {
        String filename = args[0];
        LetterInventory word = new LetterInventory(args[1]);
        int num = Integer.valueOf(args[2]);
        try {
            Scanner file = new Scanner(new File(filename));
            ArrayList<String> dict = fileToDict(file);
            file.close();
            ArrayList<String> wordlist = findWords(dict,
                    new ArrayList<String>(), word);
            ArrayList<String> anagrams = findAnagrams(wordlist, new ArrayList<String>, word);
            printArray(foundWords);

        } catch (Exception e) {
            System.out.println("No File Found");
        }
    }
}
