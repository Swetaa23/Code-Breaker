/*
 * Swetaa Suresh
 * November 18, 2019
 * Code Breaker
 */
package Exercises;
import java.util.Scanner;
import java.util.Random;
public class CodeBreaker {
	static Scanner console = new Scanner(System.in);
	public static void main(String[] args) {
		final String COLOURS = "YGBOPR";
		final int LENGTH = 4;
		final int TRIES = 10; 
		char[] colourCode = createCode(COLOURS,LENGTH);
		
		/*System.out.println("Please enter your guess of length " + LENGTH +  " using the letters " + COLOURS + ":");
		String guess1 = console.nextLine();
		char[] guess = new char[guess1.length()];
		for (int i = 0; i < guess1.length(); i++) {
			guess[i] = guess1.charAt(i);
		}
		System.out.println();
*/
		//char[][] guesses = new char[TRIES][guess1.length()];
		//char[][] clues = new char[TRIES][guess1.length()];
		char [][] guesses = {{'Y', 'G', 'B', 'Y'}, {'B', 'B', 'B', 'B'}};
		char[][] clues = {{ 'b', 'b', 'w', 'w'}, {'b'}};
		System.out.print(displayGame(guesses, clues));

		/*int guessCounter = 0;
		int cluesCounter = 0;
		while (valid(guess, COLOURS, LENGTH) && guessCounter <= 10) {
			for (int i = 0; i < LENGTH; i ++) {
				guesses[guessCounter][i] = guess1.charAt(i);
			}
			clues[cluesCounter] = findFullyCorrect(guess, colourCode);
			
			findColourCorrect(guess, colourCode);
			findFullyCorrect(guess, colourCode);
			displayGame(guesses,clues);
			guessCounter += 1;
			cluesCounter += 1;
		}*/

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
		for (int i = 0; i < colourCode.length; i++) {
			System.out.print(colourCode[i]);
		}
		System.out.println();
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
			}  else { // if the values are not the options displayed
				System.out.println("Please enter your guess again of length " + length +  " using the letters " + colours + ":");
			}
			System.out.println(valid);
		}  else { // if the values are not the options displayed
			System.out.println("Please enter your guess again of length " + length +  " using the letters " + colours + ":");
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
		for (int i = 0; i < j; i++) { // DELETE LATER
			System.out.print(correctPositions[i]);
		}
		System.out.println();
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
			}
		}
		for (int i = 0; i < j; i++) { // DELETE LATER
			System.out.print(incorrectPositions[i]);
		}
		System.out.println();
		return incorrectPositions;
	}
	public static char[] findColourCorrect(char[] guess, char[] colourCode) {
		char correctColours[] = new char[colourCode.length]; // new array that hold correct colours
		int counter = 0; // counter for correctColours array
		char incorrectPositions[] = removeFullyCorrect(guess, colourCode); // calls removeFullyCorrect to find leftover colours that are not in the correct positions
		
		for (int i = 0; i < incorrectPositions.length; i ++) { // goes through the incorrect colours
			for (int j = 0; j < colourCode.length; j++) { // goes through the colour code
				if (colourCode[i] == incorrectPositions[j]) { // checks if the values in the guess are in the colour code
					correctColours[counter] = 'w'; // if condition is passed then, w is added to correctColours
					counter++; // increment the counter
					break;  // make j the length of colourCode to exit the loop, so that no repeats happen
				}
			}
		}
		for (int i = 0; i < counter; i++) { // DELETE LATER
			System.out.print(correctColours[i]);
		}
		System.out.println();
		return correctColours;
	}
	public static String displayGame(char[][] guesses, char[][] clues) {
		String display = "Guess\tClues";
		display += "\n************************\n";
		for (int j = 0; j < 10; j ++) {
			for (int i = 0; i < guesses[j].length; i++) {
				if (!(guesses[j][i] == 0)) {
					display += guesses[j][i] + " ";
				}
			}
			/**	char [][] guesses = {{'Y', 'G', 'B', 'Y'}, {'B', 'B', 'B', 'B'}};
				char[][] clues = {{ 'b', 'b', 'w', 'w'}, {'b'}};*/
			System.out.print("\t");
			for (int i = 0; i < clues[j].length; i ++) {
				if (!(clues[j][i] == 0)) {
					display += clues[j][i] + " ";
				}
			}
		}
		
	//	System.out.println(display);
		return display;
		
	}
}