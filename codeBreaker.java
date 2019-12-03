/*
 * Swetaa Suresh & Wendy Majadas
 * December 2, 2019
 * Code Breaker
 */
package Exercises;
import java.util.Scanner;
import java.util.Random;
public class codeBreaker {
	static Scanner console = new Scanner(System.in);
	public static void main(String[] args) {
		final String COLOURS = "YGBOPR";
		final int LENGTH = 4;
		final int TRIES = 10;
		char[] colourCode = createCode(COLOURS,LENGTH);
		System.out.println("Welcome to Code Breaker!");
		
		System.out.println("Please enter your guess of length " + LENGTH +  " using the letters " + COLOURS + ":");
		String guess1 = console.nextLine(); // takes user input
		char[] guess = new char[guess1.length()]; // creates a char array to hold user's guess
		for (int j = 0; j < guess1.length(); j++) { // goes through the guess
			guess[j] = guess1.charAt(j); // inputs the characters in the string into a char array
		}
		String code = new String (colourCode); // converts the colour code into a string
		while (!(valid(guess, COLOURS, LENGTH))) { // if the guess is not valid keep asking for a new guess
			System.out.println("Please enter your guess again of length " + LENGTH +  " using the letters " + COLOURS + ":");
			guess1 = console.nextLine(); // asks for the next guess
			guess = new char[guess1.length()]; // creates a char array to hold user's guess
			for (int k = 0; k < guess1.length(); k++) {
				guess[k] = guess1.charAt(k); // converts the string to the char array
			}
		}
		int counter = 0; // number of tries
		char[][] guesses = new char[TRIES][LENGTH]; // holds all the guesses
		char[][] clues = new char[TRIES][LENGTH]; // holds all the clues
		while (counter < TRIES) {// checks if the guess is valid and the number of tries is less than the assigned value
			if (guess1.equals(code)) { // checks if the guess is equal to the colour code
				System.out.println("Congratulations! It took you " + (counter + 1) + " guesses to find the code.");
				break; // breaks out of the while loop
			}
			for (int i = 0; i < guess.length; i ++) { // goes through the guesses array
				guesses[counter][i] = guess[i]; // adds the guess to the 2D array
			}
			for (int i = 0; i < clues[counter].length; i ++) { // goes through the clues array
				clues[counter][i] = findFullyCorrect(guess, colourCode)[i]; // adds the 'b' clues to the 2D array
			}
			int lengthCorrect = 0; // length of correct values
			for (int i = 0; i < findFullyCorrect(guess, colourCode).length; i ++) { // goes through the fully correct array
				if (!(findFullyCorrect(guess, colourCode)[i] == 0)) { // checks how many correct values there are
					lengthCorrect++; // adds to the length of correct values
				}
			}
			int j = 0; // counter for the correct colours array
			for (int i = lengthCorrect; i < clues[counter].length; i ++) { // loops that starts at the length of correct values to add the correct colours
				clues[counter][i] = findColourCorrect(guess, colourCode)[j]; // adds the correct colours
				j++;
			}
			System.out.println(displayGame(guesses, clues)); // displays the current state of the game
			System.out.println("Please enter your guess of length " + LENGTH +  " using the letters " + COLOURS + ":");
			guess1 = console.nextLine(); // asks for the next guess
			guess = new char[guess1.length()]; // creates a char array to hold user's guess
			for (int k = 0; k < guess1.length(); k++) {
				guess[k] = guess1.charAt(k); // converts the string to the char array
			}
			while (!(valid(guess, COLOURS, LENGTH))) { // if the guess is not valid keep asking for a new guess
				System.out.println("Please enter your guess again of length " + LENGTH +  " using the letters " + COLOURS + ":");
				guess1 = console.nextLine(); // asks for the next guess
				guess = new char[guess1.length()]; // creates a char array to hold user's guess
				for (int k = 0; k < guess1.length(); k++) {
					guess[k] = guess1.charAt(k); // converts the string to the char array
				}
			}
			counter ++; // adds to the user number of tries
		}
		if (counter == TRIES) { // if the user reaches the limit of tries
			System.out.println("I'm sorry you lose. The correct code was " + code + ".");
		}
	}
	/**
	 * Returns a string array containing randomly generated single character from 
	 * colours with a size of length.
	 *
	 * @param colours contains the characters that will be used in the returned array
	 * @param length determines the size of the returned array
	 * @returns		 a character array of size, length,of randomly chosen characters from colours
	 */
	public static char[] createCode(String colours, int length) {
		Random randomNumber = new Random(); // random number generator
		char colourCode[] = new char[length]; // character array of colour code
		for (int i = 0; i < length; i ++) { // loop that inputs values in colour code
			int randomInt = randomNumber.nextInt(colours.length()); // random number inbetween the length of colours
			colourCode[i] = colours.charAt(randomInt); // inputs a random value at the given index
		}
		return colourCode;
	}
	/**
	 * Returns a boolean true if the every value in the array is in the string and 
	 * if the size of the array is equal to the length.
	 *
	 * @param array of single characters
	 * @param colours contains the characters that must be in the string array
	 * @param length that array should be
	 * @return		true if all conditions are passed
	 */
	public static boolean valid(char[] guess, String colours, int length) {
		boolean valid = false; // sets the value as false
		if (guess.length == length) {
			int correctValues = 0; // sets the number of correct values as 0
			for (int i = 0; i < length; i ++) { // goes through the guess characters
				for (int j = 0; j < colours.length(); j ++) { // goes through the colours array
					if (guess[i] == colours.charAt(j)) { // checks if the values in the guess are in the colours array
						correctValues += 1; // adds 1 to correct value if it is in the colours array
					}
				}
			}
			if (correctValues == length) { // if the number of correct values is equal to the length, then valid becomes true
				valid = true;
			} 
		}
		return valid;
	}
	/**
	 * Returns a new array of 'b', if the guess values are in the same positions
	 * as the computer generated array.
	 *
	 * @param array of single characters from the user
	 * @param array of single characters randomly generated by the computer
	 * @return		array of 'b' based on number of correct positions
	 */
	public static char[] findFullyCorrect(char[] guess, char[] colourCode) {
		char correctPositions[] = new char[colourCode.length]; // new array that holds number of correct positions
		int j = 0; // counter for correctPostions array
		for (int i = 0; i < colourCode.length; i ++) { // goes through the guess and colour code
			if (guess[i] == colourCode[i]) { // checks if both values at the same positions of the array are equal
				correctPositions[j] = 'b'; // input 'b' in the correctPositions array if they are equal
				j++; // increments counter
			}
		}
		return correctPositions;
	}
	/**
	 * Returns a new array that removes all of the correct values.
	 *
	 * @param array of single characters containing user input
	 * @param array of single characters containing randomly generated values
	 * @return		array of values that are not in the correct positions or are not in the computer generated array
	 */
	public static char[] removeFullyCorrect(char[] guess, char[] colourCode) {
		char incorrectPositions[] = new char[colourCode.length]; // array for incorrect positions
		int j = 0; // counter for incorrectPositions
		for (int i = 0; i < guess.length; i ++) { // goes through guess and colourCode simultaneously
			if (!(guess[i] == colourCode[i])) { // checks if the values in the same positions are not the same
				incorrectPositions[j] = guess[i]; // stores the incorrect values in incorrectPositions
				j++; // increments counter
			} else {
				incorrectPositions[j] = 0; // places a 0 to show that the value there was correct
				j++;
			}
		}
		return incorrectPositions;
	}
	/**
	 * Removes (outputs) the valid or invalid characters of the player's guess that are in the wrong 
	 * position.
	 *
	 * @param guess	is a 2D char array that contains the single characters of the player's guess.
	 * @param colourCode is a char array that contains the given code of single characters for the player to guess.
	 *
	 * @return incorrChar is a char array containing the letters that are in the wrong position of the player's
	 * 						guess.
	 */
	public static char[] removeCodeCorrect(char[] guess, char[] colourCode) {
		char incorrectColourPositions[] = new char[colourCode.length]; // array for incorrect positions
		int j = 0; // counter for incorrectPositions
		for (int i = 0; i < guess.length; i ++) { // goes through guess and colourCode simultaneously
			if (!(guess[i] == colourCode[i])) { // checks if the values in the same positions are not the same
				incorrectColourPositions[j] = colourCode[i]; // stores the incorrect values from colourCode in incorrectCodePositions
				j++; // increments counter
			} else {
				incorrectColourPositions[j] = 0; // places a 0 to show that the value there was correct
				j++;
			}
		}
		return incorrectColourPositions;
	}
	/**
	 * Finds the letters of the player's guess that are valid but in the wrong position compared to
	 * the given colour code.
	 *
	 * @param guess	 a 2D char array that contains the single characters of the player's guess.
	 * @param colourCode 	a char array that contains the given code of single characters for the player to guess.
	 *
	 * @return correctColours  a char array containing 'w' letters to indicate whether the player's single
	 * 						characters are in the incorrect slots.
	 */
	public static char[] findColourCorrect(char[] guess, char[] colourCode) {
		char correctColours[] = new char[colourCode.length]; // new array that hold correct colours
		int counter = 0; // counter for correctColours array
		char incorrectPositions[] = removeFullyCorrect(guess, colourCode); // calls removeFullyCorrect to find leftover colours that are not in the correct positions
		char incorrectColourPositions[] = removeCodeCorrect(guess, colourCode); // calls removeFullyCorrect to find leftover colours that are not in the correct positions
		for (int i = 0; i < incorrectPositions.length; i ++) { // goes through the incorrect colours
			for (int j = 0; j < colourCode.length; j++) { // goes through the colour code
				if (incorrectPositions[i] == incorrectColourPositions[j] && !(incorrectPositions[i] == 0)) { // checks if the values in the guess are in the colour code
					correctColours[counter] = 'w'; // if condition is passed then, w is added to correctColours
					counter++; // increment the counter
					incorrectColourPositions[j] = 0; // removes the value in code, so that no other values match with it
					break;  // make j the length of colourCode to exit the loop, so that no repeats happen
				}
			}
		}
		return correctColours;
	}
	/**
	 * returns the current state of the game as it's ongoing.
	 *
	 * @param guesses is a 2D char array that holds values in LENGTH length of columns and TRIES length of rows
	 * @param clues is a 2D char array that hold values in LENGTH length of columns and TRIES length of rows
	 *
	 * @return a string of the guesses and clues
	 */
	public static String displayGame(char[][] guesses, char[][] clues) {
		String display = "Guess\t\tClues";
		display += "\n************************\n";
		for (int j = 0; j < guesses.length; j ++) {
			if (guesses[j][0] == 0) { // checks if there is a value or if the rest is just empty spaces
				break; // exits loop
			}
			for (int i = 0; i < guesses[j].length; i++) { // goes through guesses
				if (!(guesses[j][i] == 0)) { // checks if the guess is not equal to 0
					display += guesses[j][i] + " "; // adds the guess to the display
				}
			}
			display += "\t";
			for (int i = 0; i < clues[j].length; i ++) { // goes through clues
				if (!(clues[j][i] == 0)) { // checks if clue is not equal to 0
					display += clues[j][i] + " "; // adds clue to the display
				}
			}
			display += "\n";
		}
		return display;
	}
}