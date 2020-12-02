/*Max Dizy
Novermber 27, 2020
ICS4U
Mr. Hofstatter
wordGuessGame
Word guessing game that allows user to guess the letters of a word and guess the final secret word*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Dictionary;
public class wordGuessGame{

  //global validation VARIABLE
  public static boolean approved = false;

  //method to CHECK if name is compossed of letters
  public static boolean checkName(String name){
    boolean confirmed = false;
    boolean ifString = false;
    char[] nameArray = name.toCharArray();
    char[] alph = "abcdefghijklmnopqrstuvwrxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    for (char digit : nameArray){
      confirmed = false;
      for (char letter : alph){
        if (digit == letter){
          confirmed = true;
        }
      }
      if (confirmed == false){
        System.out.println("\nSorry I don\'t understand, Please only use letters and try again: ");
        return false;
      }
    }
    return true;
  }

  //method to CHECK if level input is correct
  public static boolean checkLevel(String level){

      if (level.equals("easy")){
        return true;
        }
      else if (level.equals("medium")){
        return true;
        }
      else if (level.equals("hard")){
        return true;
        }
      else{
        System.out.println("\nSorry I don\'t understand, Please enter one of the options below and try again: ");
        return false;
        }
  }

  //method to CHECK if answer is a letter
  public static boolean checkAnswer(String answer, String name, String word, int guesses){
    Scanner scan = new Scanner(System.in);
    boolean confirmed = false;
    char[] answerArray = answer.toCharArray();
    char[] alph = "abcdefghijklmnopqrstuvwrxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    if (answer.length() == 1){
      for (char digit : answerArray){
        for (char letter : alph){
          if (digit == letter){
            confirmed = true;
            }
          }
        if (confirmed == false){
          System.out.println("\nSorry I don\'t understand, Please enter a letter and try again: ");
          return false;
          }
        }
        return true;
      }
    else if (answer.equals("word")){
      System.out.print("\nOkay " + name + ", please enter a word: ");
      String wordGuess = scan.next().toLowerCase();

      if (wordGuess.equals(word)){
        winGame(guesses, name, word);
      }
      else{
        System.out.print("\nSorry that is incorrect");
        return false;
      }
    }

    else{
      System.out.println("\nSorry I don\'t understand, Please enter a letter and try again: ");
      return false;
      }
      return false;
    }

  //method to PRINT output array
  public static char printOutputArray(char[] outputArray){
    for (char letter : outputArray){
      System.out.print(letter);
    }
    return ' ';
  }

  //method to PRINT guessed characters
  public static String printGuessed(String[] guessed){
    for (String guess : guessed){
      if (guess!=null){
        System.out.print(" " + guess);
      }
    }
    return "";
  }

  //method to CREATE new word with guess
  public static char[] newWord(int index, char[] outputArray, String guess){
    char guessChar = guess.charAt(0);
    outputArray[index] = (guessChar);
    return outputArray;
  }

  //method to CHECK if guess is in word
  public static boolean isGuessIn(String guess, String word){
    int guessInx = word.indexOf(guess);
    if (guessInx != -1){
      return true;
    }else{
      return false;
    }
  }

  //method to WIN GAME
  public static void winGame(int guesses, String name, String word){
    System.out.println("\nCONGRATULATIONS " + name.toUpperCase() + " YOU WIN");
    System.out.println("\nMy word was " + word);
    System.out.println("\nYou guessed my word in " + guesses + " guesses!");
    System.exit(0);
  }

  //MAIN method
  public static void main(String[] args){

    //define VARIABLES
    boolean validation;
    int correct = 0;
    int guesses = 1;
    int instance = 0;
    String guess = "";
    String word = "default";
    String updateWord = "default";

    //get word
    Random rand = new Random();
    int wordIndex = rand.nextInt(1000);
    Path listPath = Paths.get("D:\\Max Dizy\\Code\\Word Guess Game\\wordList.txt");
    Charset charset = StandardCharsets.UTF_8;
    try{
      List <String> wordList = Files.readAllLines(listPath, charset);
      word = wordList.get(wordIndex);
      updateWord = word;
    }
    catch(IOException ex){
      System.out.format("I/O Exception", ex);
    }

    char[] wordArray = word.toCharArray();
    char[] outputArray = "_ ".repeat(word.length()).toCharArray();
    Scanner scan = new Scanner(System.in);

    //create WORD DICTIONARY
    Hashtable<Integer, String> wordDict = new Hashtable<Integer, String>();

    //set values to dictionary
    int index = 0;
    for (char letter : wordArray){
      wordDict.put(index, String.valueOf(letter));
      index ++;
    }

    //WELCOME
    System.out.println("Welcome to the Word Guessing Game!");
    System.out.println("I will think of a word and you have to guess the letters to find out the word i'm thinking of... are you ready?");

    //get user NAME
    approved = false;
    String name = "";
    while (!approved){
      System.out.print("\n" + "Please enter you name to begin: ");
      name = scan.nextLine();
      approved = checkName(name);

    //get prefered LEVEL
    approved = false;
    String level = "";
    while (!approved){
      System.out.print("\n" + "Please choose a difficulty level \'easy\', \'medium\' or \'hard\': ");
      level = scan.nextLine();
      approved = checkLevel(level);
    }
    //define number of allowed guess based on LEVEL
    String[] guessed = {"nothing"};
    int guessesAvail = 0;
    int guessedIndex = 0;
    if (level.equals("easy")){
      guessesAvail = 30 + 1;
      guessed = new String[guessesAvail];
    }
    else if (level.equals("medium")){
      guessesAvail = 20 + 1;
      guessed = new String[guessesAvail];
    }
    else if (level.equals("hard")){
      guessesAvail = 10 + 1;
      guessed = new String[guessesAvail];
    }

    //START
    System.out.println("\n" + "Okay " + name + ", I have my word");

    approved = false;
    //GAME loop
    while(!approved){
      validation = false;
      //VALIDATION loop
      while (!validation){
        System.out.println("\nMy word is: ");
        printOutputArray(outputArray);
        System.out.print("\n\n" + "You have guessed ");
        printGuessed(guessed);
        System.out.print("\nGuess a letter (or write \'word\' to guess the entire word): ");
        guess = scan.next().toLowerCase();
        validation = checkAnswer(guess, name, word, guesses);
        if (validation){
          guessed[guessedIndex] = guess;
          guessedIndex++;

        }
      }

      //if the guess is in the word
      instance = 0;
      while (isGuessIn(guess, updateWord)){
        instance++;
        newWord((updateWord.indexOf(guess) * 2), outputArray, guess);
        updateWord = updateWord.replaceFirst(guess, " ");
        correct++;
        if (correct == word.length()){
          winGame(guesses, name, word);
          }
        }
      if (instance > 0){
        System.out.println("\nCONGRATS \'" + guess + "\' is in my word");
        System.out.println("\nYou have " + (guessesAvail - guesses - 1) + " guesses left");
      }

      if (instance == 0){
        System.out.println("\nSorry \'" + guess + "\' is not in my word");
        System.out.println("\nYou have " + (guessesAvail - guesses - 1) + " guesses left");
        }

      if ((guessesAvail - guesses) -1  == 0){
        System.out.println("\nOops!! Your out of guesses... Better luck next time!!");
        System.out.println("My word was " + word);
        System.exit(0);
        }

      guesses++;
      }
    }
  }
}
