///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Test Word Detective
// Course:          CS 200, Spring 2020
//
// Author:          Joe Rafferty
// Email:           jrrafferty@wisc.edu
// Lecturer's Name: Jim Williams
//

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is a testbench for the WordDetective program.
 *  
 * @author Jim Williams
 * TODO add your name when you add test cases.
 *
 */
public class TestWordDetective {

    /**
     * This contains the calls to the various test methods. 
     * Uncomment the method calls and run to execute the test cases.
     * 
     * These test cases are not intended to be comprehensive but
     * provide some examples.  Extend with your own test cases to be
     * comprehensive.
     * 
     * @param args unused
     */
	public static void main(String[] args) {
		//Part 1: Load Word Set
		testLoadWordSet();
		testReorderLetters();
		
		//Part 2: Supporting Methods
	    testCharToString();
	    testPickWordToShow();
	    testWordMatches();
	    testShow();
	    testWordSetComplete();	
		
	    //Part 3: Word Detective main
	    //implement main, test with various output examples

	}
	
	/**
	 * Some test cases for the loadWordSet method.
	 */
    private static void testLoadWordSet() {
        boolean error = false;

        { //load a specific word set
            ArrayList<String> wordSet;
            try {
                wordSet = WordDetective.loadWordSet("sets50.txt", 2);
                if ( !wordSet.toString().equals(
                    "[other, her, hero, hot, other, the, toe]"
                    )) {
                    error = true;
                    System.out.println("testLoadWordSet 1: contents: " + wordSet.toString());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        { //load a specific non-existing word set
            ArrayList<String> wordSet;
            try {
                wordSet = WordDetective.loadWordSet("sets50.txt", 2000);
                if ( wordSet.size() != 0) {
                    error = true;
                    System.out.println("testLoadWordSet 2: unexpected word set: " + wordSet.toString());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        { //try to load from a file that doesn't exist.
            //try
                //call the loadWordSet method passing the name of a non-existing file.
                //if execution reaches here that is an error.
            //catch the expected exception
                //since it was expected for this test case, its actually success.
        }

        if ( error) {
            System.out.println("testLoadWordSet failed.");
        } else {
            System.out.println("testLoadWordSet passed.");
        }
    }
    
    /**
     * Some test cases for the reorderLetters method.
     */
    private static void testReorderLetters() {
        boolean error = false;

        { //reordered word
            Random randGen = new Random( 345);
            String expected = "thero";
            String actual = WordDetective.reorderLetters("other", randGen);
            if (!actual.equals(expected)) {
                error = true;
                System.out.println("testReorderLetters 1: actual: " + actual + " expected: " + expected);
            }
        }
        
        { //reordered word
            Random randGen = new Random( 765);
            String expected = "hoter";
            String actual = WordDetective.reorderLetters("other", randGen);
            if (!actual.equals(expected)) {
                error = true;
                System.out.println("testReorderLetters 2: actual: " + actual + " expected: " + expected);
            }
        }

        if ( error) {
            System.out.println("testReorderLetters failed.");
        } else {
            System.out.println("testReorderLetters passed.");
        }
    }
    
    /**
     * Some test cases for the charToString method.
     */
    private static void testCharToString() {
        boolean error = false;

        { //string with 5 '-'
            String expected = "-----";
            String actual = WordDetective.charToString('-', 5); 
            if (!actual.equals(expected)) {
                error = true;
                System.out.println("testCharToString 1: actual: " + actual + " expected: " + expected);
            }
        }
        
        //suggest adding an additional test cases.

        if ( error) {
            System.out.println("testCharToString failed.");
        } else {
            System.out.println("testCharToString passed.");
        }
    }
    
    /**
     * Some test cases for the show method.
     */
    private static void testShow() {
        boolean error = false;

        { //
            ArrayList<String> wordSet = new ArrayList<>();
            wordSet.add("ago");
            wordSet.add("now");
            wordSet.add("own");
            wordSet.add("wagon");
            boolean []guessed = {false,true,false,false};
            String expected = "---\nnow\n---\n-----\n";
            String actual = WordDetective.show(wordSet,guessed); 
            if (!actual.equals(expected)) {
                error = true;
                System.out.println("testShow 1: actual: " + actual + " expected: " + expected);
            }
        }
        
        //suggest adding an additional test cases.

        if ( error) {
            System.out.println("testShow failed.");
        } else {
            System.out.println("testShow passed.");
        }
    }
    
    /**
     * Some test cases for the pickWordToShow method.
     */
    private static void testPickWordToShow() {
        boolean error = false;

        { //
            ArrayList<String> wordSet = new ArrayList<>();
            wordSet.add("ago");
            wordSet.add("now");
            wordSet.add("own");
            wordSet.add("wagon");
            boolean []guessed = {true,false,true,false};
            
            String expected = "now";
            String actual = WordDetective.pickWordToShow(wordSet, guessed); 
            if (!actual.equals(expected) || !guessed[1]) {
                error = true;
                System.out.println("testPickWordToShow 1: actual: " + actual + " expected: " + expected);
            }
        }
        
        //suggest adding additional test cases.

        if ( error) {
            System.out.println("testPickWordToShow failed.");
        } else {
            System.out.println("testPickWordToShow passed.");
        }
    }
    
    /**
     * Some test cases for the wordMatches method.
     */
    private static void testWordMatches() {
        boolean error = false;

        { //test if own is found
            ArrayList<String> wordSet = new ArrayList<>();
            wordSet.add("ago");
            wordSet.add("now");
            wordSet.add("own");
            wordSet.add("wagon");
            boolean []guessed = {false,false,false,false};
            
            boolean expected = true;
            boolean actual = WordDetective.wordMatches(wordSet, guessed, "own"); 
            if ( actual != expected) {
                error = true;
                System.out.println("testWordMatches 1: actual: " + actual + " expected: " + expected);
            }
        }

        //suggest adding additional test case, for example if a word is not found.
        
        if ( error) {
            System.out.println("testWordMatches failed.");
        } else {
            System.out.println("testWordMatches passed.");
        }
    }
    
    /**
     * Some test cases for the wordSetComplete method.
     */
    private static void testWordSetComplete() {
        boolean error = false;

        { 
            boolean []guessed = {false,false,false,false};
            
            boolean expected = false;
            boolean actual = WordDetective.wordSetComplete( guessed); 
            if ( actual != expected) {
                error = true;
                System.out.println("testWordSetComplete 1: actual: " + actual + " expected: " + expected);
            }
        }
        
       //suggest adding additional test such as when all words are guessed.
        
        if ( error) {
            System.out.println("testWordSetComplete failed.");
        } else {
            System.out.println("testWordSetComplete passed.");
        }
    }
}
