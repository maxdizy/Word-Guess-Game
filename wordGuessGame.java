/*Max Dizy
Novermber 27, 2020
ICS4U
Mr. Hofstatter
wordGuessingGame
Word guessing game that allows user to guess the letters of a word and guess the final secret word*/

import java.util.Scanner;
public class wordGuessGame{

  //global validation VARIABLE
  public static boolean approved = false;

  //method to check if name is compossed of letters
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
        System.out.println("Sorry I don\'t understand, Please only use letters and try again: ");
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args){

    //define scanner VARIABLE
    Scanner scan = new Scanner(System.in);

    //WELCOME
    System.out.println("Welcome to the Word Guessing Game!");
    System.out.println("I will think of a word and you have to guess the letters to find out the word i'm thinking of...");
    System.out.println("are you ready?");

    //get user NAME
    approved = false;
    String name = "";
    while (!approved){
      System.out.print("\n" + "Please enter you name to begin: ");
      name = scan.nextLine();
      approved = checkName(name);
    }


  }
}
