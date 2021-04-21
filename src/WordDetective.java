///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Word Detective
// Course:          CS 200, Spring 2020
//
// Author:          Joe Rafferty
// Email:           jrrafferty@wisc.edu
// Lecturer's Name: Jim Williams
//
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordDetective {

    /**
     * Finds the specified set of words in the specified file and returns them
     * as an ArrayList.  This finds the specified set in the file which is on the 
     * line number of the set. The first line and set in the file is 1.
     * 
     * This returns an ArrayList with the keyword first, then : and then followed 
     * by the rest of the words in the set, space delimited.
     * Note: The String class indexOf method can be used to find the :. The keyword is
     * everything prior to the : and the keywords are space delimited after. 
     * 
     * An error message to System.out should be printed and an empty list returned
     * in the following cases:
     *    A set number is not found.
     *       "Error: set " + fileSetNumber + " not found."
     *    A set doesn't have the : after the keyword.
     *       "Error: colon (:) not found in set " + fileSetNumber + "."
     * 
     * Example file format:
     * about: about auto bat boat but out tab tub 
     * other: her hero hot other the toe 
     * their: her hire hit the their tie tier tire 
     * 
     * If the set number is 2 the returned ArrayList should contain:
     * [other, her, hero, hot, other, the, toe]
     * 
     * @param filename The file containing word sets.
     * @param set  The number of the set, starting with 1.
     * @return The set of words, with the keyword first and later in the list.
     * @throws FileNotFoundException 
     */
	public static ArrayList<String> loadWordSet(String filename, int set) throws FileNotFoundException {
	    ArrayList<String> wordSet = new ArrayList<>();
	    
	    try {
	        String line;
	        
	        File file = new File(filename);
	        FileReader fileReader = new FileReader(file);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        for (int i = 0; i < set - 1; ++i) {
	            bufferedReader.readLine();
	        }
	        
	        line = bufferedReader.readLine();
            String[] words = line.split(" ");
            
            if (words[0].charAt(words[0].length() - 1) != ':') {
                System.out.println("Error: colon (:) not found in set " + set + ".");
            }
            
            else {
                wordSet.add(words[0].substring(0, words[0].length() - 1));
            
                for (int j = 1; j < words.length; ++j) {
                    wordSet.add(words[j]);
                }
            }
            
            bufferedReader.close();

        } 
	    
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	        /*System.out.println("Error: set " + set + " not found.");*/
	    }
	    
	    catch (IOException e1) {
	        e1.printStackTrace();
	    }
	    
	    return wordSet;
	}

	
    /**
     * Reorders the letters in word.  
     * 
     * Algorithm:
     * Copy the word into an array of characters.
     * Repeat 4 times:
     *     Randomly select 2 indexes in the word array using
     *         nextInt( word length) of the randGen parameter.
     *     Swap the letters at the selected indexes in the word array.
     * Create a string from the array of characters.
     * 
     * @param word The letters to be reordered.
     * @param randGen A random number generator.
     * @return The letters that have been randomly ordered.
     */
	public static String reorderLetters(String word, Random randGen) {
	    char[] letters = new char[word.length()];
	    String newLetters = "";
	    /* Copy the word into an array of characters */
        for (int i = 0; i < letters.length; ++i) {
            letters[i] = word.charAt(i);
        }
        
        /* Repeat 4 times:
         *     Randomly select 2 indexes in the word array using
         *         nextInt( word length) of the randGen parameter.
         *     Swap the letters at the selected indexes in the word array. */
        for (int i = 0; i < 4; ++i) {
            
            int index1 = randGen.nextInt(word.length());
            char letter1 = letters[index1];
            int index2 = randGen.nextInt(word.length());
            char letter2 = letters[index2];
            
            letters[index1] = letter2;
            letters[index2] = letter1;
        }
        
        for (int j = 0; j < letters.length; ++j) {
            newLetters += letters[j];
        }
        
        return newLetters;
	}	
	
    /**
     * This returns a string containing the char ch, count times.  
     * For example calling with 'a', 5 would return:
     * "aaaaa"
     * 
     * @param ch  The character to repeat.
     * @param count The number of the character.
     * @return A string with count number of ch.
     */
	public static String charToString(char ch, int count) {
	    String charLine = "";
	    for (int i = 0; i < count; ++i) {
	        charLine += ch;
	    }
        return charLine; //TODO
	}
	
    /**
     * Returns (does not print out) a string showing the words that have 
     * been guessed and dashes (-) for the letters of the words not yet guessed.
     * 
     * Example
     * wordSet: ago,  now,   own,  wagon
     * guessed: true, false, true, false
     * Returned string: "ago\n---\nown\n-----\n"
     * 
     * @param wordSet The set of words to be guessed.
     * @param guessed Which words have been guessed.
     * @return A string showing the state of the guesses.
     */
	public static String show(ArrayList<String> wordSet, boolean[] guessed) {
	    String show = "";
	    for (int i = 0; i < guessed.length; ++i) {
	        if (guessed[i] == true) {
	            show += wordSet.get(i) + "\n";
	        }
	        else {
	            for (int j = 0; j < wordSet.get(i).length(); ++ j) {
	                show += "-";
	            }
	            show += "\n";
	        }
	    }
        return show; //TODO
	}

    /**
     * Picks the first unguessed word to show.
     * Updates the guessed array indicating the selected word is shown.
     * 
     * @param wordSet  The set of words.
     * @param guessed  Whether a word has been guessed.
     * @return The word to show or null if all have been guessed.
     */
	public static String pickWordToShow(ArrayList<String> wordSet, boolean[] guessed) {
	    for (int i = 0; i < wordSet.size(); ++i) {
	        if (guessed[i] == false) {
	            guessed[i] = true;
	            return wordSet.get(i);
	        }
	        else {
	            continue;
	        }
	    }
        return null; //TODO
	}
	
    /**
     * Looks for guess in wordSet. If found then updates the guessed array at the 
     * corresponding index to true. Returns whether the guess was found or not.
     * 
     * @param wordSet  The set of words.
     * @param guessed  The boolean array parallel with wordSet indicating if a word
     *         has been guessed or not.
     * @param guess  The word to look for in wordSet.
     * @return Whether the guess was found in the wordSet.
     */
	public static boolean wordMatches(ArrayList<String> wordSet, boolean []guessed, String guess) {
	    for (int i = 0; i < wordSet.size(); ++i) {
	        if (wordSet.get(i).equals(guess)) {
	            guessed[i] = true;
	            return true;
	        }
	        else {
	            continue;
	        }
	    }
        return false; //TODO
	}
	
    /**
     * Determines if the all the elements in the guessed array are true.
     * 
     * @param guessed Which words have been guessed.
     * @return true, if all the words have been guessed, false otherwise.
     */
	public static boolean wordSetComplete(boolean []guessed) {
	    for (int i = 0; i < guessed.length; ++i) {
	        if (guessed[i] == false) {
	            return false;
	        }
	    }
        return true; //TODO
	}	
	
    /**
     * This is the main program loops, prompts for user input and prints to the 
     * console. For exact messages and output see the output examples in zyBooks.
     * 
     * Algorithm: 
     * Initialization and Welcome
     * Note: First wordSet is the 1st line in the file. The words sets are 
     * presented in the order in the file.
     * Game loop ("q" quits)
     *     Load the next word set (the first time use first set) from file "sets50.txt"
     *     If file not found quit with message "Error: unable to read from <filename>",
     *         with <filename> replaced with the filename.
     *     Remove the first word from the word set, that is the keyword.
     *     Reorder the letters of the keyword (reorderLetters)
     *     Create a boolean array, parallel to the word set, to indicate which words
     *         have been guessed and which have not.
     *     Word set loop (guessing all words or quitting exits)
     *         Print out the words that have been guessed and dashes for those that haven't (show).
     *         Prompt for input (s to show a word and q to quit), see example
     *         If "s" then pick word to show (pickWordToShow) and print "showing: " + word
     *         If "q" then quit
     *         Otherwise, check if word matches (wordMatches) or print "not in my list: " + word
     *         If all words are guessed (wordSetComplete) then print "Congratulations! ..." and
     *             increment word set, and word set loop is now complete.
     * When game loop complete (quit), then print "Thanks for playing Word Detective!" 
     *
     * @param args unused
     */
	public static void main(String[] args)  {
	    Scanner input = new Scanner(System.in);
	    Random randGen = new Random(876);
	    final String WORD_SET_FILE = "sets50.txt";

	    System.out.println("Welcome to Word Detective!");
	    System.out.println("Guess the words made from the letters.");
	    System.out.println("Options s:show, q:quit");
	    String guess = "";
	    int set = 1;
	    
	    try {
	        do {
	            ArrayList<String> wordSet = loadWordSet(WORD_SET_FILE, set);
	            String key = wordSet.remove(0);
	            key = reorderLetters(key, randGen);
	            boolean[] guessed = new boolean[wordSet.size()];
	            
	            do {
	                System.out.print(show(wordSet,guessed));
	                guess = input.nextLine().trim();
	                System.out.print(set + ") " + key + ": ");
	                
	                if (guess.equalsIgnoreCase("s") ) {
	                    System.out.println("showing: " + pickWordToShow(wordSet, guessed));
	                }
	                
	                else if (!guess.equalsIgnoreCase("q")) {
	                    if (!wordMatches(wordSet, guessed, guess)) {
	                        System.out.println("Not in my list: " + guess);
	                    }
	                
	                } 
	                    if (wordSetComplete(guessed)) {
	                        /*System.out.print(show(wordSet, guessed));*/
	                        System.out.println("Congratulations! You guessed all the words!");
	                        System.out.println("Guess the words made from the letters.");
	                        System.out.println("Options s:show, q:quit");
	                        set++;
	                        break;
	                    }
	                
	                }
	            while (!guess.equalsIgnoreCase("q"));
	            }
	        while (!guess.equalsIgnoreCase("q"));	    
	    
	    System.out.println("Thanks for playing Word Detective!");
	    input.close();
	    }
	    catch (FileNotFoundException e) {
	        System.out.println("Error: unable to read from " + WORD_SET_FILE);
	    }
	    }
}
