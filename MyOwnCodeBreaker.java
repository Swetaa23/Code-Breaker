package icsclass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class MyOwnCodeBreaker {

	static Scanner console = new Scanner(System.in);
	public static void main(String[] args) {
		final String VALID_CHARS = "GRBYOP";
		final int SIZE = 4;
		final int TRIES = 10;
		char [][] clues2 = {{'b','w'},{'b','b'}};
    	char[][] guesses2 = {{'G','B','Y','Y'},{'R','B','Y','Y'}};
    	System.out.println(displayGame(guesses2, clues2));
		char[] code = new char[SIZE];
		code = createCode(VALID_CHARS, SIZE);
		char[] guess = new char[SIZE];
		char[] hintLetters = new char[SIZE];
		//hintLetters = findFullyCorrect(guess, code);
		/*
		
		
		*/
		char[][] guessDisplay = new char[TRIES][SIZE]; // try 1  M K E G (loop 4 times for size)
		char[][] hints = new char[TRIES][SIZE];
		
		int tryCount = 0;
		
		while(tryCount != TRIES){ /*UTILIZE THE ROW PART OF GUESS MORE */
			
			System.out.println("Enter your guess that contains " + SIZE + " letter characters using the given letters " + VALID_CHARS + ".");
			String guessStr = console.nextLine();
			System.out.println("GUESS: " + guessStr);
			
			if(guessStr.length() == SIZE){  //for if the guess is fully correct
				for(int i = 0; i < SIZE; i++){
					guess[i] = guessStr.charAt(i);
					//System.out.print("Guess: " + new String(guess[tryCount]));
					
					//guessDisplay[tryCount][SIZE-1] = 'b';
					//System.out.println(guessDisplay);
					
					//System.out.println(); 
				}
				
				if((valid(guess, VALID_CHARS, SIZE)) == true){
					for(int i = 0; i < SIZE; i++) {
						System.out.println("guess " + guess[i]);
						
					}
					 System.out.println("they used the correct characters (GRBYOP), \nNOT IN CORRECT POS AND/OR THE CORRECT CHARACTERS FROM CODE");
					 hintLetters = findFullyCorrect(guess, code);
					 System.out.println("hintLetters " + Arrays.toString(hintLetters));
					 int i = 0;
					 guessDisplay[tryCount][i++] = guessStr.charAt(i++);
					 System.out.println("guessDisp " + guessDisplay[tryCount][i++]);
					 
					 
					 //System.out.println();
					 hints[tryCount] = hintLetters;
					 tryCount++;
					 displayGame(guessDisplay, hints);
					 //findFullyCorrect(guess, code);
					 //findColourCorrect(guess, code);
				}
			
			//displayGame(guessDisplay, hints);
			}
			
			/* if(guessStr.length() == SIZE){  //for if the guess is fully correct
				
				for(int i = 0; i < SIZE; i++){
					guess[i] = guessStr.charAt(i);	
					//System.out.print("Guess: " + new String(guess[counter]));
					//System.out.println(); 
				}
				if((valid(guess, VALID_CHARS, SIZE)) == true){
					 System.out.println("they used the correct characters (GRBYOP), \nNOT IN CORRECT POS AND/OR THE CORRECT CHARACTERS FROM CODE");
					 tryCount++;
					 displayGame(guessDisplay, hints);
					 findFullyCorrect(guess, code);
					 findColourCorrect(guess, code);
				} else { 
					tryCount++;
					System.out.println("Counter for tries: " + tryCount);
					
					if(tryCount == TRIES){
						System.out.println("-----------------------------------------------");
						System.out.println("GAME OVER \t YOU LOSE \nYour attempts have exceeded " + TRIES + ". You've guessed incorrectly too many times...");
						break;
					}  
					System.out.println("Please enter your guess again of length " + SIZE +  " using the letters " + VALID_CHARS);
					findFullyCorrect(guess, code);	
				  }
			} else {
				System.out.println("Invalid, try again.");
			} */
			System.out.println();
		} 
		
		

	}
	
	/**
	 * Displays the current state of the game as it's ongoing. 
	 * 
	 * @param
	 * @param
	 * 
	 * @return
	 */
	public static String displayGame(char[][] guessDisplay, char[][] hints) {
		int counter = 0;
		int other = 0;
		String display = "";
		String guessStr = String.valueOf(guessDisplay[counter][other]);
		display = "Guess\t\tClues\n************************\n" + guessStr + "\t" +  new String(hints[counter]);	
		System.out.println(display);
		counter++;
		return display;
	}
	
	/**
	 * Randomly generates the secret code of single characters, with the appropriate length, for the player 
	 * to guess.
	 * 
	 * @param valid_chars  a string that contains all usable single characters for the method to randomly 
	 * 						generate as a colour code.
	 * @param size  an int variable that plays as the length of the colour code. It doesn't change during game.
	 * 
	 * @return code  an array of randomly generated contents that will play as the single character code that
	 * 				  the player has to guess.
	 */
	
	public static char[] createCode(String valid_chars, int size) {
		char[] givenCode = new char[size];
		Random r = new Random();
		for(int i = 0; i < size; i++){
			int random = r.nextInt(valid_chars.length());
			givenCode[i] = valid_chars.charAt(random);
		}
		for(int i = 0; i < size; i++){
			System.out.print(givenCode[i]);
		}
		System.out.println();
		return givenCode;
	}

	/**
	 * Determines if the player's guesses are valid, meaning if the player's guess fits the appropriate length
	 * and includes the correct letters. It does not indicate if the letters are in the correct position.
	 * 
	 * @param guess  a char array that contains the single characters of the player's guess.
	 * @param valid_chars  a string that contains all usable single characters for the method to randomly 
	 * 						generate as a colour code.
	 * @param size  an int variable set as the length for the colour code. It doesn't change during the game.
	 * 
	 * @return valid  a boolean that will state whether the guess from the player is accurate to the given  
	 * 				   characters or not.
	 */
	
	public static boolean valid(char[] guess, String valid_chars, int size) {
		boolean valid = false;
		int num = 0;
		//System.out.println(guess);
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < valid_chars.length(); j++){
				//System.out.println("guess: " + guess[i] + "\nvalid chars: " + valid_chars.charAt(j));
				if(guess[i] == valid_chars.charAt(j)){
					//index[0][i] = i;
					//System.out.println("INDEX # " + index[0][i]);
					num++;
					//System.out.println(num + " valid");
					//System.out.println("-----------------------------------------------");
					break;
				}
			}
		}
		//System.out.println("GUESS LENGTH " + guess.length + " && NUM " + num);
		if(num == guess.length){
			valid = true;
			return valid;
		}
		System.out.println(valid);
		return valid;
	}
	
	/**
	 * Finds the letters of the player's guess that are valid and in the correct position compared to
	 * the given colour code.
	 * 
	 * @param guess	 a 2D char array that contains the single characters of the player's guess.
	 * @param code 	a char array that contains the given code of single characters for the player to guess.
	 * 
	 * @return correctInd  a char array containing 'b' letters to indicate whether the player's single
	 * 						characters are in the correct slots. 
	 */
	
	public static char[] findFullyCorrect(char[] guess, char[] code) {
		char[] correctInd = new char[code.length];
		int counter = 0;
		
		for (int i = 0; i < code.length; i ++) {
			//System.out.println(guess[i]);
			if (guess[i] == code[i]) {
				correctInd[counter] = 'b';
				counter++;
			}
		}
		//System.out.println("counter " + counter);
		/*for(int i = 0; i < counter; i++) {
			System.out.print(correctInd[i] + " ");
		} */
		if (counter == code.length) {
			System.out.println("\tYOU WONNN");
			System.exit(0);
		} 
		removeFullyCorrect(guess, code);
		
		return correctInd;
		
	}
	
	/**
	 * Removes (outputs) the valid or invalid characters of the player's guess that are in the wrong 
	 * position.
	 * 
	 * @param guess	 a 2D char array that contains the single characters of the player's guess.
	 * @param code 	a char array that contains the given code of single characters for the player to guess.
	 * 
	 * @return incorrChar  a char array containing the letters that are in the wrong position of the player's
	 * 						guess.
	 */
	
	public static char[] removeFullyCorrect(char[] guess, char[] code) {
		char[] incorrChar = new char[code.length];
		int counter = 0;
		for(int i = 0; i < code.length; i++) {
			if(guess[i] != code[i]){
				incorrChar[counter] = guess[i];
				counter++;
			}
		}
		//System.out.println();
		/*for(int i = 0; i < counter; i++) {
			System.out.print(incorrChar[i] + " ");
		}*/
				
		//System.out.println();
		findColourCorrect(guess, code);
		return incorrChar;
	}
	
	/**
	 * Finds the letters of the player's guess that are valid but in the wrong position compared to
	 * the given colour code.
	 * 
	 * @param guess	 a 2D char array that contains the single characters of the player's guess.
	 * @param code 	a char array that contains the given code of single characters for the player to guess.
	 * 
	 * @return incorrPos  a char array containing 'w' letters to indicate whether the player's single
	 * 						characters are in the incorrect slots. 
	 */
	
	public static char[] findColourCorrect(char[] guess, char[] code) {
		char[] incorrPos = new char[guess.length];
		int counter = 0;
		
		for (int i = 0; i < code.length; i ++) {
			//System.out.println(guess[i]);
			if (guess[i] != code[i]) {
				incorrPos[counter] = 'w';
				counter++;
			}
		}
		//System.out.println("counter " + counter);
		/*for(int i = 0; i < counter; i++) {
			System.out.print(incorrPos[i] + " ");
		} */
		
		return incorrPos;
	}

}